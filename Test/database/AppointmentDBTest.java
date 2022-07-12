package database;

import helper.JDBC;
import main.App;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDBTest {

    @Test
    void overlapAddCheck() {
        JDBC.openConnection();
        //Compares given dates and times to what is in database to detect overlapping appointments, set
        //to the given customer ID. If false is returned, there is no overlap, which is the desired result
        LocalDateTime ldtStart = LocalDateTime.of(2022, Month.AUGUST, 12, 1, 00);
        LocalDateTime ldtEnd = LocalDateTime.of(2022, Month.AUGUST, 12, 12, 30);
        boolean result = AppointmentDB.overlapAddCheck(ldtStart, ldtEnd, 1);
        assertFalse(result);
    }

    @Test
    void overlapUpdateCheck() {
        JDBC.openConnection();
        //Compares given dates and times to what is in database to detect overlapping appointments, set
        //to the given customer ID and appointment ID. If false is returned, there is no overlap, which
        // is the desired result
        LocalDateTime ldtStart = LocalDateTime.of(2022, Month.AUGUST, 12, 1, 00);
        LocalDateTime ldtEnd = LocalDateTime.of(2022, Month.AUGUST, 12, 12, 30);
        boolean result = AppointmentDB.overlapUpdateCheck(ldtStart, ldtEnd, 1, 1);
        assertFalse(result);
    }
}