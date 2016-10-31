package se.su.it.flex

import grails.transaction.Transactional
import javax.sql.DataSource
import groovy.sql.Sql

@Transactional
class FlexService {
  DataSource dataSource
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
}
