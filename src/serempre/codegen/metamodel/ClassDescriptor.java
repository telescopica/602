/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel;

import java.util.*;
import org.xml.sax.*;
import serempre.codegen.metamodel.parsers.ConsumerContext;
import serempre.codegen.metamodel.parsers.IParsingContext;
/**
 *
 * @author Thor
 */
public class ClassDescriptor implements IParsingContext {
    protected ConsumerContext mCtx;
    //this represents this element within XML tags
    public static final String TAG_NAME = "Class";
    //this stands for the attributes this tag can handle
    public enum TAG_ATTRIBUTES { name };
    //holds this class' attributes
    public ArrayList<AttributeDescriptor> mAttributes;
    //this holds every relationship owned by this class
    private ArrayList<RelationshipDescriptor> mOwnedTypes;
    //this holds every relationship that targets this class
    private ArrayList<RelationshipDescriptor> mTargetedByTypes;
    //stores this class' name
    private String mClassName;
    /**
     * setter for class name
     * @param name a string for this class' name
     */
    public void setCalssName(String name){
        mClassName = name;
    }
    /**
     * getter for class name
     * @return a string representing this class' name
     */
    public String getClassName(){
        return mClassName;
    }
    /**
     * default parameterless constructor
     */
    public ClassDescriptor(){
        mAttributes = new ArrayList<AttributeDescriptor>();
        mOwnedTypes = new ArrayList<RelationshipDescriptor>();
        mTargetedByTypes = new ArrayList<RelationshipDescriptor>();
    }
    /**
     * setter for this class's attributes
     * @param attributes  attributes that define this class
     */
    public void setAttributes(ArrayList<AttributeDescriptor> attributes){
        mAttributes = attributes;
    }
    /**
     * getter for this class' attributes
     * @return this class' attributes
     */
    public ArrayList<AttributeDescriptor> getAttributes(){
        return mAttributes;
    }

    /**
     * @return the mOwnedTypes
     */
    public ArrayList<RelationshipDescriptor> getOwnedTypes() {
        return mOwnedTypes;
    }

    /**
     * @param mOwnedTypes the mOwnedTypes to set
     */
    public void setOwnedTypes(ArrayList<RelationshipDescriptor> mOwnedTypes) {
        this.mOwnedTypes = mOwnedTypes;
    }

    /**
     * @return the mTargetedByTypes
     */
    public ArrayList<RelationshipDescriptor> getTargetedByTypes() {
        return mTargetedByTypes;
    }

    /**
     * @param mTargetedByTypes the mTargetedByTypes to set
     */
    public void setTargetedByTypes(ArrayList<RelationshipDescriptor> mTargetedByTypes) {
        this.mTargetedByTypes = mTargetedByTypes;
    }
    /**
     * set a consumer context for parsing operations
     * @param ctx an object that fires xml document events
     */
    @Override
    public void setConsumerContext(ConsumerContext ctx) {
        mCtx = ctx;
    }
    /**
     * this is fired when a tag start event is found on an xml document
     * @param uri namespace for tag being processed
     * @param localName tag name
     * @param qName fully qualified name for tag
     * @param attributes tag attributes
     * @throws SAXException if parsing fails
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //if a class is being parsed
        if(qName.equalsIgnoreCase(getTagName())){
            //set class name from xml attributes
            mClassName = attributes.getValue(TAG_ATTRIBUTES.name.toString());
        //if an attribute is being parsed
        }else if(qName.equalsIgnoreCase(AttributeDescriptor.TAG_NAME)){
            //create an attribute descriptor
            AttributeDescriptor attribute = new AttributeDescriptor();
            //add an attribute to this class
            mAttributes.add(attribute);
            //let the attribute parse itself
            mCtx.pushHandler(attribute);
            //forward event to new handler
            attribute.startElement(uri, localName, qName, attributes);
        }
    }
    /**
     * this is fired when a tag end event is found on an xml document
     * @param uri namespace for tag being processed
     * @param localName tag name
     * @param qName fully qualified tag name
     * @throws SAXException if parsing fails
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //if a class element is ending
        if(qName.equalsIgnoreCase(TAG_NAME)){
            //release the parsing context so that another object may take control of parsing operations
            mCtx.popHandler();
        }
    }
    /**
     * this is fired when text is found within a tag
     * @param text text held by the tag body
     */
    @Override
    public void characters(String text) {
        //there should be no text within a class tag
        
    }
    /**
     * 
     * @return the tag name for the element this class is able to parse
     */
    @Override
    public String getTagName() {
        return TAG_NAME;
    }
    
}
