package se.su.it.flex

class Employee {
  Date dateCreated
  String emailAddress
  String firstName
  String lastName
  Date lastUpdated
  String uid

  static constraints = {
    dateCreated(nullable: false)
    emailAddress(nullable: true, blank: true, maxSize: 128)
    firstName(nullable: false, blank: false, maxSize: 64)
    lastName(nullable: false, blank: false, maxSize: 64)
    lastUpdated(nullable: false)
    uid(nullable: false, blank: false, maxSize: 32)
  }

  @Override
  public String toString() {
    return "${lastName}, ${firstName} (${uid})"
  }
}
