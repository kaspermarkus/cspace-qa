package org.collectionspace.qa;

import java.util.HashMap;
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


    public HashMap<String, String> fieldMap, selectMap, dateMap, vocabMap;
    
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

    private Record(int type) {
        this.type = type;
        initVars(type);
    }

    public static String getRequiredIDmessage(int recordType) {
        return records[recordType].requiredIDmessage;
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

    public static HashMap<String, String> getVocabMap(int recordType) {
        return records[recordType].vocabMap;
    }

    public void initVars(int type) {
        this.fieldMap = new HashMap<String, String>();
        this.selectMap = new HashMap<String, String>();
        this.dateMap = new HashMap<String, String>();
        this.vocabMap = new HashMap<String, String>();
        
        switch (type) {
            case CATALOGING:
            this.url = "cataloging.html";
                this.shortname = "cataloging";
                this.longname = "Cataloging";
                this.requiredIDmessage = "Please specify an Identification Number ";
                this.IDFieldSelector = ".csc-object-identification-object-number";

                break;
                
            case INTAKE:
            this.url = "intake.html";
                this.shortname = "intake";
                this.longname = "Intake";
                this.requiredIDmessage = "Please specify an Intake Entry Number";
                this.IDFieldSelector = ".csc-intake-entry-number";

		this.dateMap.put(".csc-intake-entry-date", "2011-05-04");
		this.dateMap.put(".csc-intake-returnDate", "2011-05-05");
		this.dateMap.put(".csc-intake-fieldCollectionDate", "2011-05-03");
		this.dateMap.put(".csc-intake-insurance-renewal-date", "2011-05-02");
		this.dateMap.put(".csc-intake-location-date", "2011-05-01");
		this.dateMap.put(".csc-intake-condition-check-date", "2011-05-06");
		this.selectMap.put(".csc-intake-entry-reason-selection", "label=Consideration");
		this.selectMap.put("repeat::.csc-intake-entryMethod", "label=Post");
		this.selectMap.put("repeat::.csc-intake-fieldCollectionMethod", "label=netted");
		this.selectMap.put("repeat::.csc-intake-current-location-fitness-selection", "label=Dangerous");
		this.selectMap.put("repeat::.csc-intake-conditionCheckMethod", "label=Observed");
		this.selectMap.put("repeat::.csc-intake-conditionCheckReason", "label=Consideration");
		this.selectMap.put("repeat::.csc-intake-conditionCheckReason", "label=Conservation");
		this.fieldMap.put(".csc-intake-entry-note", "Random entry note here");
		this.fieldMap.put(".csc-intake-packing-note", "Some packing note goes here");
		this.fieldMap.put(".csc-intake-depositor-requirements", "Always Requirements... sigh");
		this.fieldMap.put(".csc-intake-fieldCollectionPlace", "Who Knows");
		this.fieldMap.put(".csc-intake-fieldCollectionNote", "Yadi yada - note goes here");
		this.fieldMap.put(".csc-intake-fieldCollectionNumber", "Some FC number");
		this.fieldMap.put(".csc-intake-valuation-reference-number", "Reference number here");
		this.fieldMap.put(".csc-intake-insurance-reference-number", "..And yet another reference number");
		this.fieldMap.put(".csc-intake-insurance-policy-number", "1234567890 and then some");
		this.fieldMap.put(".csc-intake-insurance-note", "Too expensive to be insured");
		this.fieldMap.put("repeat::.csc-intake-current-location", "Under the bridge");
		this.fieldMap.put("repeat::.csc-intake-current-location-note", "Beware of trolls");
		this.fieldMap.put(".csc-intake-normal-location", "The Moon");
		this.fieldMap.put(".csc-intake-condition-check-reference-number", "855-check cond-10101010101");
		this.fieldMap.put(".csc-intake-condition-check-note", "Looks fine and dandy");
		this.fieldMap.put("repeat::.csc-intake-field-collection-event-name", "Ooooops, thought I forgot this one?!");
		this.vocabMap.put(".csc-intake-current-owner", "VOCAB");
		this.vocabMap.put(".csc-intake-depositor", "VOCAB");
		this.vocabMap.put("repeat::.csc-intake-fieldCollectionSource", "VOCAB");
		this.vocabMap.put("repeat::.csc-intake-fieldCollector", "VOCAB");
		this.vocabMap.put(".csc-intake-valuer", "VOCAB");
		this.vocabMap.put("repeat::.csc-intake-insurer", "VOCAB");
		this.vocabMap.put("repeat::.csc-intake-conditionCheckerOrAssessor", "VOCAB");
                break;

            case LOAN_IN:
                this.url = "loanin.html";
                this.shortname = "loanin";
                this.longname = "Loan In";
                this.IDFieldSelector = ".csc-loanIn-loanInNumber";
                this.requiredIDmessage = "Please specify a Loan In Number";
                
		this.fieldMap.put(".csc-loanIn-loanInNote", "Some Loan in Note");
		this.fieldMap.put(".csc-loanIn-loanInConditions", "Some Conditions");

                this.selectMap.put(".csc-loanIn-loanPurpose-selection", "label=Photography");

                this.vocabMap.put("repeat::.csc-loanIn-lendersContact", "VOCAB");
		this.vocabMap.put("repeat::.csc-loanIn-lender", "VOCAB");
		this.vocabMap.put("repeat::.csc-loanIn-lendersAuthorizer", "VOCAB");
		this.vocabMap.put(".csc-loanIn-loanInContact", "VOCAB");
		this.vocabMap.put(".csc-loanin-borrowersAuthorizer", "VOCAB");

		this.dateMap.put("repeat::.csc-loanIn-lendersAuthorizerDate", "2011-05-05");
		this.dateMap.put(".csc-loanIn-loanRenewalApplicationDate", "2011-05-05");
		this.dateMap.put(".csc-loanIn-loanReturnDate", "2011-05-05");
		this.dateMap.put(".csc-loanIn-loanInDate", "2011-05-04");
		this.dateMap.put(".csc-loanIn-loanReturnDate", "2011-05-03");
		this.dateMap.put(".csc-loanIn-loanRenewalApplicationDate", "2011-05-02");
                break;

            case LOAN_OUT:
                this.url = "loanout.html";
                this.shortname = "loanout";
                this.longname = "Loan Out";
                this.IDFieldSelector = ".csc-loanOut-loanOutNumber";
                this.requiredIDmessage = "Please specify a Loan Out Number";

                this.fieldMap.put(".csc-loanOut-loanOutNote", "Some loan out note");
                this.fieldMap.put(".csc-loanOut-loanOutConditions", "Some conditions");
                this.fieldMap.put("repeat::.csc-loanOut-loanedObjectStatusNote", "yadi yada");

                this.dateMap.put(".csc-loanOut-lendersAuthorizationDate", "2011-04-12");
                this.dateMap.put("repeat::.csc-loanOut-loanedObjectStatusDate", "2011-04-13");
                this.dateMap.put("repeat::.csc-loanOut-loanedObjectStatusDate", "2011-04-14");
                this.dateMap.put(".csc-loanOut-loanOutDate", "2011-04-15");
                this.dateMap.put(".csc-loanOut-loanReturnDate", "2011-04-16");
                this.dateMap.put(".csc-loanOut-loanRenewalApplicationDate", "2010-04-17");

                this.vocabMap.put(".csc-loanOut-borrower", "VOCAB");
                this.vocabMap.put(".csc-loanOut-borrowersContact", "VOCAB");
                this.vocabMap.put(".csc-loanOut-lendersAuthorizer", "VOCAB");
                this.vocabMap.put(".csc-loanOut-lendersContact", "VOCAB");

                this.selectMap.put(".csc-loanOut-loanPurpose-selection", "Analysis");
                this.selectMap.put("repeat::.csc-loanOut-loanedObjectStatus-selection", "Photography requested");
                break;
              
            case ACQUISITION:
                this.url = "acquisition.html";
                this.shortname = "acquisition";
                this.longname = "Acquisition";
                this.IDFieldSelector = ".csc-acquisition-numberPatternChooser-reference-number";
                this.requiredIDmessage = "Please specify an Acquisition Reference Number";

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

                this.vocabMap.put(".csc-acquisition-acquisitionAuthorizer", "VOCAB");
                this.vocabMap.put("repeat::.csc-acquisition-acquisitionSource", "VOCAB");
                this.vocabMap.put("repeat::.csc-acquisition-owner", "VOCAB");
                this.vocabMap.put("repeat::.csc-acquisition-acquisitionFundingSource", "VOCAB");

                this.selectMap.put(".csc-acquisition-acquisition-method-selection", "Exchange");
                this.selectMap.put(".csc-acquisition-group-purchase-price-currency-selection", "Euro");
                this.selectMap.put(".csc-acquisition-object-offer-price-currency-selection", "Pound Sterling");
                this.selectMap.put(".csc-acquisition-object-purchase-offer-price-currency-selection", "Danish Krone");
                this.selectMap.put(".csc-acquisition-object-purchase-price-currency-selection", "Danish Krone");
                this.selectMap.put(".csc-acquisition-original-object-purchase-price-currency-selection", "Canadian Dollar");
                this.selectMap.put("repeat::.csc-acquisition-acquisitionFundingCurrency-selection", "Danish Krone");
                break;
                                
            case MOVEMENT:
                this.url = "movement.html";
                this.shortname = "movement";
                this.longname = "Location and Movement";
                this.IDFieldSelector = ".csc-movement-currentLocation";
                this.requiredIDmessage = "Please specify Current Location";

		this.vocabMap.put(".csc-movement-removalDate", "2011-05-05");
		this.vocabMap.put(".csc-movement-plannedRemovalDate", "2011-05-04");
		this.vocabMap.put(".csc-movement-locationDate", "2011-05-03");
		this.fieldMap.put(".csc-movement-currentLocationNote", "Random note");
		this.fieldMap.put(".csc-movement-normalLocation", "Under the big table");
		this.fieldMap.put(".csc-movement-movementReferenceNumber", "some ref number");
		this.fieldMap.put(".csc-movement-movementNote", "Another random note");
		this.selectMap.put(".csc-movement-currentLocationFitness-selection", "label=Temporary");
		this.selectMap.put("repeat::.csc-movement-movementMethods", "label=Handcarried");
		this.selectMap.put(".csc-movement-reasonForMove-selection", "label=Inventory");
		this.vocabMap.put(".csc-movement-movementContact", "VOCAB");
                break;

            case MEDIA:
                this.url = "Media.html";
                this.shortname = "media";
                this.longname = "Media Handling";
                this.IDFieldSelector = ".csc-media-identificationNumber";
                this.requiredIDmessage = "Please specify an Identification Number";
                
		this.vocabMap.put("repeat::.csc-media-valueDate", "2011-05-01");
		this.vocabMap.put("repeat::.csc-media-date", "2011-05-02");
		this.fieldMap.put(".csc-media-title", "Mediarrrrgh");
		this.fieldMap.put(".csc-media-dimensionSummary", "No clue");
		this.fieldMap.put("repeat::.csc-media-value", "22");
		this.fieldMap.put("repeat::.csc-media-valueQualifier", "karat?");
		this.fieldMap.put(".csc-media-coverage", "All over");
		this.fieldMap.put(".csc-media-source", "Someone somewhere in the mountains");
		this.fieldMap.put("repeat::.csc-media-relation", "None");
		this.fieldMap.put("repeat::.csc-media-subject", "Got no idea");
		this.fieldMap.put(".csc-media-copyrightStatement", "Use at will");
		this.fieldMap.put(".csc-media-description", "This is some random media ...");
		this.selectMap.put("repeat::.csc-media-dimension-selection", "label=Running Time");
		this.selectMap.put("repeat::.csc-media-measurementMethod-selection", "label=Protractor");
		this.selectMap.put("repeat::.csc-media-measurementUnit-selection", "label=Millimeters");
		this.selectMap.put("repeat::.csc-media-type", "label=Document");
		this.selectMap.put("repeat::.csc-media-language", "label=Russian");
		this.vocabMap.put("repeat::.csc-media-measuredBy", "VOCAB");
		this.vocabMap.put(".csc-media-contributor", "VOCAB");
		this.vocabMap.put(".csc-media-creator", "VOCAB");
		this.vocabMap.put(".csc-media-publisher", "VOCAB");
		this.vocabMap.put(".csc-media-rightsHolder", "VOCAB");
                break;
                
            case OBJECT_EXIT:
                this.url = "objectexit.html";
                this.shortname = "objectexit";
                this.longname = "Object Exit";
                this.IDFieldSelector = ".csc-objectexit-exitNumber";
                this.requiredIDmessage = "Please specify Exit Number ";

                this.dateMap.put(".csc-objectexit-exitDate", "2011-05-05");

                this.fieldMap.put(".csc-objectexit-exitNote", "Goodbye birdie");
		this.fieldMap.put(".csc-objectexit-packingNote", "Foam and Cardboard");

                this.selectMap.put(".csc-objectexit-exitReason-selection", "label=Disposal");
		this.selectMap.put("repeat::.csc-objectexit-exitMethods", "label=In Person");

                this.vocabMap.put(".csc-objectexit-currentOwner", "VOCAB");
		this.vocabMap.put(".csc-objectexit-depositor", "VOCAB");

                break;

            default:

                System.out.println("NOT IMPLEMENTED YET");
        }
    }
}
