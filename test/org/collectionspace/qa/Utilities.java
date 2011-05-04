package org.collectionspace.qa;

import com.thoughtworks.selenium.*;
import java.util.HashMap;
import java.util.Iterator;
import static org.junit.Assert.*;

public class Utilities {

    public static String
            BASE_URL = "http://localhost:8180/collectionspace/ui/html/",
            LOGIN_URL = "index.html",
            LOGIN_USER = "admin@collectionspace.org",
            LOGIN_PASS = "Administrator",
            MAX_WAIT = "60000";
    public static int
            MAX_WAIT_SEC = 60;
    public static String LOGIN_REDIRECT = "myCollectionSpace.html";

    /**
     * Logs in to collectionspace as LOGIN_USER with LOGIN_PASS
     *
     * @param selenium a Selenium object to check with
     * @throws Exception
     */
    public static void login(Selenium selenium) throws Exception {
        selenium.open(LOGIN_URL);
        log("LOGIN: logging in as admin\n");
        selenium.type("userid", LOGIN_USER);
        selenium.type("password", LOGIN_PASS);
        selenium.click("//input[@value='Sign In']");
        selenium.waitForPageToLoad(MAX_WAIT);
        assertEquals(BASE_URL + LOGIN_REDIRECT, selenium.getLocation());
    }

    /**
     * Saves record and wait for successful message. Returns once the success
     * message is shown
     *
     * EXPECTS: to be in a record where the required fields are filled out
     *
     * @param selenium a Selenium object to run the actions with
     * @throws Exception
     */
    public static void save(Selenium selenium) throws Exception {
        //save record
        selenium.click("//input[@value='Save']");
        textPresent("successfully", selenium);
    }

    /**
     * Fills out the fields of the given record type with the given recordID string as ID
     * and the default values of that record type.
     *
     * EXPECTS: that the record is loaded
     *
     * @param recordType The record type to fill out
     * @param recordID The value to put in the ID field
     * @param selenium The selenium object used to fill out the form
     */
    public static void fillForm(int recordType, String recordID, Selenium selenium) {
        fillForm(recordType, recordID, Record.getFieldMap(recordType), Record.getSelectMap(recordType), Record.getDateMap(recordType), selenium);
    }

    /**
     * Fills out the fields of the given record type with the given recordID string as ID
     * and the default values given as parameters
     *
     * EXPECTS: that the record is loaded
     *
     * @param recordType The record type to fill out
     * @param recordID The value to put in the ID field
     * @param fieldMap Map of selectors/values to put in regular text fields and text areas
     * @param selectMap Map of selectors/values to use in select boxes
     * @param dateMap Map of selectors/dates to use in the date fields.
     * @param selenium The selenium object used to fill out the form
     */
    public static void fillForm(int recordtype, String recordID, HashMap<String, String> fieldMap, HashMap<String, String> selectMap, HashMap<String, String> dateMap, Selenium selenium) {
        selenium.type(Record.getIDSelector(recordtype), recordID);

        //fill out all fields:
        Iterator<String> iterator = fieldMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            //System.out.println("changing " + selector + " to " + fieldMap.get(selector) + " modified");
            selenium.type(selector, fieldMap.get(selector));
        }
        //fill out all dates:
        iterator = dateMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            selenium.type(selector, dateMap.get(selector));
        }
        //select from all select boxes
        iterator = selectMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            selenium.select(selector, "label=" + selectMap.get(selector));
        }
    }

    /**
     * Checks all the fields of the loaded record against the default values of the
     * given recordType. Also checks that the ID of the record equals the value of
     * recordID.
     *
     * EXPECTS: that the record is loaded
     *
     * @param recordType The record type to verify
     * @param recordID The value expected in the ID field
     * @param selenium The selenium object used to fill out the form
     */
    public static void verifyFill(int recordType, String recordID, Selenium selenium) {
        verifyFill(recordType, recordID, Record.getFieldMap(recordType), Record.getSelectMap(recordType), Record.getDateMap(recordType), selenium);
    }

    /**
     * Checks all the fields of the loaded record against the values given as parameters.
     *
     * EXPECTS: that the record is loaded
     *
     * @param recordType The record type to fill out
     * @param recordID The value to put in the ID field
     * @param fieldMap Map of selectors/values to put in regular text fields and text areas
     * @param selectMap Map of selectors/values to use in select boxes
     * @param dateMap Map of selectors/dates to use in the date fields.
     * @param selenium The selenium object used to fill out the form
     */
    public static void verifyFill(int recordType, String recordID, HashMap<String, String> fieldMap, HashMap<String, String> selectMap, HashMap<String, String> dateMap, Selenium selenium) {
        assertEquals(recordID, selenium.getValue(Record.getIDSelector(recordType)));
        //check values:
        Iterator<String> iterator = fieldMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            assertEquals("checking for field: " + selector, fieldMap.get(selector), selenium.getValue(selector));
        }
        iterator = dateMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            assertEquals(dateMap.get(selector), selenium.getValue(selector));
        }
        iterator = selectMap.keySet().iterator();
        while (iterator.hasNext()) {
            String selector = iterator.next();
            assertEquals(selectMap.get(selector), selenium.getSelectedLabel(selector));
        }
    }

    public static void waitForRecordLoad(Selenium selenium) throws Exception {
        elementPresent("//input[@value='Select number pattern']", selenium);
    }

    public static void waitForRecordLoad(int recordType, Selenium selenium) throws Exception {
        waitForRecordLoad(selenium);
        elementPresent(Record.getIDSelector(recordType), selenium);
    }
    /**
     * Asserts that the text given as parameter is not present after MAX_WAIT_SEC
     *
     * @param text the text to check for whether is present
     * @param selenium a Selenium object to check with
     * @throws Exception
     */
    static final void textNotPresent(String text, Selenium selenium) throws Exception {
        for (int second = 0;; second++) {
            if (second >= MAX_WAIT_SEC) {
                fail("timeout");
            }
            try {
                if (!selenium.isTextPresent(text)) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
    }

    /**
     * Asserts that the text becomes present within MAX_WAIT_SEC
     *
     * @param text the text to check whether is present
     * @param selenium  a Selenium object to check with
     * @throws Exception
     */
    static final void textPresent(String text, Selenium selenium) throws Exception {
        for (int second = 0;; second++) {
            if (second >= MAX_WAIT_SEC) {
                fail("timeout");
            }
            try {
                if (selenium.isTextPresent(text)) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
    }

    /**
     * Asserts that the element is present within MAX_WAIT_SEC
     *
     * @param selector The selector for the element to check
     * @param selenium a Selenium object to check with
     * @throws Exception
     */
    static final void elementPresent(String selector, Selenium selenium) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 60) {
                fail("timeout");
            }
            try {
                if (selenium.isElementPresent(selector)) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
    }
    
    public static void log(String str) {
        System.out.print(str);

    }
}
