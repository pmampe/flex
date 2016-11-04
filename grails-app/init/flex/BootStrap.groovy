package flex

import grails.util.Environment
import se.su.it.flex.Employee

class BootStrap {
  def destroy = {
  }

  def init = { servletContext ->
    if(Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) {
      Employee employee = Employee.findByUid('supersture@su.se')
      if(!employee) {
        employee = Employee.newInstance()
        employee.uid = 'supersture@su.se'
        employee.firstName = 'Sture'
        employee.lastName = 'SuperAdmin'
        employee.emailAddress = 'supersture@su.se'
        employee.dateCreated = Date.newInstance()
        employee.lastUpdated = Date.newInstance()
        employee.save(flush: true)
      }
    }
  }
}
