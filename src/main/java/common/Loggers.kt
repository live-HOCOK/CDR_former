package common

import org.apache.logging.log4j.LogManager

class Loggers {
    companion object {
        val fileLogger = LogManager.getLogger("FileLogger")
        val consoleLogger = LogManager.getLogger("ConsoleLogger")
    }
}