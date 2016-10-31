<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Flex Sysadmin</title>
    <asset:javascript src="sysAdmin/index.js"/>
  </head>
  <body>
    <div class="container">
      <ul>
        <li><strong>Environment:</strong> ${grails.util.Environment.current.name}</li>
        <li><strong>App version:</strong> <g:meta name="info.app.version"/></li>
        <li><strong>Grails version:</strong> <g:meta name="info.app.grailsVersion"/></li>
        <li><strong>Groovy version:</strong> ${GroovySystem.getVersion()}</li>
        <li><strong>JVM version:</strong> ${System.getProperty('java.version')}</li>
        <li><strong>Calendar.count:</strong> ${se.su.it.flex.Calendar.count()} st</li>
        <li><strong>Employee.count:</strong> ${se.su.it.flex.Employee.count()} st</li>
        <li><strong>ReportedTime.count:</strong> ${se.su.it.flex.ReportedTime.count()} st</li>
        <li><strong>Absence.count:</strong> ${se.su.it.flex.Absence.count()} st</li>
        <li><strong>TimeAdjustment.count:</strong> ${se.su.it.flex.TimeAdjustment.count()} st</li>
        <li><strong>WorkRate.count:</strong> ${se.su.it.flex.WorkRate.count()} st</li>
      </ul>
    </div>
  </body>
</html>
