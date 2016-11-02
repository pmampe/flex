package se.su.it.flex

class WorkRate {
  String comment
  Date dateCreated
  Date endDate
  Employee employee
  Date lastUpdated
  Date startDate
  int rate = 0
  int rateMonday = 0
  int rateTuesday = 0
  int rateWednesday = 0
  int rateThursday = 0
  int rateFriday = 0

  static constraints = {
    comment(nullable: true, blank: true)
    dateCreated(nullable: false)
    employee(nullable: false)
    endDate(nullable: false)
    lastUpdated(nullable: false)
    startDate(nullable: false)
  }
}
