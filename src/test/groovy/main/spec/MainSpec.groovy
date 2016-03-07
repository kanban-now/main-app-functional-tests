package main.spec

import feign.Feign
import feign.auth.BasicAuthRequestInterceptor
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import geb.spock.GebReportingSpec
import main.archivecardserviceclient.ArchivedCardClient
import main.archivecardserviceclient.Card
import main.pages.ArchivedCardsListPage
import main.pages.KanbanNowHomePage
import main.pages.LoginPage

class MainSpec extends GebReportingSpec {

    public static String testUser1_id
    public static String testUser1_password

    public static String archiveCardServiceUrl
    public static String archiveCardServiceUserName
    public static String archiveCardServicePassword



    def "can get to KanbanNow home page"() {

        when:
        ArchivedCardClient archivedCardClient = Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .requestInterceptor(new BasicAuthRequestInterceptor(archiveCardServiceUserName, archiveCardServicePassword))
                .target(ArchivedCardClient.class, archiveCardServiceUrl);


        String userId = "userId1"
        Card newCard1 = new Card()
        newCard1.setText("Test text 1")
        newCard1.setBoardId(444)
        newCard1 = archivedCardClient.createCard(userId, newCard1)

        Card newCard2 = new Card()
        newCard2.setText("Test text 2")
        newCard2.setBoardId(445)
        newCard2 = archivedCardClient.createCard(userId, newCard2)

//        // Fetch and print a list of the contributors to this library.
//        List<Card> cards = archivedCardClient.cards(userId);
////        for (Contributor contributor : contributors) {
////            System.out.println(contributor.login + " (" + contributor.contributions + ")");
////        }

        LoginPage loginPage =   to LoginPage
        KanbanNowHomePage homePage = loginPage.login(testUser1_id, testUser1_password )

        then:
        homePage.helloMessage.text().startsWith("Hello, ")

        when:
        ArchivedCardsListPage archivedCardsListPage = homePage.navigateToArchivedCardsPage()


        then:
        archivedCardsListPage.archivedCards.size() == 1

        when:
        browser.driver.navigate().back()
        homePage.logout()

        then:
        homePage.whoAreYouMessage.text() == "Who are you?"

        cleanup:
        archivedCardClient.deleteCard(userId,newCard1.id)
        archivedCardClient.deleteCard(userId,newCard2.id)


    }
}