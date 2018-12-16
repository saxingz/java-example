package org.saxing.doubledispatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Meteoroid game object
 *
 * @author saxing 2018/12/16 13:38
 */
public class Meteoroid extends GameObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(Meteoroid.class);

    public Meteoroid(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    @Override
    public void collision(GameObject gameObject) {

    }


}
