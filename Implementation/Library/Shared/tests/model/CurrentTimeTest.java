package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CurrentTimeTest {

    private CurrentTime currentTime;

    @BeforeEach
    void setUp() {}

    @Test void return_current_time_in_iso_formatter(){
        currentTime = new CurrentTime();
        System.out.println(currentTime.getFormattedIsoDate());
    }

    @Test void return_current_time_in_european_time_formatter(){
        currentTime = new CurrentTime();
        System.out.println(currentTime.getFormattedTime());
    }
}