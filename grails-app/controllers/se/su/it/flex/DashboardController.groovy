package se.su.it.flex

class DashboardController {
  FlexService flexService
  def index() {
    Employee employee = (session.uid) ? Employee.findByUid(session.uid) : null
    int dailyDelta = (session.uid) ? flexService.getAggregatedDeltaForUser(session.uid) : 0
    List<Absence> absences = (employee) ? Absence.findAllByEmployee(employee, [sort: 'calendar.workDate', order: 'desc']) : []
    List<ReportedTime> reportedTimes = (employee) ? ReportedTime.findAllByEmployee(employee, [max: 25, sort: 'calendar.workDate', order: 'desc']) : []
    [absences: absences, dailyDelta: dailyDelta, employee: employee, reportedTimes: reportedTimes]
  }
}
