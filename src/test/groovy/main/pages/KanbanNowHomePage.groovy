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
        ajaxCompletedDiv { $("div", id: 'ajaxCompleted')}
    }


    void logout() {
        logoutButton.click(KanbanNowHomePage)
    }

    ArchivedCardsListPage navigateToArchivedCardsPage() {
        JavascriptExecutor jsDriver = (JavascriptExecutor)browser.driver
        archivedCardsButton.click(ArchivedCardsListPage)
        waitFor { ajaxCompletedDiv }
        return browser.page
    }


    private void sleep(long millisToSleep) {
        try {
            Thread.sleep(millisToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
