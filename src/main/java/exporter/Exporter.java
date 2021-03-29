package exporter;

/**
 * 不同格式的汇总统计报告
 */
public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
