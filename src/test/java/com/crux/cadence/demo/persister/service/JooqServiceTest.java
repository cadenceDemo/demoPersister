package com.crux.cadence.demo.persister.service;

import com.crux.cadence.demo.persister.activity.SaveWeatherActivityImpl;
import com.crux.cadence.demo.persister.entity.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JooqServiceTest {

    @Mock
    private JooqService jooqService;

    private SaveWeatherActivityImpl saveWeatherActivity;

    @BeforeEach
    public void setUp() {
        saveWeatherActivity = new SaveWeatherActivityImpl(jooqService);
    }

    @Test
    public void saveWeatherHappyPass(){
        when(jooqService.saveWeather(anyString(), anyDouble())).thenReturn(1);

        Weather weather = new Weather();
        weather.setCity("testCity");
        weather.setTemperature(10.2);
        var result = saveWeatherActivity.saveWeather(weather);

        verify(jooqService).saveWeather(eq(weather.getCity()), eq(weather.getTemperature()));

        assertThat("save weather successfully ", result);
    }

    @Test
    public void saveWeatherErr(){
        when(jooqService.saveWeather(anyString(), anyDouble())).thenReturn(0);

        Weather weather = new Weather();
        weather.setCity("testCity");
        weather.setTemperature(10.2);
        var result = saveWeatherActivity.saveWeather(weather);

        verify(jooqService).saveWeather(eq(weather.getCity()), eq(weather.getTemperature()));

        assertThat("save weather fail ", !result);
    }
}