package controllers.twitter

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.TwitterSearchService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TwitterSearchController @Inject()(cc: ControllerComponents, twitterSearchService: TwitterSearchService) extends AbstractController(cc) {


  def getTweets(keyword: String) = Action.async {
    request => {
      twitterSearchService.fetchTweets(keyword)
      Future(Ok(""))
    }
  }
}
