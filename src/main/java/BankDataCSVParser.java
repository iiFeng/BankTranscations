import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankDataCSVParser {
    List<Notification> list = new ArrayList<>();

    public BankData parseLine(String line) {
        String[] columns = line.split(",");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(columns[0], formatter);
        double price = Double.parseDouble(columns[1]);
        String description = columns[2];
        BankData bankData = new BankData(localDate, price, description);

        Notification notification = getValidate(bankData);
        if (notification.hasErrors()) {
            list.add(notification);
        }
        return bankData;
    }

    public List<BankData> parseLines(List<String> lines) {
        List<BankData> list = new ArrayList<>();
        for (String line : lines) {
            list.add(parseLine(line));
        }
        return list;
    }

    public Notification getValidate(BankData bankData) {
        return bankData.validate();
    }

    public List<Notification> notificationsList() {
        return new ArrayList<>();
    }
}
