package UI;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

    public class BasePage {
        protected WebDriver driver;
        protected WebDriverWait wait;
        protected MainPage mainPage;
        @Before
        public void SetChrome() {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32-91\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
            mainPage = new MainPage(driver, wait);


        }

        @After
        public void CloseDriver() {
            driver.close();
        }
    }
