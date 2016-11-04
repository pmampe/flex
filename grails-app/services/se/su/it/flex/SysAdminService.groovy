package se.su.it.flex

import grails.transaction.Transactional
import javax.sql.DataSource
import groovy.sql.Sql
import groovy.transform.CompileStatic

@Transactional
@CompileStatic
class SysAdminService {
  DataSource dataSource

  @Transactional(readOnly = true)
  public List<Expando> findInactiveFlexers(int limit) {
    Sql sql = null
    List<Expando> inactiveFlexers = []
    try {
      sql = new Sql(dataSource)
      sql.rows("select max(c.work_date) as lastreport, e.uid, e.id from employee e inner join reported_time r on e.id=r.employee_id inner join calendar c on c.id=r.calendar_id  group by e.uid order by lastreport asc limit ${(limit>0)? limit : 100};").each { row ->
        inactiveFlexers << Expando.newInstance(lastDate: row['lastreport'], employee: Employee.get(row['id'] as long))
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
    return inactiveFlexers
  }

  @Transactional(readOnly = true)
  public List<Employee> findRecentUsers(int numberOfDays) {
    List<Employee> recentUsers = []
    Sql sql = null
    try {
      sql = new Sql(dataSource)
      sql.rows("select e.id from employee e where e.id in (select distinct(r.employee_id) from reported_time r where r.calendar_id in (select c.id from calendar c where c.work_date<now() and c.work_date>subdate(now(),${numberOfDays}))) order by e.last_name, e.first_name;").each { row ->
        Employee e = (row['id']) ? Employee.get(row['id'] as long) : null
        if(e) {
          recentUsers << e
        }
      }
    } catch(Throwable exception) {
      log.warn "Some problems fetching users: ${exception.getMessage()}"
    } finally {
      if(null!=sql) {
        try {
          sql.close()
        } catch(Throwable exception) {
        }
        sql = null
      }
    }
    return recentUsers
  }
}
