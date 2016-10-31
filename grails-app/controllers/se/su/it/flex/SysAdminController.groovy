package se.su.it.flex

import javax.sql.DataSource
import groovy.sql.Sql

class SysAdminController {
  DataSource dataSource
  
  def index() {
    List<Expando> recentUsers = []
    Sql sql = null
    try {
      sql = new Sql(dataSource)
      sql.rows("select e.id, e.uid, e.first_name, e.last_name from employee e where e.id in (select distinct(r.employee_id) from reported_time r where r.calendar_id in (select c.id from calendar c where c.work_date<now() and c.work_date>subdate(now(),35))) order by e.uid;").each { row ->
        recentUsers << Expando.newInstance(id: row['id'], name: "${row['uid']} (${row['last_name']}, ${row['first_name']})")
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
    [recentUsers: recentUsers]
  }

  def sudo() {
    if(!params.long('otherUser')) {
      flash.message = "No user provided!"
      log.error "No user Provided"
      return redirect (action: 'index')
    }
    Employee employee = Employee.get(params.long('otherUser'))
    if(!employee) {
      flash.message = "No user found for ${params.long('otherUser')}!"
      log.error "No user found for ${params.long('otherUser')}!"
      return redirect (action: 'index')
    }
    session.setAttribute('uid', employee.uid)
    return redirect (controller: 'dashboard', action: 'index')
  }
}
