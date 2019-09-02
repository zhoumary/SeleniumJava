package javaBase;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class login {
    @org.jetbrains.annotations.NotNull
    static WebDriver loginS1(String user, String key, String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        WebElement loginUser = driver.findElement(By.xpath("//input[@id='xs_username-inner']"));
        loginUser.sendKeys(user);
        WebElement loginPassword = driver.findElement(By.xpath("//input[@id='xs_password-inner']"));
        loginPassword.sendKeys(key);
        WebElement login = driver.findElement(By.xpath("//button[@id='logon_button']"));
        if (login != null) {
            login.click();
        }
        return driver;
    }
}
