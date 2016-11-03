// drop database flex;
// create database flex charset utf8 collate utf8_swedish_ci;
// grant all on flex.* to 'flex'@'localhost' identified by 'flex';
databaseChangeLog = {
  changeSet(author: "mano3567", id: "1448278733528-1") {
    createTable(tableName: "employee") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "employeePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "uid", type: "varchar(32)") {
        constraints(nullable: "false", unique: true)
      }

      column(name: "first_name", type: "varchar(64)") {
        constraints(nullable: "false")
      }

      column(name: "last_name", type: "varchar(64)") {
        constraints(nullable: "false")
      }

      column(name: "email_address", type: "varchar(128)") {
        constraints(nullable: "true")
      }
    }
  }

  changeSet(author: "mano3567", id: "1448278733528-2") {
    createTable(tableName: "calendar") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "calendarPK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "work_date", type: "date") {
        constraints(nullable: "false", unique: true)
      }

      column(name: "description", type: "varchar(128)") {
        constraints(nullable: "true")
      }

      column(name: "full_time", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "mandatory_start", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "mandatory_end", type: "integer") {
        constraints(nullable: "false", default: 0)
      }
    }
  }
  
  changeSet(author: "mano3567", id: "1448278733528-3") {
    createTable(tableName: "reported_time") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "reportedTimePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "employee_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "calendar_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "comment", type: "varchar(128)") {
        constraints(nullable: "true")
      }

      column(name: "end_minute", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "start_minute", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "lunch_length", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "absent_all_day", type: "bit(1)") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "daily_delta", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "daily_total", type: "integer") {
        constraints(nullable: "false", default: 0)
      }
    }
  }

  changeSet(author: "mano3567", id: "1448278733528-4") {
    createTable(tableName: "absence") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "absencePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "calendar_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "employee_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "comment", type: "varchar(255)") {
        constraints(nullable: "true")
      }

      column(name: "length", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "start", type: "integer") {
        constraints(nullable: "false", default: 0)
      }
    }
  }

  changeSet(author: "mano3567", id: "1448278733528-5") {
    createTable(tableName: "time_adjustment") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "timeAdjustmentPK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "calendar_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "employee_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "comment", type: "varchar(255)") {
        constraints(nullable: "true")
      }

      column(name: "delta", type: "integer") {
        constraints(nullable: "false", default: 0)
      }
    }
  }

  changeSet(author: "mano3567", id: "1448278733528-6") {
    createTable(tableName: "work_rate") {
      column(autoIncrement: "true", name: "id", type: "bigint") {
        constraints(nullable: "false", primaryKey: "true", primaryKeyName: "workRatePK")
      }

      column(name: "version", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "date_created", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "last_updated", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "start_date", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "end_date", type: "datetime") {
        constraints(nullable: "false")
      }

      column(name: "employee_id", type: "bigint") {
        constraints(nullable: "false")
      }

      column(name: "comment", type: "varchar(255)") {
        constraints(nullable: "true")
      }

      column(name: "rate", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "rate_monday", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "rate_tuesday", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "rate_wednesday", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "rate_thursday", type: "integer") {
        constraints(nullable: "false", default: 0)
      }

      column(name: "rate_friday", type: "integer") {
        constraints(nullable: "false", default: 0)
      }
    }
  }

  changeSet(author: "mano3567", id: "1448278733528-7") {
    addForeignKeyConstraint(baseColumnNames: "calendar_id", baseTableName: "reported_time", constraintName: "rtcFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "calendar", referencesUniqueColumn: "false")
    addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "reported_time", constraintName: "rteFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employee", referencesUniqueColumn: "false")
    addForeignKeyConstraint(baseColumnNames: "calendar_id", baseTableName: "absence", constraintName: "abscFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "calendar", referencesUniqueColumn: "false")
    addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "absence", constraintName: "abseFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employee", referencesUniqueColumn: "false")
    addForeignKeyConstraint(baseColumnNames: "calendar_id", baseTableName: "time_adjustment", constraintName: "tacFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "calendar", referencesUniqueColumn: "false")
    addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "time_adjustment", constraintName: "taeFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employee", referencesUniqueColumn: "false")
    addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "work_rate", constraintName: "wreFK", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employee", referencesUniqueColumn: "false")
  }
}
