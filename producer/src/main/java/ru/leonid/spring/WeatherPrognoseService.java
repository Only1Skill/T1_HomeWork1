package ru.leonid.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class WeatherPrognoseService {

    private final Random random =  new Random();
    private static final String[] WEATHER_CONDITIONS = {"солнечно", "облачно", "дождь"};

    public WeatherPrognose WeatherPrognose(WeatherPrognose weatherPrognose) throws JsonProcessingException {
        WeatherPrognose weather = generateWeatherPrognose();
        log.debug("Сгенерирован прогноз погоды: {}", weather.toString());
        return weather;
    }

    @Scheduled(fixedRate = 2000)
    public WeatherPrognose generateWeatherPrognose() {
        WeatherPrognose weather = new WeatherPrognose();
        weather.setTemperature(random.nextDouble(36));
        weather.setDescription(WEATHER_CONDITIONS[random.nextInt(WEATHER_CONDITIONS.length)]);
        return weather;
    }
}