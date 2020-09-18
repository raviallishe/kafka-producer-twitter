package config

import com.twitter.hbc.httpclient.auth.OAuth1

object ApplicationConfig {

  // to-do - put below keys in config
  val twitterResponseTweetCount = 5

  val twitterAuthToken = "3548573954-gC77ml5ChX0hUqQeH1ujwVn5EAzOdiKC0RcQlKx"
  val twitterAuthSecret = "QQBk0soJdVjbC38NX1DZWEKrEMyENumZIQI6ojITiACZE"
  val twitterConsumerKey = "O3d7YeEPZz56CL7U6Ss5pyJzh"
  val twitterConsumerSecret = "TdcLkG70M7vN1BkrHQNbP9QHeKBwy4I2Yso4SSKwCI4bXw1glS"

  val oAuth1 = new OAuth1(
    twitterConsumerKey,
    twitterConsumerSecret,
    twitterAuthToken,
    twitterAuthSecret)

//  def requireProperty(name: String): String = {
//    System.getenv(name) match {
//      case value: String => value
//      case _ => throw new NoSuchElementException(s"no property set with name $name")
//    }
//  }

}
