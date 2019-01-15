package org.saxing.flux;

import org.saxing.flux.action.MenuItem;
import org.saxing.flux.dispatcher.Dispatcher;
import org.saxing.flux.store.ContentStore;
import org.saxing.flux.store.MenuStore;
import org.saxing.flux.view.ContentView;
import org.saxing.flux.view.MenuView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/1/15 23:15
 */
public class Aa001548FluxApplication {

    public static void main(String[] args) {

        MenuStore menuStore = new MenuStore();
        Dispatcher.getInstance().registerStore(menuStore);
        ContentStore contentStore = new ContentStore();
        Dispatcher.getInstance().registerStore(contentStore);
        MenuView menuView = new MenuView();
        menuStore.registView(menuView);
        ContentView contentView = new ContentView();
        contentStore.registView(contentView);

        // render initial view
        menuView.render();
        contentView.render();

        // user clicks another menu item
        // this triggers action dispatching and eventually causes views to render with new content
        menuView.itemClicked(MenuItem.COMPANY);

    }

}

