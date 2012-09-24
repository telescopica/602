/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import serempre.codegen.metamodel.parsers.ConsumerContext;
import serempre.codegen.metamodel.parsers.IParsingContext;

/**
 *
 * @author Thor
 */
public class ResourceUtility {
    /**
     * gets a URL to a file stored within the jar being executed
     * @param pathWithinJar path within jar root
     * @return URI to requested resource
     * @throws URISyntaxException if a URL to the specified file can't be built
     */
    public static <T> URI getURIToFile(String pathWithinJar, Class<T> caller) throws URISyntaxException{
        URL resourcePath = caller.getClassLoader().getResource(pathWithinJar);
        return resourcePath.toURI();
    }
    /**
     * builds a path to a file at this jar's execution path
     * @param fileName file at this jar's execution path
     * @return full path to a file at this jar's execution path
     */
    public static String getPathToFile(String fileName){
        //get jar execution path. This should be netbeans project root
        String filePath = System.getProperty("user.dir");
        //point filePath to a test file which will be used for input
        filePath += System.getProperty("file.separator") + fileName;
        //return calculated file path
        return filePath;
    }
    /**
     * creates a SAX parser
     * @return a SAX parser implementation
     */
    public static SAXParser getSAXParser() throws ParserConfigurationException, SAXException {
        //this is a place holder for a sax parser
        SAXParser parser = null;
        //request a SAX parser
        parser = SAXParserFactory.newInstance().newSAXParser();
        //return the newly built sax parser
        return parser;
    }
    /**
     * parse a file using provided parsing context
     * @param parsingContext content handler for an xml file
     * @param fileName xml file to be parsed
     */
    public static void parseFileUsingParsingContext(IParsingContext parsingContext, String fileName) throws ParserConfigurationException, SAXException, IOException {
        //create a content handler for the xml document to be parsed
        ConsumerContext ctx = new ConsumerContext();
        //build a SAX parser
        SAXParser parser = getSAXParser();
        //build a a full path to the file that will get parsed
        String filePath = getPathToFile(fileName);
        //setup delegate parsers
        ctx.pushHandler(parsingContext);
        //start parsing
        parser.parse(new File(filePath), ctx);
    }
    /**
     * creates directory structure, if missing
     * @param pathToOutputFolder path to folder where output files will be written to
     */
    public static void buildTargetPath(String pathToOutputFolder) {
        File folder = new File(pathToOutputFolder);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }
}
