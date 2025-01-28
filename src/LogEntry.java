import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;


public class LogEntry {
    final String line;
    final String ipAddr;
    final LocalDateTime time;
    final HttpMethod method;
    final String path;
    final int responseCode;
    final int size;
    final String referer;
    UserAgent userAgent;

    enum HttpMethod {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    public LogEntry(String line) {
        this.line = line;

        String[] parts = line.split(" ");

        ipAddr = parts[0];

        String times = parts[3].replaceAll("\\[", "");
        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("dd/MMM/yyyy:HH:mm:ss").toFormatter(Locale.ENGLISH);
        time = LocalDateTime.parse(times, df);

        method = HttpMethod.valueOf(parts[5].replaceAll("\"", ""));

        path = parts[6];

        responseCode = Integer.parseInt(parts[8]);

        size = Integer.parseInt(parts[9]);

        referer = parts[10];

        if (line.contains("Mozilla/5.0")){
            userAgent = new UserAgent(line.split("Mozilla/5.0")[1]);
        }

    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getSize() {
        return size;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
