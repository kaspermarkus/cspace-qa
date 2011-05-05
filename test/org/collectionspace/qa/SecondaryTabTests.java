package org.collectionspace.qa;

import com.thoughtworks.selenium.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.junit.*;
import static org.junit.Assert.*;
import static org.collectionspace.qa.Utilities.*;

/**
 *
 * @author kasper
 */
public class SecondaryTabTests {
    static Selenium selenium;
    public static String BASE_URL = "http://localhost:8180/collectionspace/ui/html/",
            LOGIN_URL = "index.html",
            LOGIN_USER = "admin@collectionspace.org",
            LOGIN_PASS = "Administrator",
            REDIRECT_URL = "myCollectionSpace.html";
    private static Record mainRecordType;

    /**
     * Opens the record with type primaryType, then navigates to the secondary
     * given by secondaryType and creates a new record of that type. The record
     * is NOT saved
     *
     * @param primaryType
     * @param primaryID
     * @param secondaryType
     */
    public static void openNewRelatedOf(int primaryType, String primaryID, int secondaryType) throws Exception {
        open(primaryType, primaryID);
        //go to secondary tab:
        selenium.click("link=" + Record.getRecordTypePP(secondaryType));
        elementPresent("//input[@value='Add record']", selenium);
        selenium.click("//input[@value='Add record']");
        selenium.click("//div[39]/div[2]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/input</td>");

    }

    public static void openRelatedOf(int primaryType, String primaryID, int secondaryType, String secondaryID) throws Exception {
        open(primaryType, primaryID);
        //go to secondary tab:
        selenium.click("link=" + Record.getRecordTypePP(secondaryType));
        textPresent(secondaryID, selenium);
        int rowCount = 0;
        System.out.println("textpresent: " + secondaryID);
        String selector = "row::column:-1";
        System.out.println("checking whether " + selector + " is present" + selenium.isElementPresent(selector));
        elementPresent(selector, selenium);
        System.out.println("checking whether " + selector + " is present" + selenium.isElementPresent(selector));
        while (selenium.isElementPresent(selector)) {
            System.out.println("found " + selector);
            if (secondaryID.equals(selenium.getText(selector))) {
                System.out.println("matched text: '" + selenium.getText(selector) + "'");
                selenium.click(selector);
                waitForRecordLoad(secondaryType, selenium);
                return;
            }
            System.out.println("didn't match text: '" + selenium.getText(selector) + "'");
            rowCount++;
            selector = "row:" + rowCount + ":column:-1";
            System.out.println("checking whether " + selector + " is present" + selenium.isElementPresent(selector));
        }
        assertTrue("Error when opening related record of " + primaryID + " - couldn't find " + secondaryID, false);
    }

    public static void open(int primaryType, String primaryID) throws Exception {
        System.out.println("opening record of type " + Record.getRecordTypePP(primaryType));
        elementPresent("css=.cs-searchBox .csc-searchBox-selectRecordType", selenium);
        //Search for our ID
        selenium.select("css=.cs-searchBox .csc-searchBox-selectRecordType", "label=" + Record.getRecordTypePP(primaryType));
        selenium.type("css=.cs-searchBox .csc-searchBox-query", primaryID);
        selenium.click("css=.cs-searchBox .csc-searchBox-button");
        System.out.println("clicking search");
        //Expect record and only that to be found in search results (to avoid false thruths :) )
        selenium.waitForPageToLoad(MAX_WAIT);
        elementPresent("link=" + primaryID, selenium);
        System.out.println("found search result");
        //go to the record again:
        selenium.click("link=" + primaryID);
        waitForRecordLoad(selenium);
    }

    //@Test
    public void tabLeavePageWarning() throws Exception {
        int primaryType = Record.ACQUISITION,
                secondaryType = Record.LOAN_OUT;
        String primaryID = "standardID",
                seconaryID = "relatedLoanOut";

        openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
        navigateWarningClose(secondaryType, seconaryID, selenium);
//        FIXME warningDialogSave(primaryType, primaryID, selenium);
        openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
//      FIXME  warningDialogDontSave(secondaryType, seconaryID, selenium);
    }

    //@Test
    public void tabTestCanel() throws Exception {
        int primaryType = Record.ACQUISITION,
                secondaryType = Record.LOAN_OUT;
        String primaryID = "standardID",
                seconaryID = "relatedLoanOut";

        log(Record.getRecordTypePP(primaryType) + ": test cancel button in secondary tab");
        openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
        //fill out fields again with default values and save
        fillForm(primaryType, primaryID, selenium);
        System.out.println("saving with default values");
        selenium.click("//input[@value='Save']");
        textPresent("successfully", selenium);
        System.out.println("record looks saved - navigating to record");
        openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
        System.out.println("running cancel test");
        //FIXME cancelTest(secondaryType, seconaryID, selenium);
    }
}
