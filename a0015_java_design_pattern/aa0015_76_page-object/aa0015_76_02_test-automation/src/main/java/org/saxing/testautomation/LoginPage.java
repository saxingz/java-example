package org.saxing.testautomation;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 */
public class LoginPage extends Page {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private static final String LOGIN_PAGE_HTML_FILE = "login.html";
    private static final String PAGE_URL = "file:" + AUT_PATH + LOGIN_PAGE_HTML_FILE;

    private HtmlPage page;

    public LoginPage(WebClient webClient) {
        super(webClient);
    }

    /**
     * Navigates to the Login page
     *
     * @return {@link LoginPage}
     */
    public LoginPage navigateToPage() {
        try {
            page = this.webClient.getPage(PAGE_URL);
        } catch (IOException e) {
            LOGGER.error("An error occured on navigateToPage.", e);
        }
        return this;
    }

    @Override
    public boolean isAt() {
        return "Login".equals(page.getTitleText());
    }

    /**
     * Enters the username into the username input text field
     *
     * @param username the username to enter
     * @return {@link LoginPage}
     */
    public LoginPage enterUsername(String username) {
        HtmlTextInput usernameInputTextField = (HtmlTextInput) page.getElementById("username");
        usernameInputTextField.setText(username);
        return this;
    }

    /**
     * Enters the password into the password input password field
     *
     * @param password the password to enter
     * @return {@link LoginPage}
     */
    public LoginPage enterPassword(String password) {
        HtmlPasswordInput passwordInputPasswordField = (HtmlPasswordInput) page.getElementById("password");
        passwordInputPasswordField.setText(password);
        return this;
    }

    /**
     * Clicking on the login button to 'login'
     *
     * @return {@link AlbumListPage}
     *        - this is the page that user gets navigated to once successfully logged in
     */
    public AlbumListPage login() {
        HtmlSubmitInput loginButton = (HtmlSubmitInput) page.getElementById("loginButton");
        try {
            loginButton.click();
        } catch (IOException e) {
            LOGGER.error("An error occured on login.", e);
        }
        return new AlbumListPage(webClient);
    }

}
