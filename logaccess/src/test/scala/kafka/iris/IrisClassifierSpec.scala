import kafka.iris.{IrisStreamClassifier, IrisModel}
import org.scalatest._
import java.util.Properties
import org.apache.kafka.streams.{StreamsConfig, TopologyTestDriver}
import org.apache.kafka.streams.test.ConsumerRecordFactory
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}


// class IrisSpec extends FlatSpec with Matchers {
//    it should "return the prediction 0" in {
//     var prediction = IrisModel.score(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
//     prediction should be (0)
//   }
//   it should "return the prediction 1" in {
//     val prediction = IrisModel.score(0.002980,4.537278e-08,0.071881,0.703904,0.003914,0.809458)
//     prediction should be (1)
//   }
// }


class IrisClassifierSpec extends FlatSpec with Matchers with BeforeAndAfterAll {
    val config: Properties = {
        val p = new Properties()
        p.put(StreamsConfig.APPLICATION_ID_CONFIG, "integration-test")
        p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy config")
        p
    }

    val driver = new TopologyTestDriver(
        IrisStreamClassifier.irisStreamClassifier("input-topic", "output-topic"), config)

    val recordFactory = new ConsumerRecordFactory("input-topic", new StringSerializer(), new StringSerializer())

    override def afterAll() {
        driver.close()
    }
    
    // "LogAccess Stream Classifier" should "return the prediction 0" in {
    //     driver.pipeInput(recordFactory.create("5.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0"))
    //     val record: ProducerRecord[String, String] = driver.readOutput("output-topic", new StringDeserializer(), new StringDeserializer())
    //     record.value() should be("{\"request_time_mean\" : 0.0, \"prediction\" : 0.0, \"daily_counts\" : 0.0, \"id\" : 5.0, \"total_count\" : 0.0, \"td_mean\" : 0.0, \"is_weekend_ratio\" : 0.0}")
    // }
}