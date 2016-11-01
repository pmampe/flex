<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Calendar Edit</title>
    <asset:javascript src="date/list.js"/>
  </head>
  <body>
    <div class="container">
      <g:form name="editForm" class="form-horizontal" action="edit" accept-charset="utf8" method='POST'>
        <g:hiddenField name="id" value="${calendar.id}"/>
        <div class="form-group">
          <label class="control-label col-sm-2" for="date">Datum:</label>
          <div class="col-sm-10">
            <g:textField name="date" class="form-control" value="${calendar.workDate.format('yyyy-MM-dd')}" disabled="${true}"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-2" for="dow">Veckodag:</label>
          <div class="col-sm-10">
            <g:textField name="dow" class="form-control" value="${dayOfWeek}" disabled="${true}"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-2" for="fullTime">Arbetstid:</label>
          <div class="col-sm-10"> 
            <g:select name="fullTime" from="${commonTimes}" class="form-control" optionKey="id" optionValue="name" value="${calendar.fullTime}"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-2" for="startTime">Senaste Starttid:</label>
          <div class="col-sm-10"> 
            <g:select name="startTime" from="${startTimes}" class="form-control" value="${calendar.mandatoryStart}"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-2" for="endTime">Tidigaste Sluttid:</label>
          <div class="col-sm-10"> 
            <g:select name="endTime" from="${endTimes}" class="form-control" value="${calendar.mandatoryEnd}"/>
          </div>
        </div>

        <div class="form-group">
          <label class="control-label col-sm-2" for="description">Beskrivning:</label>
          <div class="col-sm-10">
            <g:textField name="description" class="form-control" value="${calendar.description}"/>
          </div>
        </div>

        <div class="form-group"> 
          <div class="col-sm-offset-2 col-sm-10">
            <g:submitButton name="update" value="Uppdatera" class="btn btn-default"/>
          </div>
        </div>
      </g:form>
    </div>
  </body>
</html>
