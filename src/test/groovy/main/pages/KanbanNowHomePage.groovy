package main.pages

import geb.Page

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
        archivedCardsButton.click(ArchivedCardsListPage)
        waitFor { ajaxCompletedDiv }
        return browser.page
    }



}
