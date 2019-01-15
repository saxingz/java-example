package org.saxing.flux.view;

import org.saxing.flux.store.Store;

/**
 * Views define the representation of data.
 *
 * @author saxing 2019/1/15 23:03
 */
public interface View {

    void storeChanged(Store store);

    void render();

}
