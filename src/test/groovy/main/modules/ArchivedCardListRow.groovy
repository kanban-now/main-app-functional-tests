package main.modules

import geb.Module


class ArchivedCardListRow extends Module {

    static content = {
        cell { $("td", it) }
        cardText { cell(0).text() }
        boardId { cell(1).text() }
        archivedDate { cell(2).text() }

    }
}

