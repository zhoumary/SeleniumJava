package javaBase;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Itest {
    private String sportsOne = "******";
    private String userName = "******";
    private String passwords = "******";

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
                        WebDriverWait trainintWait = new WebDriverWait(driver, 5000);
                        List<WebElement> trainings = trainintWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='__item12-__xmlview5--sections-list-6-content']//div[@class='sapMSLITitleOnly']")));
                        if (trainings != null) {
                            WebElement training = trainings.get(0);
                            training.click();
                            WebDriverWait createWait = new WebDriverWait(driver, 10000);
                            List<WebElement> createButtons = createWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@id='__button25-__toolbar4-0']")));
                            if (createButtons != null) {
                                WebElement createButton = createButtons.get(0);
                                createButton.click();
                            }
                        }
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
