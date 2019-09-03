package javaBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class createtraining {
    WebDriver driver;

    public createtraining(WebDriver webDriver) {
        driver = webDriver;
    }


    static Boolean addTraining(WebDriver driverInst) {
        Boolean isCreated = false;
        // fill training information for creating training
        if (driverInst != null) {
            // fill title
            WebElement title = driverInst.findElement(By.xpath("//input[@id='__xmlview21--trainingShortText-inner']"));
            title.sendKeys("ctc test training 01");
            // fill location
            WebElement location = driverInst.findElement(By.xpath("//input[@id='__xmlview21--trainingLocation-inner']"));
            location.sendKeys("北京");
            // fill intensity
            WebElement intensity = driverInst.findElement(By.xpath("//span[@id='__xmlview21--trainingIntensity-arrow']"));
            intensity.click();
            String searchText = "Medium";
            WebDriverWait waitIntensity = new WebDriverWait(driverInst, 5000);
            List<WebElement> intensities = waitIntensity.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='__list21']")));
            if (intensities != null) {
                //intensities.get(0).click();
                WebElement intensityChoice = intensities.get(0);
                List<WebElement> options = intensityChoice.findElements(By.tagName("li"));
                for (WebElement option : options)
                {
                    String intensityText = option.getAttribute("innerHTML");
                    if (intensityText.equals(searchText))
                    {
                        option.click(); // click the desired option
                        break;
                    }
                }
            }

            // choose multiple appointments
            WebElement multiAppts = driverInst.findElement(By.xpath("//div[@id='__switch0-switch']"));
            multiAppts.click();
            WebDriverWait waitMulti = new WebDriverWait(driverInst, 10000);
            List<WebElement> appts = waitMulti.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='__box8-CbBg']")));
            if (appts != null) {
                WebElement unChoose = appts.get(0);
                unChoose.click();
            }


            // save training
            WebElement save = driverInst.findElement(By.xpath("//button[@id='__xmlview19--saveCreateTraining']"));
            save.click();
            WebDriverWait waitCreating = new WebDriverWait(driverInst, 20000);
            List<WebElement> trainingTitles = waitCreating.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id='__xmlview21--trainingShortText-inner']")));
            if (trainingTitles == null) {
                isCreated = true;
            }
        }


        return isCreated;
    }
}
