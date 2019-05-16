package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Album Page Operations
 *
 * @author saxing 2019/5/16 17:30
 */
public class AlbumPageTest {

  private AlbumPage albumPage = new AlbumPage(new WebClient());

  @BeforeEach
  public void setUp() {
    albumPage.navigateToPage();
  }

  @Test
  public void testSaveAlbum() {

    AlbumPage albumPageAfterChanges = albumPage
        .changeAlbumTitle("25")
        .changeArtist("Adele Laurie Blue Adkins")
        .changeAlbumYear(2015)
        .changeAlbumRating("B")
        .changeNumberOfSongs(20)
        .saveChanges();

    assertTrue(albumPageAfterChanges.isAt());

  }

  @Test
  public void testCancelChanges() {
    AlbumListPage albumListPage = albumPage.cancelChanges();
    albumListPage.navigateToPage();
    assertTrue(albumListPage.isAt());
  }

}
