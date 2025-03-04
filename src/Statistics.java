import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Stream;

public class Statistics {
    private long totalTraffic;
    private long realTraffic;
    private long botTraffic;
    static int isBotRequest;
    private static double countAvgBadRequest;
    private static LocalDateTime minTime;
    private static LocalDateTime maxTime;
    private static double countAvgUsers;
    private static int countAllRealUsers;
    private static final HashMap<String, Integer> countUsers = new HashMap<>();
    private static final HashSet<String> exAddress = new HashSet<>();
    private static final HashSet<String> notFoundAddress = new HashSet<>();
    private static final HashSet<String> siteList = new HashSet<>();
    private static final HashMap<String, Integer> frequencyOS = new HashMap<>();
    private static final HashMap<String, Integer> frequencyBrowser = new HashMap<>();
    private static final HashMap<String, Integer> maxVisitUser = new HashMap<>();
    private static final HashMap<LocalDateTime, Integer> quantityPerSecond = new HashMap<>();

    public Statistics() {
        this.totalTraffic = 0;
        this.realTraffic = 0;
        this.botTraffic = 0;

//        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("dd/MMM/yyyy:HH:mm:ss").toFormatter(Locale.ENGLISH);
        minTime = null;
        maxTime = null;

    }

    /*---------------------Обработка входящей строки---------------------------------*/
    public void addEntry(LogEntry logEntry) {

        /*--------------------Общий объем запроса--------------------*/
        totalTraffic += logEntry.getSize();

        /*--------------------Счетчик запросов с ошибками--------------------*/
        if (logEntry.getResponseCode() >= 400 && logEntry.getResponseCode() <= 599) {
            countAvgBadRequest++;
        }

        /*--------------------Количество трафика реального пользователя--------------------*/
        if (logEntry.userAgent != null && !logEntry.userAgent.isBot()) {
            realTraffic += logEntry.getSize();
        }

        /*--------------------Количество трафика бота--------------------*/
        if (logEntry.userAgent != null && logEntry.userAgent.isBot()) {
            botTraffic += logEntry.getSize();
        }

        /*--------------------Пересчет временного окна--------------------*/
        if ((minTime != null) && (maxTime != null)) {
            if (logEntry.getTime().isBefore(minTime)) {
                minTime = logEntry.getTime();
            } else if (logEntry.getTime().isAfter(maxTime)) {
                maxTime = logEntry.getTime();
            }
        } else {
            minTime = logEntry.getTime();
            maxTime = logEntry.getTime();
        }

        /*--------------------Коллекция существующих эндпоинтов--------------------*/
        if (logEntry.getResponseCode() == 200 && logEntry.getPath() != null) {
            exAddress.add(logEntry.getPath());
        }

        /*--------------------Количество не существующих эндпоинтов--------------------*/
        if (logEntry.getResponseCode() == 404 && logEntry.getPath() != null) {
            notFoundAddress.add(logEntry.getPath());
        }

        /*--------------------Таблица посещаемости уникальными пользователями--------------------*/
        if (logEntry.userAgent != null && !logEntry.userAgent.isBot()) {

            if (countUsers.containsKey(logEntry.getIpAddr())) {
                countUsers.put(logEntry.getIpAddr(), countUsers.get(logEntry.getIpAddr()) + 1);
            } else {
                countUsers.put(logEntry.getIpAddr(), 1);
            }
        }

        /*--------------------Количество посещений всеми реальными пользователями--------------------*/
        if (logEntry.userAgent != null && !logEntry.userAgent.isBot()) {
            countAllRealUsers++;
        }

        /*--------------------Таблица максимальной посещаемости пользователя--------------------*/
        if (logEntry.userAgent == null || !logEntry.userAgent.isBot()) {

            if (maxVisitUser.containsKey(logEntry.getIpAddr())) {
                maxVisitUser.put(logEntry.getIpAddr(), maxVisitUser.get(logEntry.getIpAddr()) + 1);
            } else {
                maxVisitUser.put(logEntry.getIpAddr(), 1);
            }
        }

        /*--------------------Таблица частот ОС--------------------*/
        if (logEntry.userAgent != null) {
            if (frequencyOS.containsKey(logEntry.userAgent.getTypeOS())) {
                frequencyOS.put(logEntry.userAgent.getTypeOS(), frequencyOS.get(logEntry.userAgent.getTypeOS()) + 1);
            } else frequencyOS.put(logEntry.userAgent.getTypeOS(), 1);
        }

        /*--------------------Таблица частот браузеров--------------------*/
        if (logEntry.userAgent != null) {
            if (frequencyBrowser.containsKey(logEntry.userAgent.getBrowser())) {
                frequencyBrowser.put(logEntry.userAgent.getBrowser(), frequencyBrowser.get(logEntry.userAgent.getBrowser()) + 1);
            } else frequencyBrowser.put(logEntry.userAgent.getBrowser(), 1);
        }

        /*--------------------Таблица запросов в секунду времени--------------------*/
        if (logEntry.userAgent == null || !logEntry.userAgent.isBot()) {
            if (quantityPerSecond.containsKey(logEntry.getTime())) {
                quantityPerSecond.put(logEntry.getTime(), quantityPerSecond.get(logEntry.getTime()) + 1);
            } else quantityPerSecond.put(logEntry.getTime(), 1);
        } else isBotRequest++;

        /*--------------------Множество доменов--------------------*/
        if (!logEntry.getReferer().contains("\"-\"") && logEntry.getReferer().contains("http")) {
            String encodedUrl = logEntry.getReferer();
            String decode = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8);

            siteList.add(decode
                    .replaceAll("\"", "")
                    .replaceAll("https://", "")
                    .replaceAll("http://", "")
                    .replaceAll("www.", "")
                    .replaceAll("&", "/"));
        }
    }

    /*--------------------Получение среднего объёма трафика пользователей за час--------------------*/
    public long getTotalTraffic() {
        Duration duration = Duration.between(minTime, maxTime);
        long diff = duration.getSeconds() / 3600;
        return totalTraffic / diff;
    }

    /*--------------------Получение среднего объёма трафика реальных пользователей за час--------------------*/

    public long getRealTraffic() {
        Duration duration = Duration.between(minTime, maxTime);
        long diff = duration.getSeconds() / 3600;

        return (realTraffic / diff);
    }

    /*-----------------------Получение среднего объёма трафика ботов за час ---------------------------------*/

    public long getBotTraffic() {
        Duration duration = Duration.between(minTime, maxTime);
        long diff = duration.getSeconds() / 3600;

        return (botTraffic / diff);
    }

    /*--------------------Получение существующих страниц сайта--------------------*/
    public HashSet<String> getExAddress() {
        return exAddress;
    }


    /*--------------------Получение не существующих страниц сайта--------------------*/
    public HashSet<String> getNotFoundAddress() {
        return notFoundAddress;
    }

    /*--------------------Получение статистики по ОС--------------------*/
    public HashMap<String, Double> getFrequencyOS() {
        int sum = 0;
        for (HashMap.Entry<String, Integer> entry : frequencyOS.entrySet()) {
            Integer value = entry.getValue();
            sum += value;
        }

        HashMap<String, Double> resultMap = new HashMap<>();

        if (frequencyOS.containsKey("Windows")) {
            double fr = (double) frequencyOS.get("Windows") / sum;
            resultMap.put("Windows", fr);
        }
        if (frequencyOS.containsKey("Macintosh")) {
            double fr = (double) frequencyOS.get("Macintosh") / sum;
            resultMap.put("Macintosh", fr);
        }
        if (frequencyOS.containsKey("Linux")) {
            double fr = (double) frequencyOS.get("Linux") / sum;
            resultMap.put("Linux", fr);
        }
        if (frequencyOS.containsKey("not valid OS")) {
            double fr = (double) frequencyOS.get("not valid OS") / sum;
            resultMap.put("not valid OS", fr);
        }

        return resultMap;
    }

    /*--------------------Получение статитики по браузерам--------------------*/
    public HashMap<String, Double> getFrequencyBrowser() {
        int sum = 0;
        for (HashMap.Entry<String, Integer> entry : frequencyBrowser.entrySet()) {
            Integer value = entry.getValue();
            sum += value;
        }

        HashMap<String, Double> resultMap = new HashMap<>();

        if (frequencyBrowser.containsKey("Firefox")) {
            double fr = (double) frequencyBrowser.get("Firefox") / sum;
            resultMap.put("Firefox", fr);
        }
        if (frequencyBrowser.containsKey("Chrome")) {
            double fr = (double) frequencyBrowser.get("Chrome") / sum;
            resultMap.put("Chrome", fr);
        }
        if (frequencyBrowser.containsKey("Opera")) {
            double fr = (double) frequencyBrowser.get("Opera") / sum;
            resultMap.put("Opera", fr);
        }
        if (frequencyBrowser.containsKey("Edge")) {
            double fr = (double) frequencyBrowser.get("Edge") / sum;
            resultMap.put("Edge", fr);
        }
        if (frequencyBrowser.containsKey("Another browser")) {
            double fr = (double) frequencyBrowser.get("Another browser") / sum;
            resultMap.put("Another browser", fr);
        }

        return resultMap;
    }

    /*--------------------Получение среднего количества ошибочных запросов--------------------*/
    public double getCountBadRequest() {
        Duration duration = Duration.between(minTime, maxTime);
        double diff = (double) duration.getSeconds() / 3600;
        return countAvgBadRequest / diff;
    }

    /*--------------------Получение среднего количества посещений сайта одним пользователем--------------------*/
    public double getCountAvgUsers() {
        int allVisit = 0;
        for (HashMap.Entry<String, Integer> entry : countUsers.entrySet()) {
            Integer value = entry.getValue();
            allVisit += value;
        }
        countAvgUsers = countUsers.size();
        return (double) allVisit / countAvgUsers;
    }

    /*--------------------Получение среднего количества посещений сайта за час--------------------*/
    public double getCountAvgUsersPerHour() {
        Duration duration = Duration.between(minTime, maxTime);
        double diff = (double) duration.getSeconds() / 3600;
        return (double) countAllRealUsers/diff;
    }

    /*--------------------Получение пиковой посещаемости сайта в секунду--------------------*/
    public String getQuantityPerSecond() {
        Collection<Integer> count = quantityPerSecond.values();
        Stream<Integer> stream = count.stream();
        Integer maxValue = stream.max(Integer::compare).get();
        LocalDateTime time = null;

        for (Map.Entry<LocalDateTime, Integer> entry : quantityPerSecond.entrySet()) {
            if (entry.getValue().equals(maxValue)) {
                time = entry.getKey();
                break;
            }
        }
        return "В секунду " + time + " было " + maxValue + " запросов";
    }

    /*--------------------Получение списка доменов сайтов--------------------*/
    public HashSet<String> getSiteList() {
        HashSet<String> res = new HashSet<>();
        String[] partsSite;

        for (String s : siteList) {

            if (s.contains("/")) {
                partsSite = s.split("/");
                res.add(partsSite[0]);
            } else {
                partsSite = s.split("&");
                res.add(partsSite[0]);
            }
        }
        return res;
    }

    /*--------------------Получение максимальной посещаемости одним пользователем--------------------*/
    public HashMap<String, Integer> getMaxVisitUser() {
        HashMap<String, Integer> res = new HashMap<>();
        int maxValue = 0;
        String user = "";
        for (int value : maxVisitUser.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        for (Map.Entry<String, Integer> entry : maxVisitUser.entrySet()) {
            if (entry.getValue().equals(maxValue)) {
                user = entry.getKey();
                break;
            }
        }
        res.put(user, maxValue);
        return res;
    }
}
