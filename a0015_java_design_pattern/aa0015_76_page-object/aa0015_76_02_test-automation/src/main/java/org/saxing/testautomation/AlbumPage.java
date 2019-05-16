package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Page Object encapsulating the Album Page (album-page.html)
 *
 * @author saxing 2019/5/16 17:07
 */
public class AlbumPage extends Page {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumPage.class);
    private static final String ALBUM_PAGE_HTML_FILE = "album-page.html";
    private static final String PAGE_URL = "file:" + AUT_PATH + ALBUM_PAGE_HTML_FILE;

    private HtmlPage page;


    public AlbumPage(WebClient webClient) {
        super(webClient);
    }

    /**
     * Navigates to the album page
     *
     * @return {@link AlbumPage}
     */
    public AlbumPage navigateToPage() {
        try {
            page = this.webClient.getPage(PAGE_URL);
        } catch (IOException e) {
            LOGGER.error("An error occured on navigateToPage.", e);
        }
        return this;
    }

    @Override
    public boolean isAt() {
        return "Album Page".equals(page.getTitleText());
    }



}
