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
    //literal value to use for overriding an attribute type
    protected String mAlternateType="";
    //literal value to use when defining an attribute type
    protected String mFieldAttributes="";
    //this stands for the attributes this tag can handle
    public enum TAG_ATTRIBUTES { name, type, alternateType, fieldAttributes };
    /**
     * default parameterless constructor
     */
    public AttributeDescriptor(){
    }
    /**
     * fix a new value for an alternate attribute type
     * @param name alternate attribute type
     */
    public void setAlternateType(String name){
        mAlternateType = name;
    }
    /**
     * fetches alternate attribute type
     * @return alternate attribute type
     */
    public String getAlternateType(){
        return mAlternateType;
    }
    /**
     * fixes additional field attribute content
     * @param attributes 
     */
    public void setFieldAttributes(String attributes){
        mFieldAttributes = attributes;
    }
    /**
     * returns additional field attribute content
     * @return 
     */
    public String getFieldAttributes(){
        return mFieldAttributes;
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
            //find out if alternateType is present
            if(attributes.getIndex(TAG_ATTRIBUTES.alternateType.name())>-1){
                mAttributeTypeName = attributes.getValue(TAG_ATTRIBUTES.alternateType.name());
                mAlternateType = mAttributeTypeName;
            }
            //find out if fieldAttributes is present
            if(attributes.getIndex(TAG_ATTRIBUTES.fieldAttributes.name())>-1){
                mFieldAttributes = attributes.getValue(TAG_ATTRIBUTES.fieldAttributes.name());
            }
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
