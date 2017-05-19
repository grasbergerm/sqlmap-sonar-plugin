package org.coveros.sqlmap;

import java.util.Arrays;
import java.util.List;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public class SqlMapMetrics implements Metrics {

	public static final Metric SQLMAP_RESULTS = new Metric.Builder("sqlmap_results", "sqlmap results",
			Metric.ValueType.STRING).setDescription("The results of the sqlmap scan").setQualitative(false)
					.setDomain(CoreMetrics.DOMAIN_GENERAL).create();

	public static final Metric SQLMAP_LINK = new Metric.Builder("sqlmap_results_link", "sqlmap results link",
			Metric.ValueType.STRING).setDescription("A link to the results of the sqlmap scan").setQualitative(false)
					.setDomain(CoreMetrics.DOMAIN_GENERAL).create();

	public List<Metric> getMetrics() {
		return Arrays.asList(SQLMAP_RESULTS, SQLMAP_LINK);
	}
}
