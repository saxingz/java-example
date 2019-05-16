package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;

public class AlbumListPage extends Page {

    public AlbumListPage(WebClient webClient) {
        super(webClient);
    }

    @Override
    public boolean isAt() {
        return false;
    }
}
