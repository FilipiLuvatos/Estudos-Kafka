package br.com.estudoskafka.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class EmailService {
    public static void main(String[] args) throws InterruptedException {

        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList("ECOMMERCE_SEND_EMAIL"));
        while(true) {
            var records = consumer.poll(Duration.ofMillis(1000));
            if (records.isEmpty()) {
                System.out.println("[RESGISTROS NÃO ENCONTRADOS]");
                continue;
            }
            for (var record : records) {
                System.out.println("-------------------------------------------");
                System.out.println("[SEND EMAIL....]");
                System.out.println(record.key());
                System.out.println(record.value());
                System.out.println(record.partition());
                System.out.println(record.offset());
                Thread.sleep(5000);
                System.out.println("[EMAIL ENVIADO]");
            }
            Thread.sleep(15000);
        }

    }
    private static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getName());

        return properties;
    }
}
