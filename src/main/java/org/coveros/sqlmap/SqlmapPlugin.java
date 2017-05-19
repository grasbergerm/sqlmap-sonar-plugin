package org.coveros.sqlmap;

import java.util.Arrays;
import java.util.List;
import org.sonar.api.Extension;
import org.sonar.api.SonarPlugin;

public class SqlmapPlugin extends SonarPlugin {
	public List<Class<? extends Extension>> getExtensions() {
		return Arrays.asList(SqlMapMetrics.class, SqlMapSensor.class, SqlMapDashboardWidget.class,
				SqlMapSensorConfiguration.class);
	}
}