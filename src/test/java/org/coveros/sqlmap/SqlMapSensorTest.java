package org.coveros.sqlmap;

import org.coveros.sqlmap.SqlMapSensor;
import org.coveros.sqlmap.SqlMapSensorConfiguration;
import org.junit.Test;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;

import static org.junit.Assert.*;

import java.util.Scanner;

public class SqlMapSensorTest {
	@Test
	public void testPluginExecutingNoProperties() {
		Settings settings = new Settings();
		Project project = new Project("test project").setSettings(settings);
		SqlMapSensorConfiguration configuration = new SqlMapSensorConfiguration(settings);
		SqlMapSensor sensor = new SqlMapSensor(configuration);
		assertFalse(sensor.shouldExecuteOnProject(project));
	}

	@Test
	public void testPluginExecutingCorrectProperties() {
		Settings settings = new Settings().appendProperty("sonar.sqlmap.reportLink", "test")
				.appendProperty("sonar.sqlmap.reportPath", "test");
		Project project = new Project("test project").setSettings(settings);
		SqlMapSensorConfiguration configuration = new SqlMapSensorConfiguration(settings);
		SqlMapSensor sensor = new SqlMapSensor(configuration);
		assertTrue(sensor.shouldExecuteOnProject(project));
	}
	
	@Test
	public void testPluginExecutingPartialCorrectProperties() {
		Settings settings = new Settings().appendProperty("sonar.sqlmap.reportLink", "test");
		Project project = new Project("test project").setSettings(settings);
		SqlMapSensorConfiguration configuration = new SqlMapSensorConfiguration(settings);
		SqlMapSensor sensor = new SqlMapSensor(configuration);
		assertFalse(sensor.shouldExecuteOnProject(project));
	}

	@Test
	public void testPluginSensorHtmlParserSqlInjectionFound() {
		Scanner scan = new Scanner("SQLMap can find injectable parameters !");
		Settings settings = new Settings();
		SqlMapSensorConfiguration configuration = new SqlMapSensorConfiguration(settings);
		SqlMapSensor sensor = new SqlMapSensor(configuration);
		assertTrue(sensor.parseHtmlReport(scan).equals("SQLMap found injectable parameters."));
	}
	
	@Test
	public void testPluginSensorHtmlParserSqlInjectionNotFound() {
		Scanner scan = new Scanner("SQLMap cannot find injectable parameters !");
		Settings settings = new Settings();
		SqlMapSensorConfiguration configuration = new SqlMapSensorConfiguration(settings);
		SqlMapSensor sensor = new SqlMapSensor(configuration);
		assertTrue(sensor.parseHtmlReport(scan).equals("SQLMap cannot find injectable parameters."));
	}
	
}
