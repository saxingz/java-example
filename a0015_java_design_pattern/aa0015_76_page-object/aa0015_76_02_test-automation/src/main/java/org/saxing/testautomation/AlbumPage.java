package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Page Object encapsulating the Album Page (album-page.html)
 *
 * @author saxing 2019/5/16 17:07
 */
public class AlbumPage extends Page {



    public AlbumPage(WebClient webClient) {
        super(webClient);
    }

    @Override
    public boolean isAt() {
        return false;
    }
}
