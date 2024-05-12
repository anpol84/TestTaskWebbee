import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static List<LocalDate> WORKING_WEEKENDS;
    private final static List<LocalDate> RELAX_WORKING_DAYS;

    static {
        WORKING_WEEKENDS = new ArrayList<>();
        WORKING_WEEKENDS.add(LocalDate.of(2024, 4, 27));
        WORKING_WEEKENDS.add(LocalDate.of(2024, 11, 2));

        RELAX_WORKING_DAYS = new ArrayList<>();
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 1, 1));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 1, 2));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 1, 3));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 1, 4));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 1, 5));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 1, 8));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 2, 23));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 3, 8));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 4, 29));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 4, 30));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 5, 1));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 5, 9));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 5, 10));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 6, 12));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 11, 4));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 12, 30));
        RELAX_WORKING_DAYS.add(LocalDate.of(2024, 12, 31));
    }

    public static boolean isWeekendDate(LocalDate data){
        //Если год не 2024 -> данные не валидны
        if (data.getYear() != 2024){
            throw new NotValidYearException("Year must be 2024");
        }
        //Проверяем на рабочие выходные
        if (WORKING_WEEKENDS.contains(data)){
            return false;
        }
        //Проверяем на выходные в будние
        if (RELAX_WORKING_DAYS.contains(data)){
            return true;
        }
        //проверяем на субботу и воскресенье
        return data.getDayOfWeek().equals(DayOfWeek.SATURDAY) || data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }
    public static boolean isWeekendDateAndTime(LocalDateTime time, ZoneId zoneId){
        //переводим в московское время, т.к. рабочее время в условии указано по этому часовому поясу
        ZonedDateTime zonedDateTime = ZonedDateTime.of(time, zoneId);
        ZonedDateTime moscowTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Moscow"));

        LocalDateTime moscowTimeLocal = moscowTime.toLocalDateTime();
        LocalDate localDate = moscowTimeLocal.toLocalDate();
        //если дата это выходной, то смотреть на время нет смысла
        if (isWeekendDate(localDate)){
            return true;
        }

        //проверяем на время если это не выходной
        return moscowTimeLocal.getHour() < 9 || moscowTimeLocal.getHour() >= 18;
    }
    public static void main(String[] args) {
    }
}
