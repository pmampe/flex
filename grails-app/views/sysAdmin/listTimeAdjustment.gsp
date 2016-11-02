<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>List TimeAdjustment</title>
  </head>
  <body>
    <div class="container">
      <g:render template="basics"/>

      <g:form name="listform" action="listTimeAdjustment" class="form-horizontal">
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
          <thead><tr><th>Date</th><th>UID</th><th>Delta</th><th>Comment</th></tr></thead>
          <tbody>
            <g:each in="${entries}" var="entry">
              <tr><td><g:formatDate format="yyyy-MM-dd" date="${entry.calendar.workDate}"/></td><td>${entry.employee.uid}</td><td>${(entry.delta<0) ? '-':'+'}<g:formatNumber number="${(int)(Math.abs(entry.delta/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(entry.delta)%60}" type="number" minIntegerDigits="2" /></td></td><td>${entry.comment}</td></tr>
            </g:each>
          </tbody>
        </table>
      </g:if>
    </div>
  </body>
</html>
