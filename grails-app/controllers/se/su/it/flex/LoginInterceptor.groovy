package se.su.it.flex


import grails.util.Environment
import groovy.transform.CompileStatic

@CompileStatic
class LoginInterceptor {
  int order = 30
  
  LoginInterceptor() {
    matchAll().excludes(controller: 'public')
  }
  
  boolean before() { 
    String eppn = (request.getAttribute('eppn') as String) ?: ((Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) ? 'supersture@su.se' : null)
    String affiliations = (request.getAttribute("affiliation") as String) ?:  ((Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) ? 'employee' : null)
    if (!eppn || !eppn.endsWith("@su.se") || !affiliations || !affiliations.contains('employee')) {
      /** If no eppn available we set role to public and return */
      session.setAttribute('role', Role.PUBLIC)
      return true
    }
    if(session.getAttribute('uid')) {
      return true
    }
    if(['mano3567@su.se','pama7242@su.se','jqvar@su.se','supersture@su.se'].contains(eppn)) {
      session.setAttribute('role', Role.SYSADMIN)
    } else {
      session.setAttribute('role', Role.EMPLOYEE)
    }
    session.setAttribute('uid', eppn)
    String entitlement = request.getAttribute('entitlement') // urn:mace:swami.se:gmai:
    return true
  }

  boolean after() {
   return true
  }

  void afterView() {
    // no-op
  }
}
