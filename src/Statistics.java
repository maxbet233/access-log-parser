import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class Statistics {
    int totalTraffic;
    LocalDateTime minTime;
    LocalDateTime maxTime;
    HashSet<String> exAddress = new HashSet<>();
    static HashMap<String, Integer> frequencyOS = new HashMap<>();

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

        if (logEntry.getResponseCode() == 200 && logEntry.getPath() != null) {
            exAddress.add(logEntry.getPath());
        }

        if (logEntry.userAgent != null) {
            if (frequencyOS.containsKey(logEntry.userAgent.getTypeOS())) {
                frequencyOS.put(logEntry.userAgent.getTypeOS(), frequencyOS.get(logEntry.userAgent.getTypeOS()) + 1);
            } else frequencyOS.put(logEntry.userAgent.getTypeOS(), 1);
        }

    }

    public int getTrafficRate(LogEntry logEntry) {
        addEntry(logEntry);
        Duration duration = Duration.between(minTime, maxTime);

        long diff = duration.getSeconds() / 3600;
        return (int) (totalTraffic / diff);
    }

    public ArrayList<String> getExistingPages(LogEntry logEntry) {
        addEntry(logEntry);

        ArrayList<String> listPages = new ArrayList<>();
        listPages.add(exAddress.toString());

        return listPages;
    }

    public HashMap<String, Double> getFrequencyOS(LogEntry logEntry) {
        addEntry(logEntry);

        int sum = 0;
        for (HashMap.Entry<String, Integer> entry : frequencyOS.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            sum += value;
        }

        HashMap<String, Double> resultMap = new HashMap<>();

        if (frequencyOS.containsKey("Windows")){
            double fr = (double) frequencyOS.get("Windows") / sum;
            resultMap.put("Windows", fr);
        }
        if (frequencyOS.containsKey("Macintosh")){
            double fr = (double) frequencyOS.get("Macintosh") / sum;
            resultMap.put("Macintosh", fr);
        }
        if (frequencyOS.containsKey("Linux")){
            double fr = (double) frequencyOS.get("Linux") / sum;
            resultMap.put("Linux", fr);
        }
        if (frequencyOS.containsKey("not valid OS")){
            double fr = (double) frequencyOS.get("not valid OS") / sum;
            resultMap.put("not valid OS", fr);
        }

        return resultMap;

    }

}
