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

    private static String buildReportString(int supply, int buy) {
        int result = supply - buy;
        return "supply," + supply + LINE_SEPARATOR +
                "buy," + buy + LINE_SEPARATOR +
                "result," + result;
    }

    public String getStatistic(String fromFileName) {
        supplyTotal = 0;
        buyTotal = 0;
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
            return buildReportString(supplyTotal, buyTotal);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input file: " + fromFileName, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse number in file: " + fromFileName, e);
        }
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(buildReportString(supplyTotal, buyTotal));
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + toFileName, e);
        }
    }
}