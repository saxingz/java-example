package org.saxing.flux.view;

import org.saxing.flux.action.MenuItem;
import org.saxing.flux.store.MenuStore;
import org.saxing.flux.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MenuView is a concrete view.
 *
 * @author saxing 2019/1/15 23:08
 */
public class MenuView implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuView.class);

    private MenuItem selected = MenuItem.HOME;

    @Override
    public void storeChanged(Store store) {
        MenuStore menuStore = (MenuStore) store;
        selected = menuStore.getSelected();
        render();
    }

    @Override
    public void render() {
        for (MenuItem item : MenuItem.values()){
            if (selected.equals(item)){
                LOGGER.info("* {}", item);
            }else{
                LOGGER.info(item.toString());
            }
        }
    }

    public void itemClicked(MenuItem item) {

    }
}
