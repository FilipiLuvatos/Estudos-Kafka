package br.com.estudoskafka.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(EcommerceApplication.class, args);

		var produce = new KafkaProducer<String, String>(properties());
		var key = UUID.randomUUID().toString();
		var value = key + ",3548,9895";
		var record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", key, value);
		produce.send(record, getCallback()).get();
		var email = "Bem-Vindo. Processando seu pedido";
		var emailRecord = new ProducerRecord<String, String>("ECOMMERCE_SEND_EMAIL", key, email);
		produce.send(record,getCallback());
		produce.send(emailRecord,getCallback());

	}

	private static Callback getCallback() {
		return (data, ex) -> {
			if (ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
		};
	}

	private static Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "2");
		return properties;
	}


}
