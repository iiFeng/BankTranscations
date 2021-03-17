import java.util.Comparator;

public class PriceCompare implements Comparator<BankData> {
    //使用降序
    @Override
    public int compare(BankData o1, BankData o2) {
        if (o1.getPrice() == o2.getPrice()) {
            return 0;
        }
        return o1.getPrice() - o2.getPrice() > 0 ? 1 : -1;
    }
}
