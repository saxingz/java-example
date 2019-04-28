package org.saxing.muteidiom;

import java.io.Closeable;

/**
 * Represents any resource that the application might acquire and that must be closed
 * after it is utilized. Example of such resources can be a database connection, open
 * files, sockets.
 *
 * @author saxing 2019/4/28 9:40
 */
public interface Resource extends Closeable {
}
