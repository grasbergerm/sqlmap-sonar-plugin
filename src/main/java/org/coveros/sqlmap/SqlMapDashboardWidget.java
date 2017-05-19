package org.coveros.sqlmap;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;

public class SqlMapDashboardWidget extends AbstractRubyTemplate implements RubyRailsWidget {

	public String getId() {
		return "sqlmap";
	}

	public String getTitle() {
		return "SQLMap Scan Results";
	}

	@Override
	protected String getTemplatePath() {
		return "/org/coveros/sqlmap/sonar-widget.html.erb";
	}

}