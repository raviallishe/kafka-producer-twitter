package config

import javax.inject.{Inject, Singleton}
import play.api.{Configuration}

@Singleton
class ApplicationConfig @Inject()(implicit val configuration: Configuration) {


  val twitterStandardSearchApiUrl = requireProperty("TWITTER_STANDARD_SEARCH_URL")
  val twitterResponseTweetCount = 5
//  val twitterAccessKey = requireProperty("twitter.auth.access.key")
  val twitterAuthToken = "QQBk0soJdVjbC38NX1DZWEKrEMyENumZIQI6ojITiACZE"


//  def readFromConfiguration(name: String): String = {
//    val value: Option[String] = configuration.get(name)
//    value match {
//      case Some(value) => println(">>>>>> value: "+ value ); value
//      case _ => "not available"
//    }
//  }

  def requireProperty(name: String): String = {
    System.getenv(name) match {
      case value: String => value
      case _ => throw new NoSuchElementException(s"no property set with name $name")
    }
  }

}
