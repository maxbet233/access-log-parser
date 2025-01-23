import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        int count = 1;
        while (true) {
            System.out.println("Введите путь до файла:");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);

            boolean fileExist = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExist || isDirectory) {
                System.out.println("Файл не найден, введите полный путь");
                continue;
            }
            System.out.println("Путь указан верно! Это файл номер " + count);
            count++;

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                String userAgent = "";

                int countLine = 0;
                int countYandex = 0;
                int countGoogle = 0;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) throw new LineLongException("Длина строки превышает 1024 символа");

                    LogEntry logEntry = new LogEntry(line);
                    System.out.println(logEntry.getIpAddr());
                    System.out.println(logEntry.getTime());
                    System.out.println(logEntry.getMethod());
                    System.out.println(logEntry.getPath());
                    System.out.println(logEntry.getResponseCode());
                    System.out.println(logEntry.getSize());
                    System.out.println(logEntry.getReferer());
                    System.out.println(logEntry.getUserAgent());

                    if (line.contains("Mozilla/5.0")){
                        userAgent = line.split("Mozilla/5.0")[1];
                        UserAgent ua = new UserAgent(userAgent);
                        System.out.println(ua.getTypeOS());
                        System.out.println(ua.getBrowser());
                    }

                    Statistics  st = new Statistics();
                    System.out.println(st.getTrafficRate(logEntry));



//                    if (line.contains("compatible; ")) {
//                        String[] bkt = line.split("compatible; ");
//                        String firstBrackets = bkt[1];
//                        String[] parts = firstBrackets.split(";");
//
//                        if (parts.length >= 2) {
//                            String fragment = parts[0];
//                            fragment = fragment.replaceAll("\\s", "");
//
//                            int indexSlash = fragment.indexOf("/");
//                            if (indexSlash != -1) {
//                                String bot = fragment.substring(0, indexSlash);
//
//                                if (bot.equals("YandexBot")) countYandex++;
//                                if (bot.equals("Googlebot")) countGoogle++;
//                            }
//                        }
//                    }
                    countLine++;
                }
                double shareYandex = (double) countYandex / countLine * 100;
                double shareGoogle = (double) countGoogle / countLine * 100;
                DecimalFormat df = new DecimalFormat("#.##");

//                System.out.println("Количество строк: " + countLine);
//                System.out.println("Количество Яндекс-ботов: " + countYandex);
//                System.out.println("Количество Гугл-ботов: " + countGoogle);
//                System.out.println("Доля запросов Яндекс-бота: " + df.format(shareYandex) + "%");
//                System.out.println("Доля запросов Гугл-бота: " + df.format(shareGoogle) + "%");

            } catch (LineLongException | IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
