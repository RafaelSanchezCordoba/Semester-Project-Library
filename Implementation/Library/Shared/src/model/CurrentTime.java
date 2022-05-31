package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that can return the current date and time formatted in various standard formats.
 * @author Rafael Sanchez Cordoba.
 * @version 1.0 - 08/04/22.
 */
public class CurrentTime implements Serializable
{
    private static final DateTimeFormatter EUROPEAN_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM_dd");

    /**
     * Empty constructor.
     */
    public CurrentTime() {
    }

    /**
     * Get the local time
     * @return
     * String local time with a specific format.
     */
    public String getFormattedTime() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(EUROPEAN_TIME_FORMATTER);
    }

    /**
     * Get the local date.
     * @return
     * String local date with a specific format.
     */
    public String getFormattedIsoDate() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(ISO_DATE_FORMATTER);
    }
}
