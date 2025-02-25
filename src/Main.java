import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Statistics st = new Statistics();
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
                    st.addEntry(logEntry);

                    if (line.contains("Mozilla/5.0")) {
                        userAgent = line.split("Mozilla/5.0")[1];
                        UserAgent ua = new UserAgent(userAgent);

                    }
                    countLine++;
                }
//                double shareYandex = (double) countYandex / countLine * 100;
//                double shareGoogle = (double) countGoogle / countLine * 100;
//                DecimalFormat df = new DecimalFormat("#.##");
//                System.out.println(countLine);


                System.out.println(st.getTotalTraffic());
                System.out.println(st.getRealTraffic());
                System.out.println(st.getBotTraffic());
                System.out.println(st.getFrequencyOS());
                System.out.println(st.getFrequencyBrowser());
                System.out.println(st.getExAddress());
                System.out.println(st.getNotFoundAddress());
                System.out.println(st.getCountBadRequest());
                System.out.println(st.getCountAvgUsers());
                System.out.println(st.getQuantityPerSecond());
                System.out.println(st.getSiteList());
                System.out.println(st.getMaxVisitUser());

            } catch (LineLongException | IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
