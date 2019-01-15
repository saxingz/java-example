package org.saxing.flux.store;

import org.saxing.flux.action.Action;
import org.saxing.flux.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * Store is a data model.
 *
 * @author saxing 2019/1/14 23:24
 */
public abstract class Store {

    private List<View> views = new LinkedList<>();

    public abstract void onAction(Action action);

    public void registView(View view){
        views.add(view);
    }

    protected void notifyChange(){
        views.stream().forEach(view -> view.storeChanged(this));
    }

}
