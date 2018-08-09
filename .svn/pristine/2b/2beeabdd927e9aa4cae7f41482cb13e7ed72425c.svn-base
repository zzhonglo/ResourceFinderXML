package ca.ubc.cs.cpsc210.resourcefinder.parser;

import ca.ubc.cs.cpsc210.resourcefinder.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

// Handler for XML resource parsing
public class ResourceHandler extends DefaultHandler {
    private ResourceRegistry registry;
    private StringBuilder accumulator;
    private Double lat;
    private Double lon;
    private String address;
    private GeoPoint geoLocation;
    private URL webAddress;
    private String phoneNumber;
    private String name;
    private ContactInfo contactInfo;
    private Set<Service> services;
    private Resource resource;

    // EFFECTS: constructs resource handler for XML parser
    public ResourceHandler(ResourceRegistry registry) {
        this.registry = registry;
        accumulator = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.toLowerCase().equals("resource")) {
            lat = 0.0;
            lon = 0.0;
            address = null;
            geoLocation = null;
            webAddress = null;
            phoneNumber = null;
            name = null;
            contactInfo = null;
            services = new HashSet<>();
            resource = null;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        String data = accumulator.toString().trim();
        accumulator.setLength(0);
        if (qName.toLowerCase().equals("name")) {
            name = data;
        }
        if (qName.toLowerCase().equals("address")) {
            address = data;
        }
        if (qName.toLowerCase().equals("lat")) {
            try {
                lat = Double.parseDouble(data);
            } catch (NumberFormatException e) {
                throw new SAXException("Wrong number format");
            }
        }
        if (qName.toLowerCase().equals("lon")) {
            try {
                lon = Double.parseDouble(data);
            } catch (NumberFormatException e) {
                throw new SAXException("Wrong number format");
            }
        }
        if (qName.toLowerCase().equals("webaddress")) {
            try {
                webAddress = new URL(data);
            } catch (MalformedURLException e) {
                throw new SAXException("Bad URL address entered");
            }
        }
        if (qName.toLowerCase().equals("phone")) {
            phoneNumber = data;
        }
        if (qName.toLowerCase().equals("service")) {
            switch (data) {
                case "Food":
                    services.add(Service.FOOD);
                    break;
                case "Legal advice":
                    services.add(Service.LEGAL);
                    break;
                case "Youth services":
                    services.add(Service.YOUTH);
                    break;
                case "Counselling":
                    services.add(Service.COUNSELLING);
                    break;
                case "Shelter":
                    services.add(Service.SHELTER);
                    break;
                case "Senior services":
                    services.add(Service.SENIOR);
                    break;
            }
        }
        if (qName.toLowerCase().equals("resource")) {
            geoLocation = new GeoPoint(lat, lon);
            contactInfo = new ContactInfo(address, geoLocation, webAddress, phoneNumber);
            resource = new Resource(name, contactInfo);
            for (Service next : services) {
                resource.addService(next);
            }
            if (!(lat == 0 || lon == 0 || address == null || geoLocation == null || webAddress == null || phoneNumber == null ||
                    name == null || contactInfo == null || resource == null || services.isEmpty())){
                registry.addResource(resource);
            }
        }
    }
    // EFFECTS:
    //   return parsed resource registry;
    //   resource not added to registry if any one of the following fields is missing:
    //     - name, address, phone, webaddress, location, services;
    //   throws IOException if there is a problem reading data from file (e.g., path to file is not valid);.
    //   throws ResourceParsingException if:
    //     - data in file does not follow expected syntax
    //     - URL cannot be formed from given webaddress
    //     - latitude or longitude cannot be parsed as a double
    //     - no resources were added to the registry
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        accumulator.append(ch, start, length);
    }
}
