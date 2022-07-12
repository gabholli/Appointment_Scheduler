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
        LocalDateTime ldtStart = LocalDateTime.of(2022, Month.AUGUST, 12, 1, 0);
        LocalDateTime ldtEnd = LocalDateTime.of(2022, Month.AUGUST, 12, 12, 30);
        boolean result = AppointmentDB.overlapAddCheck(ldtStart, ldtEnd, 1);
        assertFalse(result);
        //One of the appointments in the database as of this writing takes places between 2022-07-27 05:15:00 and
        //2022-07-27 05:30:00. THe following tests to verify the overlapping appointment and the desired returned
        //value is true
        LocalDateTime ldtStart2 = LocalDateTime.of(2022, Month.JULY, 27, 5, 15, 0);
        LocalDateTime ldtEnd2 = LocalDateTime.of(2022, Month.JULY, 27, 5, 30, 0);
        boolean result2 = AppointmentDB.overlapAddCheck(ldtStart2, ldtEnd2, 120);
        assertTrue(result2);
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
        //One of the appointments in the database as of this writing takes places between 2022-07-27 05:15:00 and
        //2022-07-27 05:30:00. THe desired result returns false because no change is made to the existing start
        //and end times
        LocalDateTime ldtStart2 = LocalDateTime.of(2022, Month.JULY, 27, 5, 15, 0);
        LocalDateTime ldtEnd2 = LocalDateTime.of(2022, Month.JULY, 27, 5, 30, 0);
        boolean result2 = AppointmentDB.overlapUpdateCheck(ldtStart2, ldtEnd2, 120, 50);
        assertFalse(result2);
        //Another appointment in the database as of this writing takes places between 2020-05-28 05:00:00 and
        //2020-05-28 06:00:00. THe following tests to verify the overlapping appointment existing for customer
        //ID 1. The desired result is true
        LocalDateTime ldtStart3 = LocalDateTime.of(2020, Month.MAY, 28, 5, 0, 0);
        LocalDateTime ldtEnd3 = LocalDateTime.of(2020, Month.MAY, 28, 6, 0, 0);
        boolean result3 = AppointmentDB.overlapUpdateCheck(ldtStart3, ldtEnd3, 1, 50);
        assertTrue(result3);

    }
}