package br.com.estudoskafka.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudeDetectorTWOService {
    public static void main(String[] args) {
        var fraudService = new FraudeDetectorTWOService();
        var service = new KafkaService(FraudeDetectorTWOService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudService::parse);
        service.run();
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("------------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ignoring
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }
}
