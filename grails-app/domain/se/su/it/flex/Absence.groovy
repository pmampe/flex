package se.su.it.flex

class Absence {
  Calendar calendar
  String comment
  Date dateCreated
  Employee employee
  Date lastUpdated
  int length = 0
  int start = 0

  static constraints = {
    calendar(nullable: false)
    comment(nullable: true, blank: true, maxSize: 255)
    dateCreated(nullable: false)
    employee(nullable: false, unique: ['calendar'])
    lastUpdated(nullable: false)
  }
}
