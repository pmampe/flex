package se.su.it.flex

public enum Role {
  PUBLIC('public',10),
  EMPLOYEE('employee',100),
  CALENDARADMIN ('calendar_admin', 300), 
  SYSADMIN('sysadmin',1000)

  private final String name
  private final int value

  private Role(String name, Integer value) {
    this.name = name
    this.value = value
  }

  public Integer value() {
    return this.value
  }

  public static Role findByName(String name) {
    for (r in Role.enumConstants) {
      if (name == r.name) {
        return r
      }
    }
    return null
  }

  @Override
  public String toString() {
    return name
  }
}

