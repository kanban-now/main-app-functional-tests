package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WaitForAngularRequestsToFinish {

    public static void waitForAngularRequestsToFinish(WebDriver webDriver) {
        webDriver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                                          "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    }

}
