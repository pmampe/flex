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
            <label for="otherUser" class="control-label">User</label>
            <g:select name="otherUser" from="${recentUsers}" class="form-control" optionKey="id" noSelection="['':'Choose user']"/>
          </div>
          <div class="form-group">
            <label for="role" class="control-label">Role</label>
            <g:select name="role" from="${roles}" class="form-control" noSelection="['':'Choose role']"/>
          </div>
          <div class="form-group">
            <g:submitButton name="sudo" value="Sudo" class="btn btn-default" />
          </div>
        </g:form>
      </g:if>
    </div>
  </body>
</html>