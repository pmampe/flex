package se.su.it.flex

class DashboardController {
  FlexService flexService

  def calendar() {
    List<Calendar> days = Calendar.findAllByWorkDateLessThanEquals(Date.newInstance().plus(90),  [sort: 'workDate', order: 'desc', max: 135])
    [days: days]
  }

  def contacts() {
  }

  def history() {
    Employee employee = (session.getAttribute('uid')) ? Employee.findByUid(session.getAttribute('uid') as String) : null
    int adjustment = (session.getAttribute('uid')) ? flexService.getAggregatedTimeAdjustmentsForUser(session.getAttribute('uid') as String) : 0
    int dailyDelta = (session.getAttribute('uid')) ? flexService.getAggregatedDeltaForUser(session.getAttribute('uid') as String) : 0
    List<Absence> absences = (employee) ? Absence.findAllByEmployee(employee, [sort: 'calendar.workDate', order: 'desc']) : []
    List<ReportedTime> reportedTimes = (employee) ? ReportedTime.findAllByEmployee(employee, [max: 50, sort: 'calendar.workDate', order: 'desc']) : []
    List<TimeAdjustment> timeAdjustments = (employee) ? TimeAdjustment.findAllByEmployee(employee, [sort: 'calendar.workDate', order: 'desc']) : []
    List<WorkRate> workRates = (employee) ? WorkRate.findAllByEmployee(employee, [sort: 'startDate', order: 'desc']) : []
    [absences: absences, adjustment: adjustment, dailyDelta: dailyDelta, employee: employee, reportedTimes: reportedTimes, sum: (adjustment+dailyDelta), timeAdjustments: timeAdjustments, workRates: workRates]
  }

  def index() {
    log.info "index: ${params}"
    List<Map> reportableDays = flexService.findReportableDays()
    Calendar calendar = flexService.findCalendarForDate(Date.newInstance()) ?: Calendar.get(reportableDays[0]['id'])
    if(params.changeday && params.long('report_day') && Calendar.get(params.long('report_day'))) {
      calendar = Calendar.get(params.long('report_day'))
    }
    Employee employee = (session.getAttribute('uid')) ? Employee.findByUid(session.getAttribute('uid') as String) : null
    int normTime = flexService.getNormTimeForEmployeeAndDate(employee, calendar)
    int adjustment = (session.getAttribute('uid')) ? flexService.getAggregatedTimeAdjustmentsForUser(session.getAttribute('uid') as String) : 0
    int dailyDelta = (session.getAttribute('uid')) ? flexService.getAggregatedDeltaForUser(session.getAttribute('uid') as String) : 0
    [adjustment: adjustment, dailyDelta: dailyDelta, calendar: calendar, employee: employee, normTime: normTime, reportableDays: reportableDays, sum: (adjustment+dailyDelta)]
  }

  def show() {
    if(!params.long('id')) {
      log.warn "Missing id for Calendar"
      flash.message = "Missing id for Calendar"
      return redirect(action: 'index')
    }
    Calendar calendar = Calendar.get(params.long('id'))
    if(!calendar) {
      log.warn "Cant find Calendar for ${params.long('id')}"
      flash.message = "Cant find Calendar for ${params.long('id')}"
      return redirect(action: 'index')
    }
    Employee employee = (session.getAttribute('uid')) ? Employee.findByUid(session.getAttribute('uid') as String) : null
    if(!employee) {
      log.warn "No Employee available"
      flash.message = "No Employee available"
      return redirect(action: 'index')
    }
    ReportedTime reportedTime = ReportedTime.findByCalendarAndEmployee(calendar, employee, [max: 1])
    if(!reportedTime) {
      log.warn "Cant find ReportedTime for ${session.getAttribute('uid')} and ${calendar.workDate.format('yyyy-MM-dd')}"
      flash.message = "Cant find ReportedTime for ${session.getAttribute('uid')} and ${calendar.workDate.format('yyyy-MM-dd')}"
      return redirect(action: 'index')
    }
    List<Absence> absences = Absence.findAllByEmployeeAndCalendar(reportedTime.employee, reportedTime.calendar)
    int normTime = flexService.getNormTimeForEmployeeAndDate(employee, calendar)
    List<TimeAdjustment> timeAdjustments = TimeAdjustment.findAllByEmployeeAndCalendar(reportedTime.employee, reportedTime.calendar)
    WorkRate workRate = WorkRate.findByEmployeeAndStartDateLessThanEqualsAndEndDateGreaterThanEquals(reportedTime.employee, reportedTime.calendar.workDate, reportedTime.calendar.workDate, [max: 1])
    [absences: absences, normTime: normTime, reportedTime: reportedTime, timeAdjustments: timeAdjustments, workRate: workRate]
  }
}
