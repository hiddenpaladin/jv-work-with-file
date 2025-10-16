package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    int supplyTotal = 0;
    int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readTotals(fromFileName);
        String report = buildReport(totals[0],totals[1]);
        writeToFile(toFileName,report);
         //
         //
        private int[] readTotals(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] part = value.split(DELIMITER);
                if (part.length != 2) {
                    throw new RuntimeException("Malformed line: " + value);
                }
                if (part[0].equals(SUPPLY)) {
                    supplyTotal += Integer.parseInt(part[1]);
                }
                if (part[0].equals(BUY)) {
                    buyTotal += Integer.parseInt(part[1]);
                }
                value = reader.readLine();
            }
            return new int[] {supplyTotal,buyTotal};
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input file: " + fromFileName, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse number in file: " + fromFileName, e);
        }
        }
        private String buildReportString(int suply,int buy) {
            int result = supply - buy;
            return "supply," + supply + LINE_SEPARATOR +
                    "buy," + buy + LINE_SEPARATOR +
                    "result," + result;
        }

        private void writeReport(String toFileName, String content) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write(content);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write to file: " + toFileName, e);
            }
        }
    }
}