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
        <h1>${employee.lastName}, ${employee.firstName} (${employee.uid})</h1><br/>
        <g:form name="calendarform" action="index" class="form-horizontal">
          <div class="form-group">
            <label for="report_day" class="control-label">Rapporteringsdag</label>
            <g:select name="report_day" from="${reportableDays}" optionKey="id" optionValue="name" class="form-control" value="${calendar?.id}"/>
          </div>

          <div class="form-group">
            <g:submitButton name="changeday" value="Byt Dag" class="btn btn-default" />
          </div>
        </g:form>
        <g:set var="style" value="${(dailyDelta<0)?raw(' style="color:red;"'):''}"/>
        DailyDelta: <strong${style}>${(dailyDelta<0) ? "-":"+"}<g:formatNumber number="${(int)(Math.abs(dailyDelta/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(dailyDelta)%60}" type="number" minIntegerDigits="2" /></strong><br/>
        <g:set var="style2" value="${(adjustment<0)?raw(' style="color:red;"'):''}"/>
        Adjustments: <strong${style2}>${(adjustment<0) ? "-":"+"}<g:formatNumber number="${(int)(Math.abs(adjustment/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(adjustment)%60}" type="number" minIntegerDigits="2" /></strong><br/>
        <g:set var="style3" value="${(sum<0)?raw(' style="color:red;"'):''}"/>
        Summa: <strong${style3}>${(sum<0) ? "-":"+"}<g:formatNumber number="${(int)(Math.abs(sum/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(sum)%60}" type="number" minIntegerDigits="2" /></strong><br/>
        <br/><br/>
        Rapporteringsdag: <strong><g:formatDate date="${calendar?.workDate}" format="yyyy-MM-dd"/></strong><br/>
        Normtid: <strong><g:formatNumber number="${(int)(normTime/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${normTime%60}" type="number" minIntegerDigits="2" /></strong><br/>
      </g:if>  
    </div>
  </body>
</html>
