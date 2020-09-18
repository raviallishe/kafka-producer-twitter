package services

import java.util
import java.util.Arrays
import java.util.concurrent.{LinkedBlockingQueue, TimeUnit}
import authentication.StreamSource
import org.slf4j.LoggerFactory
import scala.util.{Failure, Success, Try}

object TwitterSearchService extends App {

  val logger = LoggerFactory.getLogger(TwitterSearchService.getClass.getName)
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
          println(">>>>tweet: " + value)
          logger.info(">>>>log tweet: " + value)
        }
        case Failure(exception) => {
          println(">>>>>>: Exception")
          logger.warn(">>>>>>: log Exception")
          hosebirdClient.stop()
        }
      }
    }

    println(">>>>>>: end of streaming")
    logger.info(">>>>>>: log end of streaming")

  }

  fetchTweets("modi")
}
