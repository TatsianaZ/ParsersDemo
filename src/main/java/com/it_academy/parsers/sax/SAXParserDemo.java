package com.it_academy.parsers.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserDemo {

    private static final String XML_PATH = "employee.xml";

    public static void main(String[] args) throws Exception {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(ClassLoader.getSystemResourceAsStream(XML_PATH), handler);

       // System.out.println(handler.employees);
    }
}

