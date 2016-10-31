<!doctype html>
<html>
  <head>
    <meta name="layout" content="flex"/>
    <title>Flex Sudo</title>
    <asset:javascript src="sysAdmin/index.js"/>
  </head>
  <body>
    <div class="container">
      <g:if test="${recentUsers}">
        <g:form name="sudoform" action="sudo" class="form-horizontal">
          <div class="form-group">
            <label for="otherUser" class="control-label">Användare</label>
            <g:select name="otherUser" from="${recentUsers}" optionKey="id" optionValue="name" class="form-control" />
          </div>
          <div class="form-group">
            <g:submitButton name="sudo" value="Sudo" class="btn btn-default" />
          </div>
        </g:form>
      </g:if>
    </div>
  </body>
</html>