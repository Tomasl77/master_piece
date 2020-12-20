package fr.formation.masterpiece.api.services.impl;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.UnitTestConfig;

class SharingSessionServiceImplTest extends UnitTestConfig {

    @Autowired
    private SharingSessionServiceImpl service;

    @Test
    void should_be_valid_date() {
	service.isDateValid(LocalDateTime.of(2023, 01, 01, 14, 00));
    }

    @Test
    void should_be_not_valid_date_end_day() {
	service.isDateValid(LocalDateTime.of(2022, 11, 30, 23, 59));
    }

    @Test
    void should_be_not_valid_date_start_day() {
	service.isDateValid(LocalDateTime.of(2022, 11, 30, 23, 59));
    }
}
