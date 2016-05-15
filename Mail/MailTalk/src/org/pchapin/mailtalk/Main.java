package org.pchapin.mailtalk;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class Main extends DefaultHandler {

    // These URLs are standard and are just meant to be globally unique IDs.
    private static final String VALIDATION_FEATURE_ID =
            "http://xml.org/sax/features/validation";

    // This is, apparently, Xerces specific.
    private static final String VALIDATION_SCHEMA_ID =
            "http://apache.org/xml/features/validation/schema";

    // Replace this with the name of any suitable SAX parser class.
    private static final String DEFAULT_PARSER_NAME =
            "org.apache.xerces.parsers.SAXParser";

    // Used to hold state information from event to event.
    private String content = null;     // The text of text-only element content.
    private boolean firstTime = true;  // Used when checking dates.
    private String firstDate;          // The YYYY-MM of the first message.

    //
    // These are callbacks that are invoked by the parser whenever an event occurs.
    //
    @Override
    public void startDocument()
    {

    }


    @Override
    public void endDocument()
    {

    }


    @Override
    public void startElement(
            String namespace,
            String elementName,
            String rawName,
            Attributes attrs)
    {
        content = null;
    }


    @Override
    public void endElement(
            String namespace,
            String elementName,
            String rawName)
    {
        if (content != null) {
            switch (elementName) {
                case "Date":
                    if (firstTime) {
                        firstDate = content.substring(0, 7);
                        firstTime = false;
                    } else {
                        String currentDate = content.substring(0, 7);
                        if (!currentDate.equals(firstDate)) {
                            System.out.println("Inconsistent date found: " + currentDate);
                        }
                    }
                    break;

                default:
                    // For elements not otherwise handled.
                    break;
            }
        }
        content = null;
    }


    @Override
    public void characters(char[] buffer, int start, int length)
    {
        // Collect the content into a single String. The parser might call this method many times
        // if the content is long. I really have to collect the results of all calls before I
        // look too closely at it.
        //
        if (content == null) content = new String(buffer, start, length);
        else content = content + new String(buffer, start, length);
    }


    //
    // MAIN
    //

    public static void main(String[] args) {

        // Check the command line.
        if (args.length != 1) {
            System.err.println("Fatal: Expected URI on command line");
            return;
        }

        // Create a ContentHandler object.
        Main myExample = new Main();
        XMLReader parser;

        try {
            // Create parser and turn on desired features.
            parser = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
            parser.setFeature(VALIDATION_FEATURE_ID, true);
            parser.setFeature(VALIDATION_SCHEMA_ID, true);

            // Parse file.
            parser.setContentHandler(myExample);
            // parser.setErrorHandler(myExample);
            parser.parse(args[0]);
        }
        catch (SAXException e) {
            System.err.println("Fatal: Caught SAXException: " + e);
        }
        catch (Exception e) {
            System.err.println("Fatal: Caught: " + e);
        }
    }

}
