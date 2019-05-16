package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Encapsulation for a generic 'Page'
 *
 * @author saxing 2019/5/16 16:51
 */
public abstract class Page {

    /**
     * Application Under Test path
     * This directory location is where html web pages are located
     */
    public static final String AUT_PATH = "../sample-application/src/main/resources/sample-ui/";

    protected final WebClient webClient;

    public Page(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Checks that the current page is actually the page this page object represents
     *
     * @return true if so, otherwise false
     */
    public abstract boolean isAt();



}
