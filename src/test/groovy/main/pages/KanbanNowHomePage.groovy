package main.pages

import geb.Page
import main.WaitForAngularRequestsToFinish


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
        archivedCardsButton.click(ArchivedCardsListPage)
        WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish(browser.driver)
        return browser.page
    }

}
