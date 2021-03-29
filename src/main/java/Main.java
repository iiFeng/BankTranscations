import exporter.Exporter;
import exporter.HtmlExporter;
import exporter.SummaryStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/total.csv"));
        BankDataCSVParser bankDataCSVParser = new BankDataCSVParser();
        List<BankData> list = bankDataCSVParser.parseLines(lines);


        BankDataAnalyzer bankDataAnalyzer = new BankDataAnalyzer(list);
        List<Notification> notificationList = bankDataCSVParser.notificationsList();
        if (notificationList.isEmpty()) {
            System.out.println(bankDataAnalyzer.topTenTransaction());
        } else {
            for (Notification n : notificationList) {
                n.errorMessage();
            }
        }

        Exporter exporter = new HtmlExporter();
        double allTotal = bankDataAnalyzer.summarizer((total, bankData) ->
                total + bankData.getPrice());
        double max = bankDataAnalyzer.maxTransaction().getPrice();
        double min = bankDataAnalyzer.minTransaction().getPrice();
        double average = allTotal / list.size();
        System.out.println("html: "+exporter.export(new SummaryStatistics(allTotal, max, min, average)));
    }
}
