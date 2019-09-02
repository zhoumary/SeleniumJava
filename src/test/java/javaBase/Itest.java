package javaBase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Itest {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://s1-dev.bsu.edu.cn/");
        driver.manage().window().maximize();

        String title = driver.getTitle();
        System.out.printf(title);

        String username = "FIONAZ";
        String password = "Zyw03120312";

        WebElement loginUser = driver.findElement(By.xpath("//input[@id='xs_username-inner']"));
        loginUser.sendKeys(username);
        WebElement loginPassword = driver.findElement(By.xpath("//input[@id='xs_password-inner']"));
        loginPassword.sendKeys(password);
        WebElement login = driver.findElement(By.xpath("//button[@id='logon_button']"));
        if (login != null) {
            login.click();
        }

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

        Thread.sleep(10000);
        driver.close();

    }
}
