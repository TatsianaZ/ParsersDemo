package com.it_academy.parsers.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.io.File;
import java.util.Optional;

public class UpdateXMLWithDOM {
    //private static final String DEFAULT_FILE_PATH = "environment.xml";
    public static void main(String[] args) {
        File defaultEnvFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\environment.xml");
        File updatedEnvFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\fff.xml");

        System.out.println(defaultEnvFile.exists());
        System.out.println(updatedEnvFile.exists());

        Document document = DOMParserUtils.parseXMLDocument(defaultEnvFile);
        Node browserType = DOMParserUtils.getXmlNodeByXPath(
                "//environment/parameter[contains(., 'Browser')]/value", document);
        browserType.setTextContent("gggg");
        DOMParserUtils.transformXMLAfterUpdate(document, updatedEnvFile);
    }
}
