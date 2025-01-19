import java.io.*;
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

                int countLine = 0;
                int minLine = Integer.MAX_VALUE;
                int maxLine = 0;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) throw new LineLongException("Длина строки превышает 1024 символа");
                    if (length > maxLine) {
                        maxLine = length;
                    }
                    if (length < minLine) {
                        minLine = length;
                    }
                    countLine++;
                }
                System.out.println(countLine);
                System.out.println(maxLine);
                System.out.println(minLine);

            } catch (LineLongException | IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
