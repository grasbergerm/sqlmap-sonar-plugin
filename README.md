Build with 'mvn clean package', add to $SONARQUBE_HOME/extensions/plugins,
 
restart Sonarqube, then add the widget to the dashboard to use.



Required sonar properties are:

    sonar.sqlmap.reportPath - path of the html report created from SQMReportGenerator.py

    sonar.sqlmap.reportLink - link to the report in jenkins or other CI server
