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
