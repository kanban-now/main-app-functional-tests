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

import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainSpec extends GebReportingSpec {

    public static String testUser1_id
    public static String testUser1_password
    public static String testUser1_internalId

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


        Date timeAtStartOfTest = new Date();

        Card newCard1 = new Card()
        newCard1.setText("Test text 1")
        newCard1.setBoardId(444)
        newCard1 = archivedCardClient.createCard(testUser1_internalId, newCard1)

        Card newCard2 = new Card()
        newCard2.setText("Test text 2")
        newCard2.setBoardId(445)
        newCard2 = archivedCardClient.createCard(testUser1_internalId, newCard2)

        LoginPage loginPage = to LoginPage
        KanbanNowHomePage homePage = loginPage.login(testUser1_id, testUser1_password )

        then:
        homePage.helloMessage.text().startsWith("Hello, ")

        when:
        ArchivedCardsListPage archivedCardsListPage = homePage.navigateToArchivedCardsPage()


        then:
        archivedCardsListPage.archivedCards.size() == 2
        assert archivedCardsListPage.archivedCards[0].cardText ==  newCard1.text
        Date card1ArchiveDate = convertISO8601StringToTimestamp(archivedCardsListPage.archivedCards[0].archivedDate)
        assert card1ArchiveDate.after(timeAtStartOfTest)

        assert archivedCardsListPage.archivedCards[1].cardText ==  newCard2.text
        Date card2ArchiveDate = convertISO8601StringToTimestamp(archivedCardsListPage.archivedCards[1].archivedDate)
        assert card2ArchiveDate.after(timeAtStartOfTest)

        when:
        browser.driver.navigate().back()
        homePage.logout()

        then:
        homePage.whoAreYouMessage.text() == "Who are you?"

        cleanup:
        archivedCardClient.deleteCard(testUser1_internalId,newCard1.id)
        archivedCardClient.deleteCard(testUser1_internalId,newCard2.id)


    }

    private Date convertISO8601StringToTimestamp(String timestampString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        return df.parse(timestampString);
    }


}