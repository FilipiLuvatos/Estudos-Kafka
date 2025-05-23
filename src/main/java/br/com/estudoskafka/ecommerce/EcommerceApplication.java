package br.com.estudoskafka.ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(EcommerceApplication.class, args);

		var produce = new KafkaProducer<String, String>(properties());
		var value = "12356,687415,18156786";
		var record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", value, value);
		produce.send(record, (data, ex) -> {
			if (ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
		}).get();

	}

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}


}
