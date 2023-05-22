package com.crux.cadence.demo.persister.service;

import com.crux.cadence.demo.dsl.public_.tables.Currentweather;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class JooqService {
    @NonNull
    private final DSLContext dsl;

    public int saveWeather(String city, Double temperature){
        Currentweather weather = Currentweather.CURRENTWEATHER;

        try {
            return dsl.insertInto(weather)
                .set(weather.CITY, city)
                .set(weather.TEMPERATURE, temperature)
                .set(weather.CREATED_ON, ZonedDateTime.now().toLocalDateTime())
                .execute();
        } catch (Exception ex){
            System.out.println("persist weather error "+ex.getMessage());
            return 0;
        }

    }
}
