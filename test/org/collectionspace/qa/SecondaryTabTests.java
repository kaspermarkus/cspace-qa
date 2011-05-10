package org.collectionspace.qa;

import com.thoughtworks.selenium.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import static org.collectionspace.qa.Utilities.*;
/**
 *
 * @author kasper
 */
@RunWith(value = Parameterized.class)
public class SecondaryTabTests {

    static Selenium selenium;
    public static String BASE_URL = "http://localhost:8180/collectionspace/ui/html/",
            LOGIN_URL = "index.html",
            LOGIN_USER = "admin@collectionspace.org",
            LOGIN_PASS = "Administrator",
            REDIRECT_URL = "myCollectionSpace.html";

    private int primaryType, secondaryType;

    public SecondaryTabTests(int primaryType, int secondaryType) {
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
//            {Record.INTAKE},
//            {Record.LOAN_IN},
            {Record.LOAN_IN, Record.LOAN_OUT}
//            {Record.ACQUISITION},
//            {Record.MOVEMENT},
//            {Record.MEDIA},
//            {Record.OBJECT_EXIT},
//            {Record.CATALOGING}
        };
        return Arrays.asList(data);
    }

    @BeforeClass
    public static void init() throws Exception {
        selenium = new DefaultSelenium("localhost", 8888, "firefox", BASE_URL);
        selenium.start();

        //log in:
        login(selenium);
    }
    
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
        open(primaryType, primaryID, selenium);
        //go to secondary tab:
        selenium.click("link=" + Record.getRecordTypePP(secondaryType));
        elementPresent("//input[@value='Add record']", selenium);
        selenium.click("//input[@value='Add record']");
        elementPresent("css=.dialogForLoanOut :input[value='Create']", selenium);
        selenium.click("css=.dialogForLoanOut :input[value='Create']");

    }

    public static void openRelatedOf(int primaryType, String primaryID, int secondaryType, String secondaryID) throws Exception {
        open(primaryType, primaryID, selenium);
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

    @Test
    public void tabLeavePageWarning() throws Exception {
        String primaryID = "standardID",
                secondaryID = "relatedLoanOut";
        //createAndSave(primaryType, primaryID, selenium);
        openNewRelatedOf(primaryType, primaryID, secondaryType);
        //navigateWarningClose(secondaryType, seconaryID, selenium);
//        FIXME warningDialogSave(primaryType, primaryID, selenium);
        //openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
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
