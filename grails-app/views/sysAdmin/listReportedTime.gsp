<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>List Reported Time</title>
  </head>
  <body>
    <div class="container">
      <g:render template="basics"/>

      <g:form name="listform" action="listReportedTime" class="form-horizontal">
        <div class="form-group">
          <label for="maxsize" class="control-label">Max # of Hits</label>
          <g:select name="maxsize" from="${maxSizes}" class="form-control" value="${max}"/>
        </div>

        <div class="form-group">
          <label for="sortby" class="control-label">Sort By</label>
          <g:select name="sortby" from="${sortables}" class="form-control" value="${sort}"/>
        </div>

        <div class="form-group">
          <label for="sortorder" class="control-label">Sort Order</label>
          <g:select name="sortorder" from="${sortOrders}" class="form-control" value="${order}"/>
        </div>

        <div class="form-group">
          <g:submitButton name="find" value="Find" class="btn btn-default" />
        </div>
      </g:form>

      <g:if test="${entries}">
        <table class="table table-condensed table-hover table-bordered table-striped">
          <thead><tr><th>WorkDate</th><th>UID</th><th>Start</th><th>End</th><th>Lunch</th><th>Delta</th><th>Total</th><th>Comment</th></tr></thead>
          <tbody>
            <g:each in="${entries}" var="entry">
              <tr><td><g:formatDate date="${entry.calendar.workDate}" format="yyyy-MM-dd"/></td><td><g:link action="sudo" id="${entry.employee.id}" title="Sudo">${entry.employee.uid}</g:link></td><td><g:formatNumber number="${entry.startHour}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${entry.startMinute}" type="number" minIntegerDigits="2" /></td><td><g:formatNumber number="${entry.endHour}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${entry.endMinute}" type="number" minIntegerDigits="2" /></td><td>${entry.lunchLength}</td><td>${entry.dailyDelta}</td><td><g:formatNumber number="${(int)(Math.abs(entry.dailyTotal/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(entry.dailyTotal)%60}" type="number" minIntegerDigits="2" /></td><td>${entry.comment}</td></tr>
            </g:each>
          </tbody>
        </table>
      </g:if>
    </div>
  </body>
</html>
