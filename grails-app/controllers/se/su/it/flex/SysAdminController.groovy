package se.su.it.flex

class SysAdminController {
  SysAdminService sysAdminService
 
  def index() {
  }

  def sudo() {
    log.error "sudo ${params}"
    if(params.sudo && params.long('otherUser') && params.role) {
      Employee employee = Employee.get(params.long('otherUser'))
      if(employee) {
        if(!session.getAttribute('realuser') && session.getAttribute('realrole')) {
          session.setAttribute('realuser', session.getAttribute('uid'))
          session.setAttribute('realrole', session.getAttribute('realrole'))
        }
        session.setAttribute('uid', employee.uid)
        session.setAttribute('role', params.role)
        return redirect(controller: 'dashboard', action: 'index')
      }
    }
    List<Employee> recentUsers = sysAdminService.findRecentUsers(10)
    
    [recentUsers: recentUsers, roles: se.su.it.flex.Role.values()]
  }
}
