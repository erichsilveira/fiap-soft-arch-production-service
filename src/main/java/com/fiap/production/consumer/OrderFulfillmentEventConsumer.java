package com.fiap.production.consumer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.production.domain.entity.OrderFulfillmentEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class OrderFulfillmentEventConsumer {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

    @SqsListener(value = "${order.fullfilment.queue.url}", acknowledgementMode = "ALWAYS")
    public void consumeOrder(final String rawMessage) {
        log.info("Processing order payment event: {}", rawMessage);

        try {
            String deserializable = MAPPER.readValue(rawMessage, String.class);
            OrderFulfillmentEvent orderFulfillmentEvent = MAPPER.readValue(deserializable, OrderFulfillmentEvent.class);
            log.info("Deserialized event: {}", orderFulfillmentEvent);

            if(orderFulfillmentEvent.getStatus().equals("READY")) {
                log.info("Starting the production of the order {}", orderFulfillmentEvent.getOrderId());
            } else {
                throw new Exception("Order not ready!");
            }

        } catch (Exception e) {
            log.error("Error consuming payment event", e);
        }

    }
}
