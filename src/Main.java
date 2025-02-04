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

                    if (line.contains("Mozilla/5.0")) {
                        userAgent = line.split("Mozilla/5.0")[1];
                        UserAgent ua = new UserAgent(userAgent);

                    }


                    Statistics st = new Statistics();
//                    System.out.println(st.getRealTrafficRate(logEntry));
//                    System.out.println(st.getBotTrafficRate(logEntry));
//                    System.out.println(st.getExistingPages(logEntry));
//                    System.out.println(st.getFrequencyOS(logEntry));
//                    System.out.println(st.getNotFoundPages(logEntry));
//                    System.out.println(st.getFrequencyBrowser(logEntry));
//                    System.out.println(st.getAvgCountBadRequets(logEntry));
//                    System.out.println(st.getAvgVisitUser(logEntry));
//                    System.out.println(st.getPeakAttendance(logEntry));
//                    System.out.println(st.getSiteList(logEntry));
                    System.out.println(st.getMaxVisitUser(logEntry));


                    countLine++;
                }
                double shareYandex = (double) countYandex / countLine * 100;
                double shareGoogle = (double) countGoogle / countLine * 100;
                DecimalFormat df = new DecimalFormat("#.##");

            } catch (LineLongException | IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
