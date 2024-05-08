package com.learn.dapr.avro.service;

import com.learn.dapr.avro.model.EmployeeModel;
import com.learn.dapr.json.model.Order;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmployeePublishEventService {
    public void publishEventUsingMetadata(String pubsubName, String topic, EmployeeModel employee) {
        try (DaprClient client = new DaprClientBuilder().build()) {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("rawPayload", "true");
            metadata.put("schemaId", "2000");
            metadata.put("Content-Type", "application/json");
            metadata.put("valueSchemaType", "Avro");

            Map<String, Object> message = new HashMap<>();
            message.put("id", employee.getId());
            message.put("firstName", employee.getFirstName());
            message.put("lastName", employee.getLastName());

            // Publish the raw Avro payload to Kafka
            client.publishEvent(pubsubName, topic, message, metadata).block();

            log.info("Published Avro message to Kafka");
        } catch (Exception e) {
            log.error("Error while publishing message to Kafka: " + e.getMessage());
        }
    }

}
