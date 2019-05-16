package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlbumListPageTest {

    private AlbumListPage albumListPage = new AlbumListPage(new WebClient());

    @BeforeEach
    public void setUp() {
        albumListPage.navigateToPage();
    }

    @Test
    public void testSelectAlbum() {
        AlbumPage albumPage = albumListPage.selectAlbum("21");
        albumPage.navigateToPage();
        assertTrue(albumPage.isAt());
    }

}
