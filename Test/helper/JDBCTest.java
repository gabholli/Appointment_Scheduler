package helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDBCTest {

    @Test
    void validation() {
        JDBC jdbc = new JDBC();
        boolean result = jdbc.validation("test", "test");
        boolean result2 = jdbc.validation("admin", "admin");
        assertTrue(result);
        assertTrue(result2);
    }
}