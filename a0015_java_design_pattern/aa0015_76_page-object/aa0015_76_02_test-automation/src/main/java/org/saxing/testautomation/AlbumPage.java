package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
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

    /**
     * Sets the album title input text field
     *
     * @param albumTitle the new album title value to set
     * @return {@link AlbumPage}
     */
    public AlbumPage changeAlbumTitle(String albumTitle) {
        HtmlTextInput albumTitleInputTextField = (HtmlTextInput) page.getElementById("albumTitle");
        albumTitleInputTextField.setText(albumTitle);
        return this;
    }

    /**
     * Sets the artist input text field
     *
     * @param artist the new artist value to set
     * @return {@link AlbumPage}
     */
    public AlbumPage changeArtist(String artist) {
        HtmlTextInput artistInputTextField = (HtmlTextInput) page.getElementById("albumArtist");
        artistInputTextField.setText(artist);
        return this;
    }

    /**
     * Selects the select's option value based on the year value given
     *
     * @param year the new year value to set
     * @return {@link AlbumPage}
     */
    public AlbumPage changeAlbumYear(int year) {
        HtmlSelect albumYearSelectOption = (HtmlSelect) page.getElementById("albumYear");
        HtmlOption yearOption = albumYearSelectOption.getOptionByValue(Integer.toString(year));
        albumYearSelectOption.setSelectedAttribute(yearOption, true);
        return this;
    }

    /**
     * Sets the album rating input text field
     *
     * @param albumRating the new album rating value to set
     * @return {@link AlbumPage}
     */
    public AlbumPage changeAlbumRating(String albumRating) {
        HtmlTextInput albumRatingInputTextField = (HtmlTextInput) page.getElementById("albumRating");
        albumRatingInputTextField.setText(albumRating);
        return this;
    }


    /**
     * Sets the number of songs number input field
     *
     * @param numberOfSongs the new number of songs value to be set
     * @return {@link AlbumPage}
     */
    public AlbumPage changeNumberOfSongs(int numberOfSongs) {
        HtmlNumberInput numberOfSongsNumberField = (HtmlNumberInput) page.getElementById("numberOfSongs");
        numberOfSongsNumberField.setText(Integer.toString(numberOfSongs));
        return this;
    }



    /**
     * Cancel changes made by clicking the cancel button
     *
     * @return {@link AlbumListPage}
     */
    public AlbumListPage cancelChanges() {
        HtmlSubmitInput cancelButton = (HtmlSubmitInput) page.getElementById("cancelButton");
        try {
            cancelButton.click();
        } catch (IOException e) {
            LOGGER.error("An error occured on cancelChanges.", e);
        }
        return new AlbumListPage(webClient);
    }


    /**
     * Saves changes made by clicking the save button
     *
     * @return {@link AlbumPage}
     */
    public AlbumPage saveChanges() {
        HtmlSubmitInput saveButton = (HtmlSubmitInput) page.getElementById("saveButton");
        try {
            saveButton.click();
        } catch (IOException e) {
            LOGGER.error("An error occured on saveChanges.", e);
        }
        return this;
    }
}
