<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Calendar List</title>
    <asset:javascript src="date/list.js"/>
  </head>
  <body>
    <div class="container">
      <g:link action="populateYear" id="${1+latestYear}" title="populate new year" class="btn btn-default">Populera kalender för ${1+latestYear}</g:link><br/>
      <g:if test="${years}">
        <g:each in="${years}" var="year">
          <g:link action="list" id="${year}" title="${year}" class="btn ${(params.long('id')==year) ? 'btn-primary':'btn-default'}">${year}</g:link>
        </g:each>
      </g:if>
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
