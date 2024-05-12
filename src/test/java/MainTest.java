import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void workingWeekendTest(){
        assertFalse(Main.isWeekendDate(LocalDate.of(2024, 11, 2)));
    }

    @Test
    public void relaxWorkingDaysTest(){
        assertTrue(Main.isWeekendDate(LocalDate.of(2024, 5, 9)));
    }

    @Test
    public void notValidYearTest(){
        assertThrows(NotValidYearException.class,
                () -> Main.isWeekendDate(LocalDate.of(2022, 5, 9)));
    }

    @Test
    public void weekendTest(){
        assertTrue(Main.isWeekendDate(LocalDate.of(2024, 5, 25)));
    }

    @Test
    public void workingDayTest(){
        assertFalse(Main.isWeekendDate(LocalDate.of(2024, 5, 23)));
    }

    @Test
    public void weekendDateAndTimeTest(){
        assertTrue(Main.isWeekendDateAndTime(
                LocalDateTime.of(2024, 5, 25, 0, 0).plusHours(10),
                ZoneId.of("Europe/Moscow")));
    }

    @Test
    public void earlierTimeTest(){
        assertTrue(Main.isWeekendDateAndTime(
                LocalDateTime.of(2024, 5, 22, 0, 0),
                ZoneId.of("Europe/Moscow")));
    }

    @Test
    public void latestTimeTest(){
        assertTrue(Main.isWeekendDateAndTime(
                LocalDateTime.of(2024, 5, 22, 0, 0).plusHours(22),
                ZoneId.of("Europe/Moscow")));
    }

    @Test
    public void workingDateAndTimeTest(){
        assertFalse(Main.isWeekendDateAndTime(
                LocalDateTime.of(2024, 5, 22, 0, 0).plusHours(10),
                ZoneId.of("Europe/Moscow")));
    }

}
