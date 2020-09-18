package authentication

import java.util
import java.util.concurrent.LinkedBlockingQueue

import com.twitter.hbc.ClientBuilder
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint
import com.twitter.hbc.core.processor.StringDelimitedProcessor
import com.twitter.hbc.core.{Constants, HttpHosts}
import config.ApplicationConfig

object StreamSource {

  private val endpoint = new StatusesFilterEndpoint()

  def createTwitterClient(msgQueue: LinkedBlockingQueue[String], terms: util.List[String]) = {
    endpoint.trackTerms(terms)

    val builder = new ClientBuilder()
      .hosts(new HttpHosts(Constants.STREAM_HOST))
      .authentication(ApplicationConfig.oAuth1)
      .endpoint(endpoint)
      .processor(new StringDelimitedProcessor(msgQueue))

    builder.build()

  }

}
