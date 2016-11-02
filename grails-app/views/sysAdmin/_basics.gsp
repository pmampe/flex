<div class="row">
  <div class="col-sm-6">
    <ul>
      <li><strong>Calendar.count:</strong> <g:link action="listCalendar" title="Calendar Entries">${se.su.it.flex.Calendar.count()} st</g:link></li>
      <li><strong>Employee.count:</strong> <g:link action="listEmployee" title="Employees">${se.su.it.flex.Employee.count()} st</g:link></li>
      <li><strong>ReportedTime.count:</strong> <g:link action="listReportedTime" title="List Reported Time">${se.su.it.flex.ReportedTime.count()} st</g:link></li>
      <li><strong>Absence.count:</strong> <g:link action="listAbsence" title="List Absence">${se.su.it.flex.Absence.count()} st</g:link></li>
      <li><strong>TimeAdjustment.count:</strong> <g:link action="listTimeAdjustment" title="List Absence">${se.su.it.flex.TimeAdjustment.count()} st</g:link></li>
      <li><strong>WorkRate.count:</strong>  <g:link action="listWorkRate" title="List WorkRate">${se.su.it.flex.WorkRate.count()} st</g:link></li>
    </ul>
  </div>
  <div class="col-sm-6">
    <ul>
      <li><strong>Environment:</strong> ${grails.util.Environment.current.name}</li>
      <li><strong>App version:</strong> <g:meta name="info.app.version"/></li>
      <li><strong>Grails version:</strong> <g:meta name="info.app.grailsVersion"/></li>
      <li><strong>Groovy version:</strong> ${GroovySystem.getVersion()}</li>
      <li><strong>JVM version:</strong> ${System.getProperty('java.version')}</li>
    </ul>
  </div>
</div>
