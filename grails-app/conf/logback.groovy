import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
if(Environment.isDevelopmentMode() || Environment.currentEnvironment == Environment.TEST) {
  println("### Setting up logback for development/test mode ###")
  appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
            pattern = "%d %level %logger - %msg%n"
    }
  }
  root(INFO, ['STDOUT'])
  logger("grails.app.services.se.su.it.flex", DEBUG, ['STDOUT'], false)
  println("### Finished setting up logback for development/test mode ###")
} else {
  println("### Setting up logback for production mode ###")
  appender('TIME_BASED_FILE', RollingFileAppender) {
    file = "${System.properties["catalina.home"]}/logs/flex.log"
    rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${System.properties["catalina.home"]}/logs/flex.log.%d{yyyy-MM-dd}"
            maxHistory = 365
    }
    encoder(PatternLayoutEncoder) {
            pattern = "%d %level %logger - %msg%n"
    }
  }
  root(ERROR, ['TIME_BASED_FILE'])
  logger("org.grails.web.errors", DEBUG, ['TIME_BASED_FILE'], false)
  logger("grails", INFO, ['TIME_BASED_FILE'], false)
  logger("se.su.it.vfu", INFO, ['TIME_BASED_FILE'], false)
  println("### Finished setting up logback for production mode ###")
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
  appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%d %level %logger - %msg%n"
        }
  }
  logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}