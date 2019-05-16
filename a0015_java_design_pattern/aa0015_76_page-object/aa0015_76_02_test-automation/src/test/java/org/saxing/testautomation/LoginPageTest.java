package org.saxing.testautomation;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Login Page Object
 *
 * @author saxing 2019/5/16 17:30
 */
public class LoginPageTest {

  private LoginPage loginPage = new LoginPage(new WebClient());

  @BeforeEach
  public void setUp() {
    loginPage.navigateToPage();
  }

  @Test
  public void testLogin() {
    AlbumListPage albumListPage = loginPage
        .enterUsername("admin")
        .enterPassword("password")
        .login();
    albumListPage.navigateToPage();
    assertTrue(albumListPage.isAt());
  }

}
