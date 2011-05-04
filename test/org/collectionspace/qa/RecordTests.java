package org.collectionspace.qa;

import com.thoughtworks.selenium.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.junit.*;
import static org.junit.Assert.*;
import static org.collectionspace.qa.Utilities.*;

public class RecordTests {

//    public static Record[] recordTypes;
    static Selenium selenium;
    public static String BASE_URL = "http://localhost:8180/collectionspace/ui/html/",
            LOGIN_URL = "index.html",
            LOGIN_USER = "admin@collectionspace.org",
            LOGIN_PASS = "Administrator",
            REDIRECT_URL = "myCollectionSpace.html";
    public static Record mainRecordType;

    @BeforeClass
    public static void init() throws Exception {
        selenium = new DefaultSelenium("localhost", 8888, "firefox", BASE_URL);
        selenium.start();

        //log in:
        login(selenium);

    }

    /**
     * TEST: tests the creation of a new record via the create new page:
     * 1) Open create new page
     * 2) Select record in question via radio button
     * 3) Click Create
     *
     * X) Expect correct record page to be loaded
     * 
     * @throws Exception
     */
    @Test
    public void testCreateNew() throws Exception {
        //FIXME: OK retardo, dont hardcode this
        int primaryType = Record.LOAN_OUT;

        log("CREATE NEW: Testing creating new " + Record.getRecordTypePP(primaryType) + "\n");
        selenium.open("createnew.html");
        textPresent(Record.getRecordTypePP(primaryType), selenium);
        selenium.click("css=:radio[value='" + Record.getRecordTypeShort(primaryType) + "']");
        selenium.click("//input[@value='Create']");
        log("CREATE NEW: expect correct record page to load and pattern chooser to show\n");
        waitForRecordLoad(selenium);
        assertEquals(Record.getRecordTypePP(primaryType), selenium.getText("css=#title-bar .record-type"));
    }

    /**
     * TEST: Tests the save functionality:
     * 1) Create a new record
     * 2) Fill out form with default values
     * 3) Save the record
     * 4) Check that the fields are as expected
     * 
     * @throws Exception
     */
    @Test
    public void testPrimarySave() throws Exception {
        //FIXME: OK retardo, dont hardcode this
        int primaryType = Record.LOAN_OUT;
        String primaryID = "standardID";
        
        log(Record.getRecordTypePP(primaryType)+": test fill out record and save\n");
        selenium.open(Record.getRecordTypeShort(primaryType) + ".html");
        waitForRecordLoad(primaryType, selenium);

        fillForm(primaryType, primaryID, selenium);
        //save record
        log(Record.getRecordTypePP(primaryType) + ": expect save success message and that all fields are valid\n");
        save(selenium);
        //check values:
        verifyFill(primaryType, primaryID, selenium);
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

    public static void createAndSave(String idPrefix, Record newRecordType) throws Exception {
        //create new
        selenium.open(newRecordType.shortname + ".html");
        waitForRecordLoad(selenium);
        assertEquals(newRecordType.longname, selenium.getText("css=#title-bar .record-type"));
        selenium.type(newRecordType.IDFieldSelector, idPrefix + mainRecordType.defaultID);
        //and save
        selenium.click("//input[@value='Save']");
        textPresent("successfully", selenium);
    }

    //@Test
    public void tabLeavePageWarning() throws Exception {
        int primaryType = Record.ACQUISITION,
                secondaryType = Record.LOAN_OUT;
        String primaryID = "standardID",
                seconaryID = "relatedLoanOut";

        openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
        warningDialogClose(secondaryType, seconaryID);
        warningDialogSave(primaryType, primaryID);
        openRelatedOf(primaryType, primaryID, secondaryType, seconaryID);
        warningDialogDontSave(secondaryType, seconaryID);
    }

    public static void warningDialogClose(int primaryType, String primaryID) throws Exception {
        waitForRecordLoad(selenium);
        //edit field (ID field)
        selenium.type(Record.getIDSelector(primaryType), "hello");
        selenium.type(Record.getIDSelector(primaryType), primaryID);
        //navigate away
        selenium.click("link=Find and Edit");
        elementPresent("ui-dialog-title-1", selenium);
        //expect warning and click cancel
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("//input[@value='Cancel']");
        //navigate away, expect warning dialog, close with top right close symbol
        selenium.click("link=Acquisition");
        elementPresent("ui-dialog-title-1", selenium);
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("//img[@alt='close dialog']");
    }

    public static void warningDialogSave(int primaryType, String primaryID) throws Exception {
        waitForRecordLoad(selenium);
        //edit field (ID field)
        selenium.type(Record.getIDSelector(primaryType), "edit");
        selenium.type(Record.getIDSelector(primaryType), primaryID);
        //Search for our ID (should cause warning too)
        selenium.select("recordTypeSelect-selection", "label=" + Record.getRecordTypePP(primaryType));
        selenium.type("query", primaryID);
        selenium.click("//input[@value='Search']");
        //expect warning for leaving page
        elementPresent("ui-dialog-title-1", selenium);
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("css=.csc-confirmationDialogButton-act");        //click save
        //Expect record and only that to be found in search results (to avoid false thruths :) )
        selenium.waitForPageToLoad(MAX_WAIT);
        elementPresent("link=" + primaryID, selenium);
        assertEquals("Found 1 records for " + primaryID, selenium.getText("css=.csc-search-results-count"));
    }

    public static void warningDialogDontSave(int primaryType, String primaryID) throws Exception {
        waitForRecordLoad(selenium);
        //edit ID field
        selenium.type(".csc-loanOut-loanOutNumber", primaryID + "CHANGED");
        //navigate away, wait for dialog and click dont save:
        selenium.click("link=Create New");
        elementPresent("ui-dialog-title-1", selenium);
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("//input[@value=\"Don't Save\"]");
        //wait for page to load
        textPresent(Record.getRecordTypePP(primaryType), selenium);
        //search for the changed ID (which should not be present, since we didn't save
        selenium.select("recordTypeSelect-selection", "label=" + Record.getRecordTypePP(primaryType));
        selenium.type("query", primaryID + "CHANGED");
        selenium.click("//input[@value='Search']");
        //wait for page to load.. Record should not be be found:
        textPresent("Viewing page 1", selenium);
        assertFalse(selenium.isElementPresent("link=" + primaryID + "CHANGED"));
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
        cancelTest(secondaryType, seconaryID);
    }

    //expects record to be present with defualt values
    public void cancelTest(int primaryType, String primaryID) throws Exception {
        System.out.println("changing ID to " + primaryID + "CHANGED");
        selenium.type(Record.getIDSelector(primaryType), primaryID + "CHANGED");

        //now modify fields
        Iterator<String> iterator = Record.getFieldMap(primaryType).keySet().iterator();
        //FIXME pass this to fillform as simpleParams + include dates
        while (iterator.hasNext()) {
            String selector = iterator.next();
            String newValue = Record.getFieldMap(primaryType).get(selector) + "modified";
            System.out.println("changing from " + Record.getFieldMap(primaryType).get(selector) + " to " + Record.getFieldMap(primaryType).get(selector) + "modified");
            selenium.type(selector, newValue);
        }
        //select from all select boxes
        iterator = Record.getSelectMap(primaryType).keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            //select something than what we have
            selenium.select(selector, "index=0");
        }
        selenium.click("//input[@value='Cancel changes']");
        System.out.println("waiting for values to change");
        textNotPresent(primaryID + "CHANGED", selenium);
        System.out.println("done - veryfying fields");
//FIXME        verifyFill(primaryType, primaryID);
    }




    //@Test
    public void testRemovingValues() throws Exception {
        log(mainRecordType.longname.toUpperCase() + ": test removing of values from fields and save\n");
        //Delete contents of all fields:
        Iterator<String> iterator = mainRecordType.fieldMap.keySet().iterator();
        while (iterator.hasNext()) {
            selenium.type(iterator.next(), "");
        }
        //save record - and expect error due to missing ID
        selenium.click("//input[@value='Save']");
        log(mainRecordType.longname.toUpperCase() + ": expect error message due to missing required field\n");
        elementPresent("CSS=.cs-message-error", selenium);
        assertEquals(mainRecordType.requiredIDmessage, selenium.getText("CSS=.cs-message-error #message\n"));
        //Enter ID and save - expect successful
        log(mainRecordType.longname.toUpperCase() + ": enter id, save and expect correctly saved values\n");
        selenium.type(mainRecordType.IDFieldSelector, mainRecordType.defaultID);
        selenium.click("//input[@value='Save']");
        textPresent("successfully", selenium);
        //check values:
        iterator = mainRecordType.fieldMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            if (selector.equals(mainRecordType.IDFieldSelector)) {
                continue; //dont expect ID field to be empty
            }
            assertEquals("", selenium.getValue(iterator.next()));
        }
    }

    //Test cancel button:
    //@Test
    public void testCancel() throws Exception {
        log(mainRecordType.longname.toUpperCase() + ": test cancel button");
        //fill out fields again:
//FIXME:        fillForm(mainRecordType.fieldMap, mainRecordType.selectMap);
        //save record
        selenium.click("//input[@value='Save']");
        textPresent("successfully", selenium);
        log(mainRecordType.longname.toUpperCase() + ": modify all fields\n");
        Iterator<String> iterator = mainRecordType.fieldMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            //if data change to "now"
            String fieldValue = mainRecordType.fieldMap.get(selector);
            //FIXME:
            String newValue = (Pattern.matches("$\\d{4}\\-\\d{2}\\-\\d{2}", fieldValue) ? "now" : mainRecordType.fieldMap.get(selector) + "modified");
            System.out.println("checked date for: " + fieldValue + " -> " + newValue);
            selenium.type(selector, newValue);
        }
        //select from all select boxes
        iterator = mainRecordType.selectMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            //select something than what we have
            selenium.select(selector, "index=0");
        }
        log(mainRecordType.longname.toUpperCase() + ": click cancel and expect content to change to original\n");
        selenium.click("//input[@value='Cancel changes']");
//FIXME        verifyFill(mainRecordType.fieldMap, mainRecordType.selectMap);

    }

    //@Test
    public void testDeleteRecord() throws Exception {
        log(mainRecordType.longname.toUpperCase() + ": test delete functionality\n");
        log(mainRecordType.longname.toUpperCase() + ": delete confirmation - Close and Cancel\n");
        selenium.click("deleteButton");
        textPresent("Confirmation", selenium);

        assertTrue(selenium.isTextPresent("exact:Delete this record?"));
        selenium.click("//img[@alt='close dialog']");
        selenium.click("deleteButton");
        selenium.click("//input[@value='Cancel']");
        selenium.click("deleteButton");
        log(mainRecordType.longname.toUpperCase() + ": successfull delete\n");
        selenium.click("css=.cs-confirmationDialog :input[value='Delete']");
        textPresent("Record successfully deleted", selenium);
        selenium.click("css=.cs-confirmationDialog :input[value='OK']");
        selenium.waitForPageToLoad(MAX_WAIT);
        log(mainRecordType.longname.toUpperCase() + ": redirection to " + REDIRECT_URL + "\n");
        assertEquals(BASE_URL + REDIRECT_URL, selenium.getLocation());
        elementPresent("css=.cs-searchBox :input[value='Search']", selenium);
        selenium.select("recordTypeSelect-selection", "label=" + mainRecordType.longname);
        selenium.type("css=.cs-searchBox :input[name='query']", mainRecordType.defaultID);
        selenium.click("css=.cs-searchBox :input[value='Search']");
        selenium.waitForPageToLoad(MAX_WAIT);
        log(mainRecordType.longname.toUpperCase() + ": expect no results when searching for the record\n");
        textPresent("Found 0 records for " + mainRecordType.defaultID, selenium);
        assertFalse(selenium.isElementPresent("link=ID"));
    }

    //@Test
    public void testLeavePageWarning() throws Exception {
        log(mainRecordType.longname.toUpperCase() + ": test leave page warning\n");
        //create a new record
        selenium.open(mainRecordType.url);
        selenium.waitForPageToLoad(MAX_WAIT);
        waitForRecordLoad(selenium);
        //fill out ID
        log(mainRecordType.longname.toUpperCase() + ": dialog - Cancel and Close\n");
        selenium.type(mainRecordType.IDFieldSelector, mainRecordType.defaultID);
        //navigate away
        selenium.click("link=Find and Edit");
        elementPresent("ui-dialog-title-1", selenium);
        //expect warning and click cancel
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("//input[@value='Cancel']");
        //navigate away, expect warning dialog, close with top right close symbol
        selenium.click("link=Acquisition");
        elementPresent("ui-dialog-title-1", selenium);
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("//img[@alt='close dialog']");
        log(mainRecordType.longname.toUpperCase() + ": dialog 'Save' - expect it was properly saved\n");
        //Search for our ID
        selenium.select("recordTypeSelect-selection", "label=" + mainRecordType.longname);
        selenium.type("query", mainRecordType.defaultID);
        selenium.click("//input[@value='Search']");
        //expect warning for leaving page
        elementPresent("ui-dialog-title-1", selenium);
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("css=.csc-confirmationDialogButton-act");        //click save
        //Expect record and only that to be found in search results (to avoid false thruths :) )
        selenium.waitForPageToLoad(MAX_WAIT);
        elementPresent("link=" + mainRecordType.defaultID, selenium);
        assertEquals("Found 1 records for " + mainRecordType.defaultID, selenium.getText("css=.csc-search-results-count"));
        //go to the record again:
        selenium.click("link=" + mainRecordType.defaultID);
        waitForRecordLoad(selenium);
        //change ID
        log(mainRecordType.longname.toUpperCase() + ": dialog 'Dont Save' - expect no results when searching for the record\n");
        selenium.type(".csc-loanOut-loanOutNumber", mainRecordType.defaultID + "CHANGED");
        //navigate away, wait for dialog and click dont save:
        selenium.click("link=Create New");
        elementPresent("ui-dialog-title-1", selenium);
        assertTrue(selenium.isTextPresent("exact:Save Changes?"));
        selenium.click("//input[@value=\"Don't Save\"]");
        //wait for page to load
        textPresent(mainRecordType.longname, selenium);
        //search for the changed ID (which should not be present, since we didn't save
        selenium.select("recordTypeSelect-selection", "label=" + mainRecordType.longname);
        selenium.type("query", mainRecordType.defaultID + "CHANGED");
        selenium.click("//input[@value='Search']");
        //wait for page to load.. Record should not be be found:
        textPresent("Viewing page 1", selenium);
        assertFalse(selenium.isElementPresent("link=" + mainRecordType.defaultID + "CHANGED"));
    }



//    public void fillForm(HashMap<String, String> fieldMap, HashMap<String, String> selectMap) {
//        //FIXME - should use recordID
//        System.exit(0);
//        //fill out all fields:
//        Iterator<String> iterator = fieldMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String selector = iterator.next();
//            selenium.type(selector, fieldMap.get(selector));
//        }
//        //select from all select boxes
//        iterator = selectMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String selector = iterator.next();
//            selenium.select(selector, "label=" + selectMap.get(selector));
//        }
//    }

  

//    public void verifyFill(HashMap<String, String> fieldMap, HashMap<String, String> selectMap) {
//        System.exit(0);
//        //check values:
//        Iterator<String> iterator = fieldMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String selector = iterator.next();
//            assertEquals(fieldMap.get(selector), selenium.getValue(selector));
//        }
//        iterator = selectMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String selector = iterator.next();
//            assertEquals(selectMap.get(selector), selenium.getSelectedLabel(selector));
//        }
//    }
}
