package helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Class containing time conversion methods
 */
public class Time {

    /**
     * Method used for converting time appropriately, as well as allowing appropriate population
     * of times in comboboxes in adding and updating appointment forms
     */
    public static void loadTime() {

        ZonedDateTime etStart = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime etEnd = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22, 0), ZoneId.of("America/New_York"));

        ZonedDateTime start = etStart.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime end = etEnd.withZoneSameInstant(ZoneId.systemDefault());

        while (start.isBefore(end)) {
            ListManager.allStartTimes.add(start.toLocalTime());
            start = start.plusMinutes(15);
            ListManager.allEndTimes.add(start.toLocalTime());
        }
    }


}
