package config

import com.twitter.hbc.httpclient.auth.OAuth1

object ApplicationConfig {

  // to-do - put below keys in config
  val twitterResponseTweetCount = 5

  val twitterAuthToken = "******-******"
  val twitterAuthSecret = "******"
  val twitterConsumerKey = "******"
  val twitterConsumerSecret = "******"

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
