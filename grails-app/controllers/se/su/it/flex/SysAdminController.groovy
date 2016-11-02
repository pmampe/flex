package se.su.it.flex

class SysAdminController {
  SysAdminService sysAdminService
 
  def index() {
  }

  def listAbsence() {
    log.info "listAbsence: ${params}"
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500]
    List<String> sortables = ["calendar.workDate", "comment", "employee.uid", "length", "start"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "emplyee.uid"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: Absence.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def listCalendar() {
    int max = 100
    String sort = "workDate"
    String order = "desc"
    [entries: Calendar.findAll([max: max, sort: sort, order: order])]
  }

  def listEmployee() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500]
    List<String> sortables = ["emailAddress", "firstName", "lastName", "uid"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "uid"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: Employee.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def sudo() {
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
