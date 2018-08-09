package parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

// An example of a simple SAX parser to parse a library XML file

public class LibraryParser extends DefaultHandler {

	private StringBuilder accumulator;

    // Constructor
    public LibraryParser() {
        accumulator = new StringBuilder();
    }

	/**
	 * Called at the start of the document (as the name suggests)
	 */
	@Override
	public void startDocument() {
        // Just so you can visualize how the parser is working
		System.out.println("Start Document!");
	}

	/**
	 * Called when the parsing of an element starts. (e.g., <book>)
	 * 
	 * Lookup DefaultHandler documentation to learn meanings of parameters.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) {

        // Just so you can visualize how the parser is working
		System.out.println("StartElement: " + qName);

		// What are we parsing?
		if (qName.toLowerCase().equals("book")) {
            // Just so you can visualize how the parser is working
			System.out.println("Reached a new book with isbn = " + atts.getValue("isbn"));
		}
	}

	/**
	 * Called for values of elements
	 * 
	 * Lookup DefaultHandler documentation to learn meanings of parameters.
	 */
	public void characters(char[] temp, int start, int length) {
		// Remember the value parsed
		accumulator.append(temp, start, length);
	}

	/**
	 * Called when the end of an element is seen. (e.g., </title>)
	 * 
	 * Lookup DefaultHandler documentation to learn meanings of parameters.
	 */
	public void endElement(String uri, String localName, String qName) {
        String data = accumulator.toString().trim();   // trim leading and trailing whitespace
		accumulator.setLength(0);                      // clear accumulator

        // Just so you can visualize how the parser is working
		System.out.println("EndElement: " + qName + " value: " + data);
	}

	/**
	 * Called when the end of the document is reached
	 */
	public void endDocument() {
		// Just so you can visualize how the parser is working
		System.out.println("End Document!");
	}
}
