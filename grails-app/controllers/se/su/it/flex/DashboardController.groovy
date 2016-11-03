package se.su.it.flex

class DashboardController {
  FlexService flexService

  def index() {
    Employee employee = (session.getAttribute('uid')) ? Employee.findByUid(session.getAttribute('uid') as String) : null
    int dailyDelta = (session.getAttribute('uid')) ? flexService.getAggregatedDeltaForUser(session.getAttribute('uid') as String) : 0
    List<Absence> absences = (employee) ? Absence.findAllByEmployee(employee, [sort: 'calendar.workDate', order: 'desc']) : []
    List<ReportedTime> reportedTimes = (employee) ? ReportedTime.findAllByEmployee(employee, [max: 50, sort: 'calendar.workDate', order: 'desc']) : []
    List<TimeAdjustment> timeAdjustments = (employee) ? TimeAdjustment.findAllByEmployee(employee, [sort: 'calendar.workDate', order: 'desc']) : []
    List<WorkRate> workRates = (employee) ? WorkRate.findAllByEmployee(employee, [sort: 'startDate', order: 'desc']) : []
    [absences: absences, dailyDelta: dailyDelta, employee: employee, reportedTimes: reportedTimes, timeAdjustments: timeAdjustments, workRates: workRates]
  }

  def show() {
    if(!params.long('id')) {
      log.warn "Missing id for ReportedTime"
      flash.message = "Missing id for ReportedTime"
      return redirect(action: 'index')
    }
    ReportedTime reportedTime = ReportedTime.get(params.long('id'))
    if(!reportedTime) {
      log.warn "Cant find ReportedTime for ${params.long('id')}"
      flash.message = "Cant find ReportedTime for ${params.long('id')}"
      return redirect(action: 'index')
    }
    List<Absence> absences = Absence.findAllByEmployeeAndCalendar(reportedTime.employee, reportedTime.calendar)
    List<TimeAdjustment> timeAdjustments = TimeAdjustment.findAllByEmployeeAndCalendar(reportedTime.employee, reportedTime.calendar)
    WorkRate workRate = WorkRate.findByEmployeeAndStartDateLessThanEqualsAndEndDateGreaterThanEquals(reportedTime.employee, reportedTime.calendar.workDate, reportedTime.calendar.workDate, [max: 1])
    [absences: absences, reportedTime: reportedTime, timeAdjustments: timeAdjustments, workRate: workRate]
  }
}
