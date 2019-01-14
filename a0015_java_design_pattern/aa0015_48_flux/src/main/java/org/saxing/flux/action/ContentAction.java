package org.saxing.flux.action;

/**
 * ContentAction is a concrete action.
 *
 * @author saxing 2019/1/14 23:15
 */
public class ContentAction extends Action {

    private Content content;

    public ContentAction(Content content) {
        super(ActionType.CONTENT_CHANGED);
        this.content = content;
    }

    public Content getContent() {
        return content;
    }
}
