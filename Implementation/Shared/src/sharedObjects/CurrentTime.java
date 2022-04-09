package sharedObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that can return the current date and time formatted in various standard formats.
 */
public class CurrentTime {
    private static final DateTimeFormatter EUROPEAN_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM_dd");

    public CurrentTime() {
    }

    public String getFormattedTime() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(EUROPEAN_TIME_FORMATTER);
    }

    public String getFormattedIsoDate() {
        LocalDateTime time = LocalDateTime.now();
        return time.format(ISO_DATE_FORMATTER);
    }
}
