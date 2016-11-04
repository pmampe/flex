<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Flex Sysadmin</title>
    <asset:javascript src="sysAdmin/index.js"/>
  </head>
  <body>
    <div class="container">
      <g:render template="basics"/>

      <g:form name="inactiveform" action="index" class="form-horizontal">
        <div class="form-group">
          <label for="maxsize" class="control-label">Max # of Hits</label>
          <g:select name="maxsize" from="${maxSizes}" class="form-control" value="${max}"/>
        </div>

        <div class="form-group">
          <g:submitButton name="find" value="Find Inactive Flexers" class="btn btn-default" />
        </div>
      </g:form>

      <g:if test="${entries}">
        <table class="table table-condensed table-hover table-bordered table-striped">
          <thead><tr><th>Date</th><th>UID</th></tr></thead>
          <tbody>
            <g:each in="${entries}" var="entry">
              <tr><td>${entry.lastDate}</td><td><g:link action="sudo" id="${entry.employee.id}" title="Sudo">${entry.employee.uid}</g:link></td></tr>
            </g:each>
          </tbody>
        </table>
      </g:if>
    </div>
  </body>
</html>
