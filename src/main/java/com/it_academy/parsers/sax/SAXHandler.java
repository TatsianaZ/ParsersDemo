package com.it_academy.parsers.sax;

import com.it_academy.entity.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * The Handler for SAX Events.
 */

class SAXHandler extends DefaultHandler {

    List<Employee> employees = new ArrayList<>();
    Employee employee = null;
    String content = null;

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        System.out.println("startElement for :" + qName);
        switch (qName) {
            //Create a new Employee object when the start tag is found
            case "employee":
                employee = new Employee();
                employee.setId(attributes.getValue("id"));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        System.out.println("endElement for :" + qName);
        switch (qName) {
            //Add the employee to list once end tag is found
            case "employee":
                employees.add(employee);
                break;
            //For all other end tags the employee has to be updated.
            case "firstName":
                employee.setFirstName(content);
                break;
            case "lastName":
                employee.setLastName(content);
                break;
            case "location":
                employee.setLocation(content);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.println("characters ");
        content = String.copyValueOf(ch, start, length).trim();
    }

}