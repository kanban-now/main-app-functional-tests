package main.pages

import geb.Page
import org.openqa.selenium.JavascriptExecutor

class KanbanNowHomePage extends Page {

    static at = { title == "Kanban Now" }

    static content = {
        whoAreYouMessage { $("#who-are-you-header") }
        helloMessage { $("h1", text: startsWith("Hello, ")) }
        logoutButton { $("a", text: 'Logout') }
        archivedCardsButton { $("a", id: 'archivedCardsButton')}
    }


    void logout() {
        logoutButton.click(KanbanNowHomePage)
    }

    ArchivedCardsListPage navigateToArchivedCardsPage() {
        JavascriptExecutor jsDriver = (JavascriptExecutor)browser.driver
        jsDriver.executeScript("window.getArchivedCardsLatch = 'started'");
        archivedCardsButton.click(ArchivedCardsListPage)
        waitForAjax(jsDriver, 10)
        return browser.page
    }

    public void waitForAjax(org.openqa.selenium.JavascriptExecutor jsDriver, int timeoutInSeconds)  {
        long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000);
        boolean timedOut = false;
        while (!timedOut) {
            Boolean done = (Boolean)jsDriver.executeScript("return window.getArchivedCardsLatch == 'done'");
            if(done) {
                return;
            }

            long currentTime = System.currentTimeMillis();
            if( currentTime > endTime) {
                timedOut = true;
            }

            sleep(1000)
        }

        throw new RuntimeException("Ajax call never returned");
    }

    private void sleep(long millisToSleep) {
        try {
            Thread.sleep(millisToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
