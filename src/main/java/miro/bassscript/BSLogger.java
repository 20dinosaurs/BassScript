package miro.bassscript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BSLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("bassscript");

    public void logDebug(String s) {
        LOGGER.debug(s);
    }

    public void logInfo(String s) {
        LOGGER.info(s);
    }

    public void logWarning(String s) {
        LOGGER.warn(s);
    }
}
