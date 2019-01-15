package org.saxing.flux.store;

import org.saxing.flux.action.Action;
import org.saxing.flux.action.ActionType;
import org.saxing.flux.action.MenuAction;
import org.saxing.flux.action.MenuItem;

/**
 * MenuStore is a concrete store.
 *
 * @author saxing 2019/1/15 23:05
 */
public class MenuStore extends Store {

    private MenuItem selected = MenuItem.HOME;

    @Override
    public void onAction(Action action) {
        if (action.getType().equals(ActionType.MENU_ITEM_SELECTED)){
            MenuAction menuAction = (MenuAction) action;
            selected = menuAction.getMenuItem();
            notifyChange();
        }
    }

    public MenuItem getSelected() {
        return selected;
    }
}
