package services

import config.ApplicationConfig
import javax.inject.Inject
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class TwitterSearchService @Inject() (ws: WSClient, properties: ApplicationConfig) {

  def fetchTweets(keyword: String) = {

    val twitterUrl = properties.twitterStandardSearchApiUrl + s"?q=$keyword" +
      s"&count=${properties.twitterResponseTweetCount}&result_type=mixed"

    val headers = ("Authorization", s"Bearer ${properties.twitterAuthToken}")

    var wsRequest: WSRequest = ws.url(twitterUrl)
    wsRequest = wsRequest.withHttpHeaders(headers)

    val response: Future[WSResponse] = wsRequest.get()

    response.map(res => println(">>>>> "+ res))

    response


  }
}
