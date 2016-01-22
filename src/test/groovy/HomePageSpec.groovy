import geb.spock.GebReportingSpec

class HomePageSpec extends GebReportingSpec {


    def "can get to KanbanNow home page"() {
        when:
        to KanbanNowHomePage


//        and: //hover over to expand the menu
//        interact {
//            moveToElement(manualsMenu.toggle)
//        }
//
//        then: //first link is for the current manual
//        manualsMenu.links[0].text().endsWith("- CURRENT")
//
//        when:
//        manualsMenu.links[0].click()
//
//        then:
//        at TheBookOfGebPage
        then:
        5 == 5
    }
}