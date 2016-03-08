package main.pages

import geb.Page
import main.modules.ArchivedCardListRow

class ArchivedCardsListPage extends Page {


    static at = { title == "View Archived Cards List" }

    static content = {
        archivedCards {
            $("table", id: 'archivedCards').find("tr").tail().moduleList(ArchivedCardListRow)
        }

    }


}
