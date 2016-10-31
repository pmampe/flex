<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Flex Dashboard : ${employee?.uid}</title>
    <asset:javascript src="dashboard/index.js"/>
  </head>
  <body>
    <div class="container">
      <g:if test="${employee}">
        <strong>${employee.lastName}, ${employee.firstName} (${employee.uid}): ${(dailyDelta<0) ? "-":"+"} <g:formatNumber number="${(int)(Math.abs(dailyDelta/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(dailyDelta)%60}" type="number" minIntegerDigits="2" /></strong>
        <g:if test="${reportedTimes}">
          <table class="table table-condensed table-hover table-bordered table-striped">
            <thead><tr><th>Datum</th><th>Kom</th><th>Gick</th><th>Lunch</th><th>Delta</th><th>Total</th></tr></thead>
            <tbody>
              <g:each in="${reportedTimes}" var="reportedTime">
                <tr><td>${reportedTime.calendar.workDate.format('yyyy-MM-dd')}</td><td><g:formatNumber number="${reportedTime.startHour}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.startMinute}" type="number" minIntegerDigits="2" /></td><td><g:formatNumber number="${reportedTime.endHour}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.endMinute}" type="number" minIntegerDigits="2" /></td><td>${reportedTime.lunchLength}</td><td>${reportedTime.dailyDelta}</td><td><g:formatNumber number="${(int)(reportedTime.dailyTotal/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.dailyTotal%60}" type="number" minIntegerDigits="2" /></td></tr>
              </g:each>
            </tbody>
          </table>
        </g:if>  
      </g:if>  
    </div>
  </body>
</html>
