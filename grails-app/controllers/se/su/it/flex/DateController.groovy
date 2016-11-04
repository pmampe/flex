package se.su.it.flex

class DateController {
  static defaultAction = "list"

  FlexService flexService

  def edit() {
    log.error "edit: ${params}"
    if(!params.long('id')) {
      flash.message = "Missing id"
      log.warn "Missing id"
      return redirect(action: 'list')
    }
    Calendar calendar = Calendar.get(params.long('id'))
    if(!calendar) {
      flash.message = "Cant find Calendar entry for ${params.long('id')}"
      log.warn "Missing id"
      return redirect(action: 'list')
    }
    if(params.update) {
      calendar.fullTime = (params.int('fullTime')) ?: 0
      calendar.mandatoryStart = (params.int('startTime')) ?: 0
      calendar.mandatoryEnd = (params.int('endTime')) ?: 0
      calendar.description = params.description?.trim()
      calendar.save(flush: true)
    }
    String dayOfWeek = flexService.getWeekDay(calendar.workDate)
    List<Map> commonTimes = [[id: 0, name: 'Ledig (00:00)'], [id: 275, name: 'Halvdag (04:35)'], [id: 450, name: 'Sommartid (07:30)'], [id: 490, name: 'Normaltid (08:10)']]
    [calendar: calendar, commonTimes: commonTimes, dayOfWeek: dayOfWeek, endTimes: [0, 12, 14, 15, 16], startTimes: [0, 8, 9, 10]]
  }

  def list() {
    int year = (params.int('id')) ? params.int('id') : -1
    String sortorder = (year>2004) ? 'asc': 'desc'
    Date startdate = (year>2004) ? Date.parse('yyyy-MM-dd', "${year}-01-01"): Date.newInstance().minus(35)
    Date enddate = (year>2004) ? Date.parse('yyyy-MM-dd', "${year}-12-31"): Date.newInstance().plus(35)
    
    List<Calendar> entries = Calendar.findAllByWorkDateGreaterThanEqualsAndWorkDateLessThanEquals(startdate, enddate, [sort: 'workDate', order: sortorder])
    int latestYear = flexService.findLatestYear()
    List<Integer> years = flexService.findUniqueYears()
    [entries: entries, latestYear: latestYear, years: years]
  }

  def populateYear() {
    int year = (params.int('id')) ? params.int('id') : -1
    if(year>2015) {
      flexService.createCalendarForYear(year)
    }
    return redirect(action: 'list')
  }
}
