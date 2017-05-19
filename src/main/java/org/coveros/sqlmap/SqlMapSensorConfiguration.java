package org.coveros.sqlmap;

import org.sonar.api.BatchExtension;
import org.sonar.api.config.Settings;

public class SqlMapSensorConfiguration implements BatchExtension {

	private final Settings settings;

	public SqlMapSensorConfiguration(Settings settings) {
		this.settings = settings;
	}

	public String getReportPath() {
		return this.settings.getString("sonar.sqlmap.reportPath");
	}

	public String getReportLink() {
		return this.settings.getString("sonar.sqlmap.reportLink");
	}

}
