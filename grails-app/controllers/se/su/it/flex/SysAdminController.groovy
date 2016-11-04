package se.su.it.flex

class SysAdminController {
  SysAdminService sysAdminService
 
  def index() {
    int max = (params.int('maxsize')) ?: 100
    List<Integer> maxSizes = [25, 100, 250, 1000]
    List<Expando> entries = (params.find) ? sysAdminService.findInactiveFlexers(max): []
    [entries: entries, max: max, maxSizes: maxSizes]
  }

  def listAbsence() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500, 10000]
    List<String> sortables = ["calendar.workDate", "comment", "employee.uid", "length", "start"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "emplyee.uid"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: Absence.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def listCalendar() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500, 10000]
    List<String> sortables = ["description", "fullTime", "mandatoryEnd", "mandatoryStart", "workDate"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "workDate"
    String order = (params.sortorder?.trim()) ?: "desc"
    [entries: Calendar.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def listEmployee() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500, 10000]
    List<String> sortables = ["emailAddress", "firstName", "lastName", "uid"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "uid"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: Employee.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def listReportedTime() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500, 10000]
    List<String> sortables = ["absentAllDay", "calendar.workDate", "comment", "dailyDelta", "dailyTotal", "employee.uid", "endMinute", "lunchLength", "startMinute"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "calendar.workDate"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: ReportedTime.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def listTimeAdjustment() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500, 10000]
    List<String> sortables = ["calendar.workDate", "comment", "delta", "employee.uid"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "employee.uid"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: TimeAdjustment.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def listWorkRate() {
    List<Integer> maxSizes = [25, 100, 250, 1000, 2500, 10000]
    List<String> sortables = ["comment", "employee.uid", "endDate", "rate", "rateMonday", "rateTuesday",  "rateWednesday", "rateThursday", "rateFriday", "startDate"]
    List<String> sortOrders = ["asc", "desc"]
    int max = (params.int('maxsize')) ?: 100
    String sort = (params.sortby?.trim()) ?: "employee.uid"
    String order = (params.sortorder?.trim()) ?: "asc"
    [entries: WorkRate.findAll([max: max, sort: sort, order: order]), max: max, maxSizes: maxSizes, order: order, sort: sort, sortables: sortables, sortOrders: sortOrders]
  }

  def sudo() {
    if(params.long('id')) {
      String role = (params.role) ?: 'employee'
      Employee employee = Employee.get(params.long('id'))
      if(employee) {
        if(!session.getAttribute('realuser') && session.getAttribute('realrole')) {
          session.setAttribute('realuser', session.getAttribute('uid'))
          session.setAttribute('realrole', session.getAttribute('realrole'))
        }
        session.setAttribute('uid', employee.uid)
        session.setAttribute('role', role)
        return redirect(controller: 'dashboard', action: 'index')
      }
    }
    List<Employee> recentUsers = sysAdminService.findRecentUsers(35)
    
    [recentUsers: recentUsers, roles: se.su.it.flex.Role.values()]
  }
}
