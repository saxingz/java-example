package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * albumlist page
 *
 * @author saxing 2019/5/16 17:05
 */
public class AlbumListPage extends Page {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumListPage.class);
    private static final String ALBUM_LIST_HTML_FILE = "album-list.html";
    private static final String PAGE_URL = "file:" + AUT_PATH + ALBUM_LIST_HTML_FILE;

    private HtmlPage page;

    public AlbumListPage(WebClient webClient) {
        super(webClient);
    }

    /**
     * Navigates to the Album List Page
     *
     * @return {@link AlbumListPage}
     */
    public AlbumListPage navigateToPage() {
        try {
            page = this.webClient.getPage(PAGE_URL);
        } catch (IOException e) {
            LOGGER.error("An error occured on navigateToPage.", e);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAt() {
        return "Album List".equals(page.getTitleText());
    }

    /**
     * Selects an album by the given album title
     *
     * @param albumTitle the title of the album to click
     * @return the album page
     */
    public AlbumPage selectAlbum(String albumTitle) {
        // uses XPath to find list of html anchor tags with the class album in it
        List<HtmlAnchor> albumLinks = (List<HtmlAnchor>) page.getByXPath("//tr[@class='album']//a");
        for (HtmlAnchor anchor : albumLinks) {
            if (anchor.getTextContent().equals(albumTitle)) {
                try {
                    anchor.click();
                    return new AlbumPage(webClient);
                } catch (IOException e) {
                    LOGGER.error("An error occured on selectAlbum", e);
                }
            }
        }
        throw new IllegalArgumentException("No links with the album title: " + albumTitle);
    }

}
