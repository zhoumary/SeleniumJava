package javaBase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Itest {
    private String sportsOne = "https://s1-dev.bsu.edu.cn/";
    private String userName = "FionaZ";
    private String passwords = "Zyw03120312";

    public static void main(String[] args) throws InterruptedException {
        Itest autoTest = new Itest();
        WebDriver driver = login.loginS1(autoTest.userName, autoTest.passwords, autoTest.sportsOne);

        if (driver != null) {
            try {
                WebDriverWait loginWait = new WebDriverWait(driver, 35000);
                List<WebElement> loginEels = loginWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[@id='__item0-__xmlview0--menu-0']//div[@class='sapSportsMMItmTtl']")));
                if (loginEels != null) {
                    WebElement team = loginEels.get(0);
                    if (team != null) {
                        team.click();
                    }
                }
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(10000);
        driver.close();

    }
}