import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankDataAnalyzerTest {
    @Test
    public void shouldParseOneCorrectLine() {
        final String line = "02-02-2017,-4000,Rent";

        BankDataCSVParser bankDataCSVParser = new BankDataCSVParser();
        BankData result = bankDataCSVParser.parseLine(line);

        BankData excepted = new BankData(LocalDate.of(2017, Month.FEBRUARY, 02), -4000, "Rent");
        double tolerance = 0.0d;
        Assert.assertEquals(excepted.getCreateTime(), result.getCreateTime());
        Assert.assertEquals(excepted.getPrice(), result.getPrice(), tolerance);
        Assert.assertEquals(excepted.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseLines() {
        List<String> lines = new ArrayList<>();
        lines.add("02-02-2017,-4000,Rent");
        lines.add("03-02-2017,3000,Tesco");
        lines.add("05-02-2017,-30,Cinema");
        BankDataCSVParser bankDataCSVParser = new BankDataCSVParser();
        List<BankData> result = bankDataCSVParser.parseLines(lines);

        List<BankData> expected = new ArrayList<>();
        BankData bankData = new BankData(LocalDate.of(2017, Month.FEBRUARY, 02), -4000, "Rent");
        expected.add(bankData);
        bankData = new BankData(LocalDate.of(2017, Month.FEBRUARY, 03), 3000, "Tesco");
        expected.add(bankData);
        bankData = new BankData(LocalDate.of(2017, Month.FEBRUARY, 05), -30, "Cinema");
        expected.add(bankData);
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i).getCreateTime(), result.get(i).getCreateTime());
            Assert.assertEquals(expected.get(i).getPrice(), result.get(i).getPrice(), 0.0d);
            Assert.assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
        }
    }

}
