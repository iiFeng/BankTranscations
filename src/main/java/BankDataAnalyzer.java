import java.time.Month;
import java.util.*;

/**
 * 统计分析类
 */
public class BankDataAnalyzer {
    private final List<BankData> list;

    public BankDataAnalyzer(List<BankData> list) {
        this.list = list;
    }

    /**
     * 消费总额
     *
     * @return total
     */
    public double totalPrice() {
        double total = 0.0d;
        for (BankData bankData : list) {
            if (bankData.getPrice() < 0) {
                total += bankData.getPrice();
            }
        }
        return total;
    }

    /**
     * 某个月有多少银行流水
     */
    public double transactionMonthPrice(Month month) {
        double total = 0.0d;
        for (BankData bankData : list) {
            if (bankData.getCreateTime().getMonth().equals(month)) {
                total += bankData.getPrice();
            }
        }
        return total;
    }

    /**
     * 前 10 大开销是什么，排序
     */
    public List<BankData> topTenTransaction() {
        List<BankData> topTenList = new ArrayList<>();

        Collections.sort(list, new PriceCompare());
        for (int i = 0; i < list.size(); i++) {
            if (list.size() > 10) {
                if (i == 10) {
                    break;
                }
                topTenList.add(list.get(i));

            } else {
                topTenList.add(list.get(i));
            }
        }
        return topTenList;
    }

    /**
     * 大部分钱花在哪一类上
     */
    public String mostPriceDescType() {
        HashMap<String, Integer> map = new HashMap<>();
        String description = "";
        for (BankData data : list) {
            if (data.getPrice() < 0) {
                String desc = data.getDescription();
                if (map.containsKey(desc)) {
                    map.put(desc, map.get(desc) + 1);
                } else {
                    map.put(desc, 1);
                }
            }
        }
        int maxCount = 0;
        for (Map.Entry<String, Integer> x : map.entrySet()) {
            if (x.getValue() > maxCount) {
                maxCount = x.getValue();
                description = x.getKey();
            }
        }
        return description;
    }

    /**
     * 查找消费金额最多的消费记录
     */
    public BankData minTransaction() {
        HashMap<Double, BankData> map = new HashMap<>();
        double total = 0.0d;
        for (BankData bt : list) {
            if (bt.getPrice() < total) {
                total = bt.getPrice();
                map.put(total, bt);
            }
        }
        //查找到最小值 然后返回整个实例对象
        return map.get(total);
    }

    /**
     * 查找挣最多的消费记录
     */
    public BankData maxTransaction() {
        HashMap<Double, BankData> map = new HashMap<>();
        double total = 0.0d;
        for (BankData bt : list) {
            if (bt.getPrice() > total) {
                total = bt.getPrice();
                map.put(total, bt);
            }
        }
        return map.get(total);
    }

    /**
     * 查找特定日期范围内最大的消费记录
     * todo:时间区间搜索，搜索年、月
     */
    public void datePeriodTransaction() {
        Double total = 0.0d;
    }

    /**
     * 损益总额是多少，是正还是负：使用接口实现类进行统计
     */
    public double summarizer(BankDataSummarizer bankDataSummarizer) {
        double total = 0d;
        for (BankData bankData : list) {
            total = bankDataSummarizer.summarize(total, bankData);
        }
        return total;
    }
}
