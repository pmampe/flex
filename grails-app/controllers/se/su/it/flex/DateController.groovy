package se.su.it.flex

class DateController {
  static defaultAction = "list"

  FlexService flexService

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
