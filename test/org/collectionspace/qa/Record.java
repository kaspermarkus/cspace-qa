package org.collectionspace.qa;

import java.util.HashMap;
import java.util.Iterator;

public class Record {

    public static final int CATALOGING = 0,
            INTAKE = 1,
            LOAN_IN = 2,
            LOAN_OUT = 3,
            ACQUISITION = 4,
            MOVEMENT = 5,
            MEDIA = 6,
            OBJECT_EXIT = 7;

    public int type;
    public String url, shortname, shortChainName, longname, IDFieldSelector, requiredFieldSelector, requiredFieldMessage, generatedPostfix;


    public HashMap<String, String> fieldMap, selectMap, dateMap, vocabMap;
    
    private static Record[] records = {
        new Record(CATALOGING),
        new Record(INTAKE),
        new Record(LOAN_IN),
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

    public static String getUrl(int recordType) {
        return records[recordType].url;
    }

    public static String getRequiredFieldSelector(int recordType) {
        return records[recordType].requiredFieldSelector;
    }

    public static String getRequiredFieldMessage(int recordType) {
        return records[recordType].requiredFieldMessage;
    }

    static String getRecordTypeShort(int recordType) {
        return records[recordType].shortname;
    }

    static String getRecordTypeShortChain(int recordType) {
        Record rec = records[recordType];
        return (rec.shortChainName != null) ? rec.shortChainName : rec.shortname;
    }
    public static String getRecordTypePP(int recordType) {
        return records[recordType].longname;
    }

    public static String getGeneratedPostfix(int recordType) {
        return records[recordType].generatedPostfix;
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
    
    public static void setField(int recordType, String selector, String value) {
        HashMap<String, String> map = records[recordType].fieldMap;
//        System.out.println("set field called.. selector "+selector+", value: "+value);
//        System.out.println("looking in map of size "+        map.size());
        map.put(selector, value);
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
                this.shortChainName = "collection-object";
                this.requiredFieldMessage = "Please specify an Identification Number";
                this.IDFieldSelector = ".csc-object-identification-object-number";
                this.requiredFieldSelector = ".csc-object-identification-object-number";
                this.generatedPostfix = " - objectNumber";

		this.selectMap.put("repeat::.csc-object-identification-responsible-department", "Media and Performance Art");
		this.selectMap.put(".csc-object-identification-collection-selection", "Permanent collection");
		this.selectMap.put("repeat::.csc-object-identification-other-number-type-selection", "Lender");
		this.selectMap.put(".csc-object-identification-record-status-selection", "In process");
		this.selectMap.put("repeat::.csc-object-identification-object-title-language-selection", "Arabic");
		this.selectMap.put("repeat::.csc-collection-object-titleTranslationLanguage-selection", "Korean");
		this.selectMap.put("repeat::.csc-object-identification-object-title-type-selection", "Series");
		this.selectMap.put("repeat::.csc-object-identification-object-currency-selection", "Current");
		this.selectMap.put("repeat::.csc-object-identification-object-level-selection", "Subgroup");
		this.selectMap.put("repeat::.csc-object-identification-object-system-selection", "Nomenclature");
		this.selectMap.put("repeat::.csc-object-identification-object-type-selection", "Denomination");
		this.selectMap.put("repeat::.csc-object-identification-object-language-selection", "Chinese");
		this.selectMap.put("repeat::.csc-object-description-object-status", "holotype");
		this.selectMap.put(".csc-object-description-sex-selection", "female");
		this.selectMap.put(".csc-object-description-phase-selection", "imago");
		this.selectMap.put("repeat::.csc-object-description-form", "pinned");
		this.selectMap.put(".csc-object-description-age-unit-selection", "Months");
		this.selectMap.put("repeat::.csc-object-description-object-component-name-selection", "handle");
		this.selectMap.put("repeat::.csc-object-description-technical-attribute-selection", "magnetic tape type");
		this.selectMap.put("repeat::.csc-object-description-technical-attribute-measurement-selection", "metal");
		this.selectMap.put("repeat::.csc-object-description-technical-attribute-unit-selection", "rpm");
		this.selectMap.put("repeat::.csc-object-description-dimension-unit-selection", "minutes");
		this.selectMap.put("repeat::.csc-object-description-dimension-selection", "length");
		this.selectMap.put("repeat::.csc-object-description-dimension-part-selection", "image size");
		this.selectMap.put("repeat::.csc-object-description-content-language", "Swahili");
		this.selectMap.put("repeat::.csc-object-description-content-script", "Roman cursive");
		this.selectMap.put("repeat::.csc-object-description-content-object-type-selection", "Furniture");
		this.selectMap.put("repeat::.csc-object-description-content-position", "front");
		this.selectMap.put(".csc-object-description-inscription-content-language-selection", "Korean");
		this.selectMap.put(".csc-object-description-inscription-content-position-selection", "inside");
		this.selectMap.put(".csc-object-description-inscription-content-script-selection", "Roman cursive");
		this.selectMap.put(".csc-object-description-inscription-content-type-selection", "estate stamp");
		this.selectMap.put(".csc-object-description-inscription-description-position-selection", "outside");
		this.selectMap.put(".csc-object-description-inscription-description-type-selection", "maker's mark");
		this.selectMap.put(".csc-object-description-inscription-description-type-selection", "estate stamp");
		this.selectMap.put(".csc-object-history-association-access-selection", "open");
		this.selectMap.put(".csc-object-history-association-category-selection", "public");
		this.selectMap.put(".csc-object-history-association-denomination-selection", "Euro");
		this.selectMap.put(".csc-object-history-association-exchange-method-selection", "purchase");
		this.selectMap.put("repeat::.csc-collection-object-fieldCollectionMethod", "purchased");
		this.fieldMap.put("repeat::.csc-object-identification-brief-description", "Auch, cataloging.. Loooooooong way to go");
		this.fieldMap.put(".csc-object-identification-distinguishing-features", "It's a biiiiig page with lots of fields");
		this.fieldMap.put("repeat::.csc-object-identification-comments", "Ugh, and I'm at third textarea");
		this.fieldMap.put(".csc-object-identification-number-objects", "probably 800");
		this.fieldMap.put("repeat::.csc-object-identification-other-number", "ok, 801");
		this.fieldMap.put("repeat::.csc-object-identification-object-title", "the is cataloging");
		this.fieldMap.put("repeat::.csc-object-identification-object-title-translation", "cataloooooging");
		this.fieldMap.put("repeat::.csc-object-identification-object-name", "No idea");
		this.fieldMap.put("repeat::.csc-object-identification-object-note", "Objects have lots of fields");
		this.fieldMap.put(".csc-object-description-copy-number", "woohoo, second box");
		this.fieldMap.put(".csc-object-description-edition-number", "second field of second box");
		this.fieldMap.put(".csc-object-description-age", "about 2 years");
		this.fieldMap.put(".csc-object-description-age-qualifier", "cause i say so");
		this.fieldMap.put("repeat::.csc-object-description-style", "biiiig");
		this.fieldMap.put("repeat::.csc-object-description-color", "gray/whitish");
		this.fieldMap.put("repeat::.csc-object-description-material", "Computer");
		this.fieldMap.put("repeat::.csc-object-description-material-component", "lots");
		this.fieldMap.put("repeat::.csc-object-description-material-component-note", "even more omponents than fields");
		this.fieldMap.put("repeat::.csc-object-description-material-name", "computer");
		this.fieldMap.put("repeat::.csc-object-description-material-source", "toshiba");
		this.fieldMap.put(".csc-object-description-physical-description", "very biiiig");
		this.fieldMap.put("repeat::.csc-object-description-object-component-information", "yes, please");
		this.fieldMap.put(".csc-collection-object-dimensionSummary", "I said it was huuuuge");
		this.fieldMap.put("repeat::.csc-object-description-dimension-value", "1000");
		this.fieldMap.put("repeat::.csc-object-description-dimension-value-qualifier", "no way");
		this.fieldMap.put(".csc-object-description-content-description", "Just a lot of fields");
		this.fieldMap.put("repeat::.csc-object-description-content-activity", "fill them out for testing");
		this.fieldMap.put("repeat::.csc-object-description-content-concept", "Automatisk QA");
		this.fieldMap.put("repeat::.csc-object-description-content-place", "On my computer");
		this.fieldMap.put("repeat::.csc-object-description-content-object", "Cataloging form");
		this.fieldMap.put("repeat::.csc-object-description-content-people", "Me, myself and I");
		this.fieldMap.put("repeat::.csc-object-description-content-event-name", "auto QA");
		this.fieldMap.put("repeat::.csc-object-description-content-event-name-type", "testing");
		this.fieldMap.put("repeat::.csc-object-description-content-other", "filling out fields");
		this.fieldMap.put("repeat::.csc-object-description-content-event-name", "auto QAs");
		this.fieldMap.put("repeat::.csc-object-description-content-other-type", "same same but different");
		this.fieldMap.put(".csc-object-description-content-note", "Ugh, not even half way through the form");
		this.fieldMap.put(".csc-object-description-inscription-content", "Just complaints of how long this form is");
		this.fieldMap.put(".csc-object-description-inscription-content-method", "bits and pieces");
		this.fieldMap.put(".csc-object-description-inscription-content-interpretation", "A very long form");
		this.fieldMap.put(".csc-object-description-inscription-content-translation", "Donde esta la biblioteca");
		this.fieldMap.put(".csc-object-description-inscription-content-transliteration", "That's not even a word");
		this.fieldMap.put(".csc-object-description-inscription-description", "Some blocks with fields");
		this.fieldMap.put(".csc-object-description-inscription-description-method", "Filling them out");
		this.fieldMap.put(".csc-object-description-inscription-description-interpretation", "No idea");
		this.fieldMap.put("repeat::.csc-object-production-people", "Wooohoooo");
		this.fieldMap.put("repeat::.csc-collection-object-objectProductionPeopleRole", "Made it to next box");
		this.fieldMap.put("repeat::.csc-object-production-technique", "and from the look of the scroller");
		this.fieldMap.put("repeat::.csc-object-production-technique-type", "I'm almost half way");
		this.fieldMap.put("repeat::.csc-collection-object-objectProductionPersonRole", "I really hope the fields");
		this.fieldMap.put("repeat::.csc-object-production-place", "Below are bigger than these");
		this.fieldMap.put("repeat::.csc-collection-object-objectProductionPlaceRole", "Huuuuuge textfields");
		this.fieldMap.put("repeat::.csc-collection-object-objectProductionOrganizationRole", "that take up the whole page");
		this.fieldMap.put("repeat::.csc-object-production-reason", "to store cataloging records");
		this.fieldMap.put(".csc-object-production-note", "None, relaly");
		this.fieldMap.put("repeat::.csc-collection-object-assocActivity", "Ugh");
		this.fieldMap.put("repeat::.csc-collection-object-assocActivityType", "More small fields");
		this.fieldMap.put("repeat::.csc-collection-object-assocActivityNote", "I count 14 of them");
		this.fieldMap.put("repeat::.csc-collection-object-assocObject", "and that's on this side");
		this.fieldMap.put("repeat::.csc-collection-object-assocObjectType", "only, and looks like");
		this.fieldMap.put("repeat::.csc-collection-object-assocObjectNote", "there are more below");
		this.fieldMap.put("repeat::.csc-collection-object-assocConcept", "let me count");
		this.fieldMap.put("repeat::.csc-collection-object-assocConceptType", "one");
		this.fieldMap.put("repeat::.csc-collection-object-assocConceptNote", "two");
		this.fieldMap.put("repeat::.csc-collection-object-assocCulturalContext", "three");
		this.fieldMap.put("repeat::.csc-collection-object-assocCulturalContextType", "four");
		this.fieldMap.put("repeat::.csc-collection-object-assocCulturalContextNote", "five");
		this.fieldMap.put("repeat::.csc-collection-object-assocOrganizationType", "six");
		this.fieldMap.put("repeat::.csc-collection-object-assocOrganizationNote", "seven");
		this.fieldMap.put("repeat::.csc-collection-object-assocPeople", "eight");
		this.fieldMap.put("repeat::.csc-collection-object-assocPeopleType", "nine");
		this.fieldMap.put("repeat::.csc-collection-object-assocPeopleNote", "ten");
		this.fieldMap.put("repeat::.csc-collection-object-assocPersonType", "eleven");
		this.fieldMap.put("repeat::.csc-collection-object-assocPersonNote", "twelve");
		this.fieldMap.put("repeat::.csc-collection-object-assocPlace", "Thirteen");
		this.fieldMap.put("repeat::.csc-collection-object-assocPlaceType", "fourteen");
		this.fieldMap.put("repeat::.csc-collection-object-assocPlaceNote", "eighteen");
		this.fieldMap.put(".csc-collection-object-assocEventName", "argh, ok cheated");
		this.fieldMap.put(".csc-collection-object-assocEventNameType", "got bored with counting");
		this.fieldMap.put("repeat::.csc-object-history-association-event-people", "wonder how many fields are left");
		this.fieldMap.put("repeat::.csc-object-history-association-event-place", "200 fields left perhaps");
		this.fieldMap.put(".csc-collection-object-assocEventNote", "I hope less");
		this.fieldMap.put("repeat::.csc-collection-object-assocDateType", "But you never no");
		this.fieldMap.put("repeat::.csc-collection-object-assocDateNote", "Never ever");
		this.fieldMap.put(".csc-object-history-association-object-history-note", "Wohooo... big field");
		this.fieldMap.put("repeat::.csc-collection-object-usage", "and some smaller");
		this.fieldMap.put("repeat::.csc-collection-object-usageNote", "down here");
		this.fieldMap.put(".csc-object-history-association-ownership-place", "Japan");
		this.fieldMap.put(".csc-object-history-association-exchange-note", "note here");
		this.fieldMap.put(".csc-object-history-association-exchange-price-value", "and price - 20000000000000");
		this.fieldMap.put(".csc-object-owner-experience", "almost at the end of this form");
		this.fieldMap.put(".csc-object-owner-response", "Congratulations");
		this.fieldMap.put("repeat::.csc-object-owner-reference", "so close");
		this.fieldMap.put(".csc-object-owner-contribution-note", "I think there's less than 5 fields left");
		this.fieldMap.put(".csc-object-viewer-role", "argh, ok, that was optimistic");
		this.fieldMap.put(".csc-object-viewer-experience", "some experience");
		this.fieldMap.put(".csc-object-viewer-response", "some response");
		this.fieldMap.put("repeat::.csc-object-viewer-reference", "What's up with all these references");
		this.fieldMap.put(".csc-object-viewer-contribution-note", "Good job");
		this.fieldMap.put("repeat::.csc-collection-object-reference", "Reference again?");
		this.fieldMap.put("repeat::.csc-collection-object-referenceNote", "why are there so many of these");
		this.fieldMap.put(".csc-collection-object-fieldCollectionPlace", "Holland maybe?");
		this.fieldMap.put("repeat::.csc-collection-object-fieldColEventName", "Oooooh, three left");
		this.fieldMap.put(".csc-collection-object-fieldCollectionNote", "Sooooooo close");
		this.fieldMap.put(".csc-collection-object-fieldCollectionNumber", "Congratulations!");
		this.dateMap.put("repeat::.csc-object-description-dimension-value-date", "2011-05-01");
		this.dateMap.put(".csc-object-description-content-date", "2011-05-02");
		this.dateMap.put(".csc-object-description-inscription-content-date", "2011-05-03");
		this.dateMap.put(".csc-object-description-inscription-description-date", "2011-05-04");
		this.dateMap.put(".csc-object-production-date", "2011-05-05");
		this.dateMap.put("repeat::.csc-object-history-association-assocDate", "2011-05-06");
		this.dateMap.put(".csc-object-history-association-ownershipDates", "2011-05-07");
		this.dateMap.put(".csc-collection-object-fieldCollectionDate", "2011-05-09");
		this.vocabMap.put("repeat::.csc-object-description-content-person", "VOCAB");
		this.vocabMap.put("repeat::.csc-object-description-content-organization", "VOCAB");
		this.vocabMap.put(".csc-object-description-inscription-content-inscriber", "VOCAB");
		this.vocabMap.put(".csc-object-description-inscription-description-inscriber", "VOCAB");
		this.vocabMap.put("repeat::.csc-object-production-person", "VOCAB");
		this.vocabMap.put("repeat::.csc-object-production-organization", "VOCAB");
		this.vocabMap.put("repeat::.csc-object-history-association-event-organization", "VOCAB");
		this.vocabMap.put("repeat::.csc-object-history-association-event-person", "VOCAB");
		this.vocabMap.put("repeat::.csc-collection-object-assocOrganization", "VOCAB");
		this.vocabMap.put("repeat::.csc-collection-object-assocPerson", "VOCAB");
		this.vocabMap.put("repeat::.csc-object-history-association-owner", "VOCAB");
		this.vocabMap.put("repeat::.csc-collection-object-fieldCollectionSource", "VOCAB");
		this.vocabMap.put("repeat::.csc-collection-object-fieldCollector", "VOCAB");
                break;
                
            case INTAKE:
            this.url = "intake.html";
                this.shortname = "intake";
                this.longname = "Intake";
                this.requiredFieldMessage = "Please specify an Intake Entry Number";
                this.IDFieldSelector = ".csc-intake-entry-number";
                this.requiredFieldSelector = ".csc-intake-entry-number";
                this.generatedPostfix = " - entryNumber";

		this.dateMap.put(".csc-intake-entry-date", "2011-05-04");
		this.dateMap.put(".csc-intake-returnDate", "2011-05-05");
		this.dateMap.put(".csc-intake-fieldCollectionDate", "2011-05-03");
		this.dateMap.put(".csc-intake-insurance-renewal-date", "2011-05-02");
		this.dateMap.put(".csc-intake-location-date", "2011-05-01");
		this.dateMap.put(".csc-intake-condition-check-date", "2011-05-06");
		this.selectMap.put(".csc-intake-entry-reason-selection", "Consideration");
		this.selectMap.put("repeat::.csc-intake-entryMethod", "Post");
		this.selectMap.put("repeat::.csc-intake-fieldCollectionMethod", "netted");
		this.selectMap.put("repeat::.csc-intake-current-location-fitness-selection", "Dangerous");
		this.selectMap.put("repeat::.csc-intake-conditionCheckMethod", "Observed");
		this.selectMap.put("repeat::.csc-intake-conditionCheckReason", "Consideration");
		this.selectMap.put("repeat::.csc-intake-conditionCheckReason", "Conservation");
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
                this.requiredFieldSelector = ".csc-loanIn-loanInNumber";
                this.requiredFieldMessage = "Please specify a Loan In Number";
                this.generatedPostfix = " - loanInNumber";

		this.fieldMap.put(".csc-loanIn-loanInNote", "Some Loan in Note");
		this.fieldMap.put(".csc-loanIn-loanInConditions", "Some Conditions");

                this.selectMap.put(".csc-loanIn-loanPurpose-selection", "Photography");

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
                this.requiredFieldSelector = ".csc-loanOut-loanOutNumber";
                this.requiredFieldMessage = "Please specify a Loan Out Number";
                this.generatedPostfix = " - loanOutNumber";

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
                this.requiredFieldSelector = ".csc-acquisition-numberPatternChooser-reference-number";
                this.requiredFieldMessage = "Please specify an Acquisition Reference Number";
                this.generatedPostfix = " - acquisitionReferenceNumber";

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
                this.IDFieldSelector = ".csc-movement-movementReferenceNumber";
                this.requiredFieldSelector = ".csc-movement-currentLocation";
                this.requiredFieldMessage = "Please specify a Current Location";
                this.generatedPostfix = " - movementReferenceNumber";

		this.dateMap.put(".csc-movement-removalDate", "2011-05-05");
		this.dateMap.put(".csc-movement-plannedRemovalDate", "2011-05-04");
		this.dateMap.put(".csc-movement-locationDate", "2011-05-03");
		this.fieldMap.put(".csc-movement-currentLocationNote", "Random note");

		this.fieldMap.put(".csc-movement-movementNote", "Another random note");
                this.fieldMap.put(".csc-movement-currentLocation", "some ref number");                
                
		this.selectMap.put(".csc-movement-currentLocationFitness-selection", "Temporary");
		this.selectMap.put("repeat::.csc-movement-movementMethods", "Handcarried");
		this.selectMap.put(".csc-movement-reasonForMove-selection", "Inventory");

                this.vocabMap.put(".csc-movement-normalLocation", "VOCAB");		
		this.vocabMap.put(".csc-movement-movementContact", "VOCAB");
                break;

            case MEDIA:
                this.url = "Media.html";
                this.shortname = "media";
                this.longname = "Media Handling";
                this.IDFieldSelector = ".csc-media-identificationNumber";
                this.requiredFieldSelector = ".csc-media-identificationNumber";
                this.requiredFieldMessage = "Please specify an Identification Number";
                this.generatedPostfix = " - identificationNumber";

		this.dateMap.put("repeat::.csc-media-valueDate", "2011-05-01");
		this.dateMap.put("repeat::.csc-media-date", "2011-05-02");
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
		this.selectMap.put("repeat::.csc-media-dimension-selection", "Running Time");
		this.selectMap.put("repeat::.csc-media-measurementMethod-selection", "Protractor");
		this.selectMap.put("repeat::.csc-media-measurementUnit-selection", "Millimeters");
		this.selectMap.put("repeat::.csc-media-type", "Document");
		this.selectMap.put("repeat::.csc-media-language", "Russian");
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
                this.requiredFieldSelector = ".csc-objectexit-exitNumber";
                this.requiredFieldMessage = "Please specify an Exit Number";
                this.generatedPostfix = " - exitNumber";

                this.dateMap.put(".csc-objectexit-exitDate", "2011-05-05");

                this.fieldMap.put(".csc-objectexit-exitNote", "Goodbye birdie");
		this.fieldMap.put(".csc-objectexit-packingNote", "Foam and Cardboard");

                this.selectMap.put(".csc-objectexit-exitReason-selection", "Disposal");
		this.selectMap.put("repeat::.csc-objectexit-exitMethods", "In Person");

                this.vocabMap.put(".csc-objectexit-currentOwner", "VOCAB");
		this.vocabMap.put(".csc-objectexit-depositor", "VOCAB");

                break;

            default:

                System.out.println("NOT IMPLEMENTED YET");
        }
    }
}
