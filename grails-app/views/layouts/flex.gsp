<!DOCTYPE html>
<html lang="sv">
  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><g:layoutTitle default="Flex"/></title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <asset:javascript src="application.js"/>
    <g:layoutHead/>
  </head>
  <body>
    <div class="container">
      <nav class="navbar navbar-default">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="https://flex.su.se/">Flex</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Funktioner <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><g:link controller="date" action="list" title="View Calendar">Calendar</g:link></li>
                  <li role="separator" class="divider"></li>
                  <li><g:link controller="sysAdmin" action="sudo" title="SuDo">Sudo</g:link></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Andra System <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="http://www.su.se/" title="su.se">Stockholms Universitet</a></li>
                  <li><a href="https://hr.su.se/" title="primula">Primula</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a href="http://www.dn.se/" title="dn">Dagens Nyheter</a></li>
                </ul>
              </li>
            </ul>
            <div class="pull-right">
              <p>${session.uid} / ${session.role}</p><g:if test="${session.realuser}">(${session.realuser} / ${session.realrole})<p></p></g:if>
            </div>
          </div><!--/.nav-collapse -->
        </div>
      </nav>
      <div>
        <g:layoutBody/>
      </div>
    </div>
  </body>
</html>
