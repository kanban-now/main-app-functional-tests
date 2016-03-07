package main.pages

import geb.Page

class ArchivedCardsListPage extends Page {


    static at = { title == "View Archived Cards List" }

    static content = {
        archivedCards { $("table", id: 'archivedCards' )}
    }


}
