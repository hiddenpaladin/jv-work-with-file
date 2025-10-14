package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            int supply = 0;
            int buy = 0;
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] part = value.split(",");
                if (part[0].equals("supply")) {
                    supply += Integer.parseInt(part[1]);
                }
                if (part[0].equals("buy")) {
                    buy += Integer.parseInt(part[1]);
                }
                value = bufferedReader.readLine();
            }
            int result = supply - buy;
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
