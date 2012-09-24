/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import serempre.codegen.metamodel.parsers.ConsumerContext;
import serempre.codegen.metamodel.parsers.IParsingContext;

/**
 *
 * @author Thor
 */
public class AttributeDescriptor implements IParsingContext {
    protected ConsumerContext mCtx;
    //represents this element within xml tags
    public static final String TAG_NAME = "Attribute";
    //holds the name for the modeled attribute
    protected String mAttributeName;
    //holds the name for the attribute's type
    protected String mAttributeTypeName;
    //this stands for the attributes this tag can handle
    public enum TAG_ATTRIBUTES { name, type };
    /**
     * default parameterless constructor
     */
    public AttributeDescriptor(){
    }
    /**
     * setter for attribute name
     * @param attributeName the attribute name
     */
    public void setAttributeName(String attributeName){
        mAttributeName = attributeName;
    }
    /**
     * getter for attribute name
     * @return the modeled attribute's name
     */
    public String getAttributeName(){
        return mAttributeName;
    }
    /**
     * setter for attribute type name
     * @param attributeTypeName the attribute type's name
     */
    public void setAttributeTypeName(String attributeTypeName){
        mAttributeTypeName = attributeTypeName;
    }
    /**
     * getter for attribute type name
     * @return the modeled attribute's type name
     */
    public String getAttributeTypeName(){
        return mAttributeTypeName;
    }
    /**
     * set a consumer context that handles parsing operations
     * @param ctx an object that fires xml document events
     */
    @Override
    public void setConsumerContext(ConsumerContext ctx) {
        mCtx = ctx;
    }
    /**
     * this is fired upon tag start events
     * @param uri namespace for current tag
     * @param localName tag name within it's namespace
     * @param qName fully qualified tag name
     * @param attributes tag attributes
     * @throws SAXException if parsing fails
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //if it is an 'attribute' tag
        if(qName.equalsIgnoreCase(TAG_NAME)){
            //get the attribute's name
            mAttributeName = attributes.getValue(TAG_ATTRIBUTES.name.toString());
            //get the attribute's type
            mAttributeTypeName = attributes.getValue(TAG_ATTRIBUTES.type.toString());
        }
    }
    /**
     * this is fired upon tag end events
     * @param uri namespace for the current tag
     * @param localName tag name within it's namespace
     * @param qName fully qualified tag name
     * @throws SAXException if parsing fails
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //if the tag ending matches this tag
        if(qName.equalsIgnoreCase(TAG_NAME)){
            //remove this object from the parsing context
            mCtx.popHandler();
        }
    }
    /**
     * handle text within this tag
     * @param text text found within this tag
     */
    @Override
    public void characters(String text) {
        //DO NOTHING
    }
    /**
     * 
     * @return this element's tag name
     */
    @Override
    public String getTagName() {
        return TAG_NAME;
    }
    
}
