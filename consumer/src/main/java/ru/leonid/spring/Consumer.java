package ru.leonid.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {


    @KafkaListener(
            topics = "${kafka.topic.name}"
    )
    public void consumeMessage(WeatherPrognose message) throws JsonProcessingException {
        log.info("message consumed {}", message.toString());
    }

}