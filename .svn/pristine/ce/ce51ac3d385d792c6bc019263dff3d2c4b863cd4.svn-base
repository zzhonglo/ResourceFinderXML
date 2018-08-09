package ca.ubc.cs.cpsc210.resourcefinder.tests.parser;
import ca.ubc.cs.cpsc210.resourcefinder.model.*;
import ca.ubc.cs.cpsc210.resourcefinder.parser.IResourceParser;
import ca.ubc.cs.cpsc210.resourcefinder.parser.ResourceParsingException;
import ca.ubc.cs.cpsc210.resourcefinder.parser.XMLResourceParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

// Unit tests for XMLResourceParser class
public class ResourceParserBadURLTest {
    private static final String FILE_NAME = "./data/resourceWithBadURL.xml";
    private IResourceParser resourceParser;

    @Before
    public void runBefore() {
        resourceParser = new XMLResourceParser(FILE_NAME);
    }

    @Test (expected = ResourceParsingException.class)
    public void testParse() throws ResourceParsingException, IOException {
        resourceParser.parse();
    }
}