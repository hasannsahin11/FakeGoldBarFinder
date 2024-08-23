package com.fetchchallange.tests;

import com.fetchchallange.base.BaseTest;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoldBarTest extends BaseTest {
    @Test
    public void findFakeGoldBar() {
        // Logic to find the fake gold bar
        int fakeBar = findFakeGoldBarAlgorithm();

        // Print the fake bar
        System.out.println("The fake gold bar is: " + fakeBar);

        // Click the button corresponding to the fake bar
        driver.findElement(By.xpath("//button[text()='" + fakeBar + "']")).click();

        // Get and validate the alert message
        String alertMessage = driver.switchTo().alert().getText();
        System.out.println(alertMessage);
        Assert.assertTrue(alertMessage.contains("Yay"), "Expected success alert, but got: " + alertMessage);

        // Accept the alert
        driver.switchTo().alert().accept();
    }

    public int findFakeGoldBarAlgorithm() {
        int[] group1 = {0, 1, 2};
        int[] group2 = {3, 4, 5};
        int[] group3 = {6, 7, 8};

        // Perform first weighing (group1 vs group2)
        String result = performWeighing(group1, group2);

        if (result.equals("=")) {
            return weighIndividualBars(group3);
        } else if (result.equals("<")) {
            return weighIndividualBars(group1);
        } else {
            return weighIndividualBars(group2);
        }

    }

    private String performWeighing(int[] leftBowl, int[] rightBowl) {
        // Reset and perform the weighing, return the result "<", "=", or ">"
        resetBowls();

        // Fill the left and right bowls
        for (int i = 0; i < leftBowl.length; i++) {
            driver.findElement(By.id("left_" + i)).sendKeys(String.valueOf(leftBowl[i]));
        }
        for (int i = 0; i < rightBowl.length; i++) {
            driver.findElement(By.id("right_" + i)).sendKeys(String.valueOf(rightBowl[i]));
        }


        // Click the weigh button
        driver.findElement(By.id("weigh")).click();


        // Get the result element
        WebElement resultElement = driver.findElement(By.id("reset"));

        // Wait until result appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(resultElement, "?")));

        // Get the result of the weighing
        return driver.findElement(By.id("reset")).getText();
    }

    private void resetBowls() {
        driver.findElement(By.xpath("(//button[@id='reset'])[2]")).click();
    }

    private int weighIndividualBars(int[] group) {
        String result = performWeighing(new int[]{group[0]}, new int[]{group[1]});
        if (result.equals("=")) {
            return group[2];  // The third bar is the fake one
        } else if (result.equals("<")) {
            return group[0];  // The first bar is lighter
        } else {
            return group[1];  // The second bar is lighter
        }
    }
}
