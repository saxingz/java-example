package org.saxing.flux.action;

/**
 * MenuAction is a concrete action.
 *
 * @author saxing 2019/1/14 23:16
 */
public class MenuAction extends Action {

    private MenuItem menuItem;

    public MenuAction(MenuItem menuItem) {
        super(ActionType.MENU_ITEM_SELECTED);
        this.menuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
