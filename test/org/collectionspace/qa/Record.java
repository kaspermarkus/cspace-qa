package org.collectionspace.qa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Record {

    public static final int CATALOGING = 0,
            INTAKE = 1,
            LOAN_IN = 2,
            LOAN_OUT = 3,
            ACQUISITION = 4,
            MOVEMENT = 5,
            MEDIA = 6,
            OBJECT_EXIT = 7;
    public static final int numTypes = 8;

    public int type;
    public String url, shortname, longname, IDFieldSelector, defaultID, requiredIDmessage;
    public HashMap<String, String> fieldMap, selectMap, dateMap;
    public List<String> vocabList;
    
    private static Record[] records = {
        new Record(INTAKE),
        new Record(LOAN_IN),
        new Record(CATALOGING),
        new Record(LOAN_OUT),
        new Record(ACQUISITION),
        new Record(MOVEMENT),
        new Record(MEDIA),
        new Record(OBJECT_EXIT)
    };

    public Record(int type) {
        this.type = type;
        initVars(type);
    }

    static String getRecordTypeShort(int recordType) {
        return records[recordType].shortname;
    }
    public static String getRecordTypePP(int recordType) {
        return records[recordType].longname;
    }

    public static String getIDSelector(int recordType) {
        return records[recordType].IDFieldSelector;
    }

    public static HashMap<String, String> getSelectMap(int recordType) {
        return records[recordType].selectMap;
    }

    public static HashMap<String, String> getFieldMap(int recordType) {
        return records[recordType].fieldMap;
    }

    public static HashMap<String, String> getDateMap(int recordType) {
        return records[recordType].dateMap;
    }

    public static List<String> getVocabList(int recordType) {
        return records[recordType].vocabList;
    }

    public void initVars(int type) {
        this.fieldMap = new HashMap<String, String>();
        this.selectMap = new HashMap<String, String>();
        this.dateMap = new HashMap<String, String>();
        this.vocabList = new LinkedList<String>();
        
        switch (type) {
            case 3:
                this.url = "loanout.html";
                this.shortname = "loanout";
                this.longname = "Loan Out";
                this.IDFieldSelector = ".csc-loanOut-loanOutNumber";
                this.defaultID = this.shortname + (new Random()).nextInt(Integer.MAX_VALUE);
                this.requiredIDmessage = "Please specify a Loan Out Number";
                //fieldMap.put(IDFieldSelector, defaultID);
                this.fieldMap.put(".csc-loanOut-loanOutNote", "Some loan out note");
                this.fieldMap.put(".csc-loanOut-loanOutConditions", "Some conditions");
                this.fieldMap.put("repeat::.csc-loanOut-loanedObjectStatusNote", "yadi yada");

                this.dateMap.put(".csc-loanOut-lendersAuthorizationDate", "2011-04-12");
                this.dateMap.put("repeat::.csc-loanOut-loanedObjectStatusDate", "2011-04-13");
                this.dateMap.put("repeat::.csc-loanOut-loanedObjectStatusDate", "2011-04-14");
                this.dateMap.put(".csc-loanOut-loanOutDate", "2011-04-15");
                this.dateMap.put(".csc-loanOut-loanReturnDate", "2011-04-16");
                this.dateMap.put(".csc-loanOut-loanRenewalApplicationDate", "2010-04-17");

                this.selectMap.put(".csc-loanOut-loanPurpose-selection", "Analysis");
                this.selectMap.put("repeat::.csc-loanOut-loanedObjectStatus-selection", "Photography requested");
                break;

            case 4:
                this.url = "acquisition.html";
                this.shortname = "acquisition";
                this.longname = "Acquisition";
                this.IDFieldSelector = ".csc-acquisition-numberPatternChooser-reference-number";
                this.defaultID = this.shortname + (new Random()).nextInt(Integer.MAX_VALUE);
                this.requiredIDmessage = "Please specify an Acquisition Reference Number";
                //fieldMap.put(IDFieldSelector, defaultID);
                this.fieldMap.put(".csc-acquisition-transfer-of-title-number", "Title Number");
                this.fieldMap.put(".csc-acquisition-group-purchase-price-value", "Price Value");
                this.fieldMap.put(".csc-acquisition-object-offer-price-value", "Offer Price");
                this.fieldMap.put(".csc-acquisition-object-purchase-offer-price-value", "Purchase Offer price");
                this.fieldMap.put(".csc-acquisition-object-purchase-price-value", "purchase price val");
                this.fieldMap.put(".csc-acquisition-original-object-purchase-price-value", "obj Purchase price");
                this.fieldMap.put(".csc-acquisition-acquisition-reason", "Cause I wanted it");
                this.fieldMap.put(".csc-acquisition-acquisition-note", "Here I put my notes");
                this.fieldMap.put(".csc-acquisition-acquisition-provisos", "Yes Please");
                this.fieldMap.put("repeat::.csc-acquisition-acquisitionFundingValue", "lots");
                this.fieldMap.put("repeat::.csc-acquisition-acquisitionFundingSourceProvisos", "Sure");
                this.fieldMap.put(".csc-acquisition-creditLine", "also yes");
                this.fieldMap.put("repeat::.csc-acquisition-fieldCollectionEventName", "Abracadabre");

                this.dateMap.put(".csc-acquisition-accession-date", "2011-04-19");
                this.dateMap.put(".csc-acquisition-acquisitionAuthorizerDate", "2011-04-20");
                this.dateMap.put("repeat::.csc-acquisition-date", "2011-04-21");

                this.selectMap.put(".csc-acquisition-acquisition-method-selection", "Exchange");
                this.selectMap.put(".csc-acquisition-group-purchase-price-currency-selection", "Euro");
                this.selectMap.put(".csc-acquisition-object-offer-price-currency-selection", "Pound Sterling");
                this.selectMap.put(".csc-acquisition-object-purchase-offer-price-currency-selection", "Danish Krone");
                this.selectMap.put(".csc-acquisition-object-purchase-price-currency-selection", "Danish Krone");
                this.selectMap.put(".csc-acquisition-original-object-purchase-price-currency-selection", "Canadian Dollar");
                this.selectMap.put("repeat::.csc-acquisition-acquisitionFundingCurrency-selection", "Danish Krone");
                break;

            default:

                System.out.println("NOT IMPLEMENTED YET");
        }
    }
}
