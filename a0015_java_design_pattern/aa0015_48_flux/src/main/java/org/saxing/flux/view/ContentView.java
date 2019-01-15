package org.saxing.flux.view;

import org.saxing.flux.action.Content;
import org.saxing.flux.store.ContentStore;
import org.saxing.flux.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ContentView is a concrete view.
 *
 * @author saxing 2019/1/15 23:14
 */
public class ContentView implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentView.class);

    private Content content = Content.PRODUCTS;

    @Override
    public void storeChanged(Store store) {
        ContentStore contentStore = (ContentStore) store;
        content = contentStore.getContent();
        render();
    }

    @Override
    public void render() {
        LOGGER.info(content.toString());
    }
}
