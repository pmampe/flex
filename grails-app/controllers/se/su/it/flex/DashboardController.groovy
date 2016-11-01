package se.su.it.flex

class DashboardController {
  FlexService flexService

  def index() {
    Employee employee = (session.getAttribute('uid')) ? Employee.findByUid(session.getAttribute('uid') as String) : null
    int dailyDelta = (session.getAttribute('uid')) ? flexService.getAggregatedDeltaForUser(session.getAttribute('uid') as String) : 0
    List<Absence> absences = (employee) ? Absence.findAllByEmployee(employee, [sort: 'calendar.workDate', order: 'desc']) : []
    List<ReportedTime> reportedTimes = (employee) ? ReportedTime.findAllByEmployee(employee, [max: 25, sort: 'calendar.workDate', order: 'desc']) : []
    [absences: absences, dailyDelta: dailyDelta, employee: employee, reportedTimes: reportedTimes]
  }
}
