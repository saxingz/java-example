package org.saxing.flux.store;

import org.saxing.flux.action.Action;
import org.saxing.flux.action.ActionType;
import org.saxing.flux.action.Content;
import org.saxing.flux.action.ContentAction;

/**
 * ContentStore is a concrete store.
 *
 * @author saxing 2019/1/15 23:07
 */
public class ContentStore extends Store {

    private Content content = Content.PRODUCTS;

    @Override
    public void onAction(Action action) {
        if (action.getType().equals(ActionType.CONTENT_CHANGED)){
            ContentAction contentAction = (ContentAction) action;
            content = contentAction.getContent();
            notifyChange();
        }
    }

    public Content getContent() {
        return content;
    }
}
