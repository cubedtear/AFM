package afm.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import afm.data.Properties;
import cpw.mods.fml.common.FMLLog;

/**
 * AFMLogger
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AFMLogger {

	private static final Logger logger = Logger.getLogger("AFM");
	private static final Level defaultLevel = Level.INFO;

	public static void init() {
		AFMLogger.logger.setParent(FMLLog.getLogger());
	}

	public static void log(Level level, String message) {
		AFMLogger.logger.log(level, message);
	}

	public static void log(Level level, String message, Object... params) {
		AFMLogger.logger.log(level, String.format(message, params));
	}

	public static void log(String message) {
		AFMLogger.logger.log(AFMLogger.defaultLevel, message);
	}

	public static void log(String message, Object... params) {
		AFMLogger.logger.log(AFMLogger.defaultLevel, String.format(message, params));
	}

	public static void log(String message, Throwable thrown) {
		AFMLogger.logger.log(AFMLogger.defaultLevel, message, thrown);
	}

	public static void log(Object o) {
		AFMLogger.log(String.valueOf(o));
	}

	public static void debug(String s) {
		if (Properties.DEBUG) {
			AFMLogger.log(s);
		}
	}

}
