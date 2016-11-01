package se.su.it.flex

import grails.transaction.Transactional
import grails.transaction.NotTransactional
import javax.sql.DataSource
import groovy.sql.Sql

@Transactional
class FlexService {
  DataSource dataSource
  
  @Transactional
  public void createCalendarForYear(int year) {
    if(year>2015) {
      java.util.Calendar c = java.util.Calendar.getInstance();
      Date date = Date.parse('yyyy-MM-dd', "${year}-01-01")
      Date limit = Date.parse('yyyy-MM-dd', "${1+year}-01-01")
      Date summerStart = Date.parse('yyyy-MM-dd', "${year}-05-01")
      Date summerEnd = Date.parse('yyyy-MM-dd', "${year}-09-15")
      while(date<limit) {
        Calendar calendar = Calendar.findByWorkDate(date)
        if(!calendar) {
          calendar = Calendar.newInstance()
          calendar.workDate = date
          calendar.dateCreated = Date.newInstance()
          calendar.lastUpdated = Date.newInstance()
          c.setTime(date);
          int dayOfWeek = c.get(java.util.Calendar.DAY_OF_WEEK);
          if(java.util.Calendar.SATURDAY==dayOfWeek || java.util.Calendar.SUNDAY==dayOfWeek) {
            calendar.description = "Helg"
            calendar.fullTime = 0
            calendar.mandatoryEnd = 0
            calendar.mandatoryStart = 0
          } else {
            calendar.description = "Vardag"
            calendar.mandatoryEnd = 15
            calendar.mandatoryStart = 9
            if(date>=summerStart &&date<=summerEnd) {
              calendar.fullTime = 450
            } else {
              calendar.fullTime = 490
            }
          }
          calendar.save(flush: true)
        }
        date = date.next()
      }
      List<String> oddDates = ["${year}-01-01", "${year}-01-06","${year}-12-24", "${year}-12-25", "${year}-12-26", "${year}-12-31"]
      oddDates.each { String day ->
        date = Date.parse('yyyy-MM-dd', day)
        Calendar cal = Calendar.findByWorkDate(date)
        cal.description = "Helg"
        cal.fullTime = 0
        cal.mandatoryEnd = 0
        cal.mandatoryStart = 0
        cal.save(flush: true)
      }
    }
  }

  @Transactional(readOnly = true)
  public int findLatestYear() {
    Sql sql = null
    int latestYear = 0
    try {
      sql = new Sql(dataSource)
      sql.rows("select max(year(work_date)) as year from calendar;").each { row ->
        latestYear = row['year'] as int
      }
    } catch(Throwable exception) {
      log.error "Some problems: ${exception.getMessage()}"
    } finally {
      if(null!=sql) {
        try {
          sql.close()
        } catch(Throwable exception) {
        }
        sql = null
      }
    }
    return latestYear
  }

  @Transactional(readOnly = true)
  public List<Integer> findUniqueYears() {
    Sql sql = null
    List<Integer> years = []
    try {
      sql = new Sql(dataSource)
      sql.rows("select distinct(year(work_date)) as year from calendar order by year;").each { row ->
        years << row['year']
      }
    } catch(Throwable exception) {
      log.error "Some problems: ${exception.getMessage()}"
    } finally {
      if(null!=sql) {
        try {
          sql.close()
        } catch(Throwable exception) {
        }
        sql = null
      }
    }
    return years
  }
  
  @Transactional(readOnly = true)
  public int getAggregatedDeltaForUser(String uid) {
    Sql sql = null
    int flexsaldo = 0
    try {
      sql = new Sql(dataSource)
      sql.rows([uid: uid],"select sum(r.daily_delta) as flexsaldo from reported_time r inner join employee e on e.id=r.employee_id where e.uid=:uid;").each { row ->
        flexsaldo = row['flexsaldo'] as int
      }
    } catch(Throwable exception) {
      log.error "Some problems: ${exception.getMessage()}"
    } finally {
      if(null!=sql) {
        try {
          sql.close()
        } catch(Throwable exception) {
        }
        sql = null
      }
    }
    return flexsaldo
  }

  @NotTransactional
  public String getWeekDay(Date date) {
    java.util.Calendar inst = java.util.Calendar.getInstance()
    inst.setTime(date)
    Map<Integer, String> weekDays = [:]
    weekDays.put(java.util.Calendar.MONDAY, "Måndag")
    weekDays.put(java.util.Calendar.TUESDAY, "Tisdag")
    weekDays.put(java.util.Calendar.WEDNESDAY, "Onsdag")
    weekDays.put(java.util.Calendar.THURSDAY, "Torsdag")
    weekDays.put(java.util.Calendar.FRIDAY, "Fredag")
    weekDays.put(java.util.Calendar.SATURDAY, "Lördag")
    weekDays.put(java.util.Calendar.SUNDAY, "Söndag")
    return weekDays.get(inst.get(java.util.Calendar.DAY_OF_WEEK))
  }
}
