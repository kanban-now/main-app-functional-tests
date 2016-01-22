package main.pages

import geb.Page

class KanbanNowHomePage extends Page {

    static at = { title == "Kanban Now" }

    static content = {
        whoAreYouMessage { $("#who-are-you-header") }
        helloMessage { $("h1", text: startsWith("Hello, ")) }
        logoutButton { $("a", text: 'Logout') }
    }


    void logout() {
        logoutButton.click(KanbanNowHomePage)
    }



}
