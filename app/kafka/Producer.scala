package kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.producer.ProducerConfig._

object Producer {

  val server = "localhost:9092"
  val properties = new Properties()

  //create producer properties
  properties.put(BOOTSTRAP_SERVERS_CONFIG, server)
  properties.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  properties.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

  //create the producer
  val producer = new KafkaProducer[String, String](properties)
}
