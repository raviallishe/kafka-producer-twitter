package services

import java.util
import java.util.Arrays
import java.util.concurrent.{Future, LinkedBlockingQueue, TimeUnit}

import authentication.StreamSource
import kafka.KafkaProducer
import org.apache.kafka.clients.producer.{Callback, ProducerRecord, RecordMetadata}
import org.slf4j.LoggerFactory

import scala.util.{Failure, Success, Try}

object TwitterFeedService extends App {

  val logger = LoggerFactory.getLogger(TwitterFeedService.getClass.getName)
  val msgQueue = new LinkedBlockingQueue[String](10)

  def fetchTweets(keyword: String) = {
    val terms: util.List[String] = Arrays.asList(keyword)
    val hosebirdClient = StreamSource.createTwitterClient(msgQueue, terms)
    hosebirdClient.connect()

    while (!(hosebirdClient.isDone)) {
      Try {
        msgQueue.poll(5, TimeUnit.SECONDS)
      } match {
        case Success(value) if value.nonEmpty => {
          logger.info(">>>> tweet: " + value)
          KafkaProducer.producer.send(new ProducerRecord[String, String]("twitter_tweets", value),
            new Callback {
              override def onCompletion(recordMetadata: RecordMetadata, exception: Exception) = {
                if(exception != null) {
                  logger.error(">>>>> Something bad happened", exception)
                }
              }
            })
        }
        case Failure(exception) => {
          logger.warn(">>>>>>: Exception")
          exception.printStackTrace()
          hosebirdClient.stop()
        }
        case _ => {
          logger.warn(">>>>>> no value")
          hosebirdClient.stop()
        }
      }
    }

    logger.info(">>>>>>: end of streaming")
  }

  fetchTweets("modi")
}