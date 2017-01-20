package Utilities;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class MyWaits {

    public static ExpectedCondition<Boolean> visibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return element.isDisplayed();
                } catch (NoSuchElementException e) {
                    return false;
                } catch (AssertionError e1) {
                    return false;
                } catch (StaleElementReferenceException e2) {
                    return false;
                }
            }
        };
    }

}
