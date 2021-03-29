package exporter;

/**
 * html 格式的实现接口类
 */
public class HtmlExporter implements Exporter {
    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "sum: " + summaryStatistics.getSum();
        result += ",max: " + summaryStatistics.getMax();
        result += ",min: " + summaryStatistics.getMin();
        result += ",verage: " + summaryStatistics.getAverage();
        return result;
    }
}
