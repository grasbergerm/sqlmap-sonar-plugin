package org.coveros.sqlmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;

public class SqlMapSensor implements Sensor {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private SqlMapSensorConfiguration configuration;

	public SqlMapSensor(SqlMapSensorConfiguration configuration) {
		this.configuration = configuration;
	}

	// Only execute when correct properties exist
	public boolean shouldExecuteOnProject(Project project) {
		if (project.getSettings().getProperties().containsKey("sonar.sqlmap.reportLink")
				&& project.getSettings().getProperties().containsKey("sonar.sqlmap.reportPath"))
			return true;
		return false;
	}

	public String toString() {
		return "SQLMapSensor";
	}

	public void analyse(Project project, SensorContext sensorContext) {
		File file = new File(configuration.getReportPath());
		try {
			Scanner scan = new Scanner(file);
			String result = parseHtmlReport(scan);
			
			// Save result and result link
			Measure measure_res = new Measure(SqlMapMetrics.SQLMAP_RESULTS, result);
			sensorContext.saveMeasure(measure_res);
			Measure measure_res_link = new Measure(SqlMapMetrics.SQLMAP_LINK, configuration.getReportLink());
			sensorContext.saveMeasure(measure_res_link);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public String parseHtmlReport(Scanner scanner) {
		String result = "SQLMap cannot find injectable parameters.";
		// HTML report only has one line, check report for injectable parameters
		if (scanner.nextLine().contains("SQLMap can find injectable parameters !"))
			result = "SQLMap found injectable parameters.";
		scanner.close();
		return result;
	}

}