package main.spec

import geb.spock.GebReportingSpec
import main.pages.KanbanNowHomePage
import main.pages.LoginPage

class HomePageSpec extends GebReportingSpec {


    public static String testUser1_id
    public static String testUser1_password

    def "can get to KanbanNow home page"() {
        when:
        LoginPage loginPage =   to LoginPage

        KanbanNowHomePage homePage = loginPage.login(testUser1_id, testUser1_password )

        then:
        homePage.helloMessage.text().startsWith("Hello, ")

        when:
        homePage.logout()

        then:
        homePage.whoAreYouMessage.text() == "Who are you?"

    }
}