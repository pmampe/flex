<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Calendar List</title>
    <asset:javascript src="date/list.js"/>
  </head>
  <body>
    <div class="container">
      <g:if test="${days}">
        <table class="table table-condensed table-hover table-bordered table-striped">
          <thead><tr><th>Datum</th><th>Start</th><th>End</th><th>Length</th><th>Description</th></tr></thead>
          <tbody>
              <g:each in="${days}" var="entry">
                <tr><td>${entry.workDate.format('yyyy-MM-dd')}</td><td>${entry.mandatoryStart}</td><td>${entry.mandatoryEnd}</td><td>${entry.fullTime}</td><td>${entry.description}</td></tr>
              </g:each>
          </tbody>
        </table>
      </g:if>  
    </div>
  </body>
</html>
