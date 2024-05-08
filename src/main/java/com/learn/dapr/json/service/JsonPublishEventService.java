package com.learn.dapr.json.service;

import com.learn.dapr.avro.model.EmployeeModel;
import com.learn.dapr.json.model.Order;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonPublishEventService {
    public void publishEventUsingMetadata(String pubsubName, String topic, Order order) {
        try (DaprClient client = new DaprClientBuilder().build()) {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("rawPayload", "true");
            metadata.put("schemaId", "1000");
            metadata.put("Content-Type", "application/json");

            Map<String, Object> schema = new HashMap<>();
            schema.put("type", "struct");
            schema.put("fields", new Object[]{
                    Map.of("field", "id", "type", "int32"),
                    Map.of("field", "name", "type", "string"),
                    Map.of("field", "price", "type", "float")
            });

            Map<String, Object> message = new HashMap<>();
            message.put("schema", schema);
            message.put("payload", order);

            client.publishEvent(pubsubName, topic, message, metadata).block();

            log.info("Published json message to Kafka");
        } catch (Exception e) {
            log.error("Error while publishing message to Kafka: " + e.getMessage());
        }

    }

}
