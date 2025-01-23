import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Statistics {
    int totalTraffic;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;

        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("dd/MMM/yyyy:HH:mm:ss").toFormatter(Locale.ENGLISH);
        this.minTime = LocalDateTime.parse("25/Sep/2022:06:00:00", df);
        this.maxTime = LocalDateTime.parse("25/Sep/2022:07:00:00", df);
    }

    public void addEntry(LogEntry logEntry) {
        if (logEntry.getTime().isBefore(minTime)) {
            this.minTime = logEntry.getTime();
        } else if (logEntry.getTime().isAfter(maxTime)) {
            this.maxTime = logEntry.getTime();
        }
        totalTraffic = logEntry.getSize();
    }

    public double getTrafficRate(LogEntry logEntry) {
        int res = 0;

        Statistics statistics = new Statistics();
        statistics.addEntry(logEntry);

        Duration duration = Duration.between(statistics.minTime, statistics.maxTime);
        long diff = duration.getSeconds() / 3600;
        res = (int) (statistics.totalTraffic / diff);
        return res;
    }
}
