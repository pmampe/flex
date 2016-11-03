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
        <g:set var="style" value="${(dailyDelta<0)?raw(' style="color:red;"'):''}"/>
        DailyDelta: <strong${style}>${(dailyDelta<0) ? "-":"+"}<g:formatNumber number="${(int)(Math.abs(dailyDelta/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(dailyDelta)%60}" type="number" minIntegerDigits="2" /></strong><br/>
        <g:set var="style2" value="${(adjustment<0)?raw(' style="color:red;"'):''}"/>
        Adjustments: <strong${style2}>${(adjustment<0) ? "-":"+"}<g:formatNumber number="${(int)(Math.abs(adjustment/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(adjustment)%60}" type="number" minIntegerDigits="2" /></strong><br/>
        <g:set var="style3" value="${(sum<0)?raw(' style="color:red;"'):''}"/>
        Summa: <strong${style3}>${(sum<0) ? "-":"+"}<g:formatNumber number="${(int)(Math.abs(sum/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(sum)%60}" type="number" minIntegerDigits="2" /></strong><br/>
        <g:if test="${reportedTimes}">
          <p><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#reported_times">De senaste ${reportedTimes.size()} st rapporterade dagarna</button></p>
          <div id="reported_times" class="collapse">
            <table class="table table-condensed table-hover table-bordered table-striped">
              <thead><tr><th>Datum</th><th>Kom</th><th>Gick</th><th>Lunch</th><th>Delta</th><th>Total</th><th>Heldag</th><th>Comment</th></tr></thead>
              <tbody>
                <g:each in="${reportedTimes}" var="reportedTime">
                  <tr><td><g:link action="show" id="${reportedTime.calendar.id}" title="Show">${reportedTime.calendar.workDate.format('yyyy-MM-dd')}</g:link></td><td><g:formatNumber number="${(int)(reportedTime.startMinute/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.startMinute%60}" type="number" minIntegerDigits="2" /></td><td><g:formatNumber number="${(int)(reportedTime.endMinute/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.endMinute%60}" type="number" minIntegerDigits="2" /></td><td>${reportedTime.lunchLength}</td><td>${reportedTime.dailyDelta}</td><td><g:formatNumber number="${(int)(reportedTime.dailyTotal/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.dailyTotal%60}" type="number" minIntegerDigits="2" /></td><td><g:formatBoolean boolean="${reportedTime.absentAllDay}" true="Ja" false="Nej"/></td><td>${reportedTime.comment}</td></tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </g:if>
        <g:else>
          <strong>Ingen tid rapporterad hittills!</strong>
        </g:else>
        <g:if test="${absences}">
          <p><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#absences">${absences.size()} st rapporterad frånvaron</button></p>
          <div id="absences" class="collapse">
            <table class="table table-condensed table-hover table-bordered table-striped">
              <thead><tr><th>Datum</th><th>Gick</th><th>Tid</th><th>Comment</th></tr></thead>
              <tbody>
                <g:each in="${absences}" var="absence">
                  <tr><td>${absence.calendar.workDate.format('yyyy-MM-dd')}</td><td><g:formatNumber number="${(int)(Math.abs(absence.start/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(absence.start%60)}" type="number" minIntegerDigits="2" /></td><td><g:formatNumber number="${(int)(Math.abs(absence.length/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(absence.length%60)}" type="number" minIntegerDigits="2" /></td><td>${absence.comment}</td></tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </g:if>
        <g:if test="${timeAdjustments}">
          <p><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#timeAdjustments">${timeAdjustments.size()} st justeringar av saldo</button></p>
          <div id="timeAdjustments" class="collapse">
            <table class="table table-condensed table-hover table-bordered table-striped">
              <thead><tr><th>Datum</th><th>Tid</th><th>Comment</th></tr></thead>
              <tbody>
                <g:each in="${timeAdjustments}" var="timeAdjustment">
                  <tr><td>${timeAdjustment.calendar.workDate.format('yyyy-MM-dd')}</td><td ${(timeAdjustment.delta<0) ? " style=color:red;":''}>${(timeAdjustment.delta<0) ? '-':'+'}<g:formatNumber number="${(int)(Math.abs(timeAdjustment.delta/60))}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${Math.abs(timeAdjustment.delta%60)}" type="number" minIntegerDigits="2" /></td><td>${timeAdjustment.comment}</td></tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </g:if>
        <g:if test="${workRates}">
          <p><button type="button" class="btn btn-info" data-toggle="collapse" data-target="#workRates">${workRates.size()} st inställningar för deltid</button></p>
          <div id="workRates" class="collapse">
            <p>Tips: Heltid är default, så det behöver man inte göra någon inställning för</p>
            <table class="table table-condensed table-hover table-bordered table-striped">
              <thead><tr><th>Start</th><th>Slut</th><th>Comment</th><th>Rate</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th></tr></thead>
              <tbody>
                <g:each in="${workRates}" var="workRate">
                  <tr><td>${workRate.startDate.format('yyyy-MM-dd')}</td><td>${workRate.endDate.format('yyyy-MM-dd')}</td><td>${workRate.comment}</td><td>${workRate.rate/100}</td><td>${workRate.rateMonday/100}</td><td>${workRate.rateTuesday/100}</td><td>${workRate.rateWednesday/100}</td><td>${workRate.rateThursday/100}</td><td>${workRate.rateFriday/100}</td></tr>
                </g:each>
              </tbody>
            </table>
          </div>
        </g:if>
      </g:if>  
    </div>
  </body>
</html>
