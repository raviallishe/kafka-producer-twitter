package models

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{JsPath, Json, Reads, Writes}

case class Tweet(author: String, text: String)

object Tweet {
  implicit val tweetWrites = new Writes[Tweet] {
    def writes(tweet: Tweet) = Json.obj(
      "author" -> tweet.author,
      "text" -> tweet.text
    )
  }

  implicit val tweetReads: Reads[Tweet] = (
    (JsPath \ "user" \ "name").read[String] and
      (JsPath \ "text").read[String]
    ) (Tweet.apply _)
}