/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Thor
 */
public interface IParsingContext {
    /**
     * set's the consumer context that's using this parsing context
     * @param ctx consuemrContext forwarding SAX events to this parsing context
     */
    public void setConsumerContext(ConsumerContext ctx);
    /**
     * handles a tag start
     * @param uri namespace URI
     * @param localName tagname without namespace
     * @param qName tag name using namespace
     * @param attributes any tag attributes or an empty attributes object if there are no attributes for the tag
     * @throws SAXException if anything goes wrong during parsing
     */    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;
    /**
     * handles a tag ending
     * @param uri namespace URI
     * @param localName tagname without namespace
     * @param qName tag name using namespace
     * @throws SAXException if anything goes wrong during parsing
     */
    public void endElement(String uri, String localName, String qName) throws SAXException;
    /**
     * handles tag contents
     * @param text anything written within the tag
     */
    public void characters(String text);
    /**
     * returns the tag name for an xml element that can be processed by this parsing context
     * @return xml tag name
     */
    public String getTagName();
}
