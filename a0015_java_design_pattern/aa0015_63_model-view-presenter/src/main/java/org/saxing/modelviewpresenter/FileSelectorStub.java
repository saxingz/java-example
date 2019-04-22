package org.saxing.modelviewpresenter;

/**
 * Every instance of this class represents the Stub component in the Model-View-Presenter
 * architectural pattern.
 * <p>
 * The stub implements the View interface and it is useful when we want the test the reaction to
 * user events, such as mouse clicks.
 * <p>
 * Since we can not test the GUI directly, the MVP pattern provides this functionality through the
 * View's dummy implementation, the Stub.
 *
 * @author saxing 2019/4/22 10:15
 */
public class FileSelectorStub implements FileSelectorView {
    private static final long serialVersionUID = 9117143886395147952L;

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public void setPresenter(FileSelectorPresenter presenter) {

    }

    @Override
    public FileSelectorPresenter getPresenter() {
        return null;
    }

    @Override
    public void setFileName(String name) {

    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void displayData(String data) {

    }
}
