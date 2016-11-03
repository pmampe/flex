<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>List Employee</title>
  </head>
  <body>
    <div class="container">
      <g:render template="basics"/>

      <g:form name="listform" action="listEmployee" class="form-horizontal">
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
          <thead><tr><th>Uid</th><th>First Name</th><th>Last Name</th><th>Email Address</th></tr></thead>
          <tbody>
            <g:each in="${entries}" var="entry">
              <tr><td><g:link action="sudo" id="${entry.id}" title="Sudo">${entry.uid}</g:link></td><td>${entry.firstName}</td><td>${entry.lastName}</td><td>${entry.emailAddress}</td></tr>
            </g:each>
          </tbody>
        </table>
      </g:if>
    </div>
  </body>
</html>
