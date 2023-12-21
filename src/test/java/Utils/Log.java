package Utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    // Initialize SLF4J logger
    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    // This is to print log for the beginning of the test case
    public static void startTestCase(String sTestCaseName) {
        logger.info("****************************************************************************************");
        logger.info("****************************************************************************************");
        logger.info("$$$$$$$$$$$$$$$$$$$$$            " + sTestCaseName + "            $$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("****************************************************************************************");
        logger.info("****************************************************************************************");
    }

    // This is to print log for the ending of the test case
    public static void endTestCase(String sTestCaseName) {
        logger.info("Elenaproblemsolutionstatementclassesfocusqueryresolutionhoperegretinstructorcrying             " + "-E---N---D-" + "             XXXXXXXXXXXXXXXXXXXXXX");
        logger.info("Chaymae");
        logger.info("Chaymae");
        logger.info("Chaymae");
        logger.info("Chaymae");
    }

    // Need to create these methods, so that they can be called
    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.error("FATAL: " + message); // SLF4J doesn't have a distinct "fatal" level, so you can treat it as an error.
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}

