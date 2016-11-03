<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Flex Dashboard : ${employee?.uid}</title>
    <asset:javascript src="dashboard/index.js"/>
  </head>
  <body>
    <div class="container">
      <div class="row">
        <div class="col-sm-4">Datum</div>
        <div class="col-sm-8">${reportedTime.calendar.workDate.format('yyyy-MM-dd')}</div>
      </div>
      <div class="row">
        <div class="col-sm-4">Började</div>
        <div class="col-sm-8"><g:formatNumber number="${(int)(reportedTime.startMinute/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.startMinute%60}" type="number" minIntegerDigits="2" /></div>
      </div>
      <div class="row">
        <div class="col-sm-4">Lunch</div>
        <div class="col-sm-8">${reportedTime.lunchLength} min</div>
      </div>
      <div class="row">
        <div class="col-sm-4">Slutade</div>
        <div class="col-sm-8"><g:formatNumber number="${(int)(reportedTime.endMinute/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.endMinute%60}" type="number" minIntegerDigits="2" /></div>
      </div>
      <div class="row">
        <div class="col-sm-4">Totalt</div>
        <div class="col-sm-8"><g:formatNumber number="${(int)(reportedTime.dailyTotal/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${reportedTime.dailyTotal%60}" type="number" minIntegerDigits="2" /></div>
      </div>
      <div class="row">
        <div class="col-sm-4">Normtid</div>
        <div class="col-sm-8"><g:formatNumber number="${(int)(normTime/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${normTime%60}" type="number" minIntegerDigits="2" /></div>
      </div>
      <div class="row">
        <div class="col-sm-4">Delta</div>
        <div class="col-sm-8">${reportedTime.dailyDelta}</div>
      </div>
      <div class="row">
        <div class="col-sm-4">Flex Heldag</div>
        <div class="col-sm-8"><g:formatBoolean boolean="${reportedTime.absentAllDay}" true="Ja" false="Nej"/></div>
      </div>
      <div class="row">
        <div class="col-sm-4">Kommentar</div>
        <div class="col-sm-8">${reportedTime.comment}</div>
      </div>
      <g:if test="${absences}">
        <strong>Frånvaro:</strong>
        <g:each in="${absences}" var="entry">
          <div class="row">
            <div class="col-sm-4"><g:formatNumber number="${(int)(entry.start/60)}" type="number" minIntegerDigits="2" />:<g:formatNumber number="${entry.start%60}" type="number" minIntegerDigits="2" /></div>
            <div class="col-sm-4">${entry.length} min</div>
            <div class="col-sm-4">${entry.comment}</div>
          </div>
        </g:each>
      </g:if>
      <g:if test="${timeAdjustments}">
        <strong>Justeringar:</strong>
        <g:each in="${timeAdjustments}" var="entry">
          <div class="row">
            <div class="col-sm-6">${entry.delta} min</div>
            <div class="col-sm-6">${entry.comment}</div>
          </div>
        </g:each>
      </g:if>
    </div>
  </body>
</html>
