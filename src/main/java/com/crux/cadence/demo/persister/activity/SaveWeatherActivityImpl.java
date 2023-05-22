package com.crux.cadence.demo.persister.activity;

import com.crux.cadence.demo.persister.entity.Weather;
import com.crux.cadence.demo.persister.service.JooqService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveWeatherActivityImpl implements SaveWeatherActivity{
    @NonNull
    private final JooqService service;

    @Override
    public boolean saveWeather(Weather weather) {
        return service.saveWeather(weather.getCity(), weather.getTemperature()) == 1;
    }
}
