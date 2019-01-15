package org.saxing.flux.dispatcher;

import org.saxing.flux.action.*;
import org.saxing.flux.store.Store;

import java.util.LinkedList;
import java.util.List;

/**
 * Dispatcher sends Actions to registered Stores.
 *
 * @author saxing 2019/1/15 23:10
 */
public final class Dispatcher {

    private static Dispatcher instance = new Dispatcher();

    private List<Store> stores = new LinkedList<>();

    private Dispatcher() {}

    public static Dispatcher getInstance() {
        return instance;
    }

    public void registerStore(Store store) {
        stores.add(store);
    }

    /**
     * Menu item selected handler
     *
     * @param menuItem
     */
    public void menuItemSelected(MenuItem menuItem){
        dispatchAction(new MenuAction(menuItem));
        switch (menuItem){
            case HOME:
            case PRODUCTS:
            default:
                dispatchAction(new ContentAction(Content.PRODUCTS));
                break;
            case COMPANY:
                dispatchAction(new ContentAction(Content.COMPANY));
                break;
        }
    }

    private void dispatchAction(Action action) {
        stores.stream().forEach(store -> store.onAction(action));
    }
}
