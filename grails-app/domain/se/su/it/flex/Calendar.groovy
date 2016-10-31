package se.su.it.flex

class Calendar {
  Date dateCreated
  String description
  int fullTime = 0
  Date lastUpdated
  int mandatoryEnd = 0
  int mandatoryStart = 0
  Date workDate

  static constraints = {
    dateCreated(nullable: false)
    description(nullable: true, blank: true, maxSize: 128)
    lastUpdated(nullable: false)
    workDate(nullable: false, unique: true)
  }
  
  @Override
  public String toString() {
    return workDate.format('yyyy-MM-dd')
  }
}
