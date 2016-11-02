<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>List Calendar</title>
  </head>
  <body>
    <div class="container">
      <g:render template="basics"/>
      <g:if test="${entries}">
        <table class="table table-condensed table-hover table-bordered table-striped">
          <thead><tr><th>Datum</th><th>Start</th><th>End</th><th>Length</th><th>Description</th></tr></thead>
          <tbody>
          <g:each in="${entries}" var="entry">
            <tr><td>${entry.workDate.format('yyyy-MM-dd')}</td><td>${entry.mandatoryStart}</td><td>${entry.mandatoryEnd}</td><td>${entry.fullTime}</td><td>${entry.description}</td></tr>
          </g:each>
          </tbody>
        </table>
      </g:if>
    </div>
  </body>
</html>
