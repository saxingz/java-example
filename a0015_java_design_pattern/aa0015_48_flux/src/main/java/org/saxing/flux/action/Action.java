package org.saxing.flux.action;


/**
 * Action is the data payload dispatched to the stores when something happens.
 *
 * @author saxing 2019/1/14 23:14
 */
public abstract class Action {

    private ActionType type;

    public Action(ActionType type) {
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

}
