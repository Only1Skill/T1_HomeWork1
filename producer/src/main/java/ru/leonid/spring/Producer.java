package ru.leonid.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, WeatherPrognose> kafkaTemplate;
    private final WeatherPrognoseService weatherPrognoseService;

    public Producer(KafkaTemplate<String, WeatherPrognose> kafkaTemplate, ObjectMapper objectMapper, WeatherPrognoseService weatherPrognoseService) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.weatherPrognoseService = weatherPrognoseService;
    }

    @Scheduled(fixedRateString = "2000")
    public String sendMessage() throws JsonProcessingException {
        WeatherPrognose weatherPrognose = weatherPrognoseService.generateWeatherPrognose();
        kafkaTemplate.send(orderTopic, weatherPrognose);

        log.info("weather prognose produced {}", weatherPrognose);

        return "message sent";
    }
}