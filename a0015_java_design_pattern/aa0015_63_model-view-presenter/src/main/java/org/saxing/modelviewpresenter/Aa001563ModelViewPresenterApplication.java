package org.saxing.modelviewpresenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2019/4/22 10:25
 */
public class Aa001563ModelViewPresenterApplication {

    public static void main(String[] args) {
        FileLoader loader = new FileLoader();
        FileSelectorJFrame jFrame = new FileSelectorJFrame();
        FileSelectorPresenter presenter = new FileSelectorPresenter(jFrame);
        presenter.setLoader(loader);
        presenter.start();
    }

}
