package javaBase;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
                Boolean isTrainCreated = false;
                String trainingsURL = "";
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
                                trainingsURL = driver.getCurrentUrl();
                                WebElement createButton = createButtons.get(0);
                                createButton.click();
                                Thread.sleep(10000);

                                // create training
                                createtraining trainingInst = new createtraining(driver);
                                isTrainCreated = trainingInst.addTraining(driver);
                            }
                        }
                    }
                }

                // query the training created just now
                if (isTrainCreated && trainingsURL!="") {
                    // invoke query all trainings service
                    String playerID  = "";
                    URL trainList = new URL(trainingsURL);
                    String queryTraining = trainList.getRef();
                    int idx = queryTraining.indexOf("=");
                    playerID = URLDecoder.decode(queryTraining.substring(idx + 1), "UTF-8");


                    // invoke service, which needs to invoke another one
                    String queryTrainURL = "https://s1-dev.bsu.edu.cn/sap/sports/trm/appsvc/traininglist/services/rest/trainingList/traininglist/team/" + playerID + "?";
                    URL allTrainURL = new URL(queryTrainURL);
                    HttpURLConnection conn = (HttpURLConnection) allTrainURL.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP Error code : "
                                + conn.getResponseCode());
                    }
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    final Object trainContent = conn.getContent();
                    conn.disconnect();
                }
            } catch (TimeoutException | MalformedURLException | UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(10000);
        driver.close();

    }
}