/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel;

import org.xml.sax.*;
import serempre.codegen.metamodel.parsers.ConsumerContext;
import serempre.codegen.metamodel.parsers.IParsingContext;

/**
 *
 * @author Thor
 */
public class RelationshipDescriptor implements IParsingContext {
    protected ConsumerContext mCtx;
    //this tag describes a relationship within an xml document
    public static final String TAG_NAME = "Relationship";
    //this holds all tag attributes that need processing
    public enum TAG_ATTRIBUTES { name, type, owner, target };
    //this holds all allowed relationship type values
    public enum RELATIONSHIP_TYPE { one_to_one, one_to_many, many_to_many };
    //this is tha name for the class that owns this relationship
    private String mOwnerClassName;
    //this is a reference to the class descriptor for the class that owns this relationship
    private ClassDescriptor mOwnerClass;
    //this is the target of the class this relationship references
    private String mTargetClassName;
    //this is the target class this relationship references
    private ClassDescriptor mTargetClass;
    //this is the name for this relationship
    private String mName;
    //type of this relationship
    protected RELATIONSHIP_TYPE mType;
    /**
     * default parameterless constructor
     */
    public RelationshipDescriptor(){
    }
    /**
     * getter for relationship type
     * @return relationship type
     */
    public RELATIONSHIP_TYPE getType(){
        return mType;
    }
    /**
     * setter for relationship type
     * @param type new value for relationship type
     */
    public void setType(RELATIONSHIP_TYPE type){
        mType = type;
    }
    /**
     * getter for this relationship's name
     * @return name property
     */
    public String getName(){
        return mName;
    }
    /**
     * setter for this relationship's name
     * @param name new value for name property
     */
    public void setName(String name){
        mName = name;
    }

    /**
     * @return the mOwnerClassName
     */
    public String getOwnerClassName() {
        return mOwnerClassName;
    }

    /**
     * @param mOwnerClassName the mOwnerClassName to set
     */
    public void setOwnerClassName(String mOwnerClassName) {
        this.mOwnerClassName = mOwnerClassName;
    }

    /**
     * @return the mOwnerClass
     */
    public ClassDescriptor getOwnerClass() {
        return mOwnerClass;
    }

    /**
     * @param mOwnerClass the mOwnerClass to set
     */
    public void setOwnerClass(ClassDescriptor mOwnerClass) {
        this.mOwnerClass = mOwnerClass;
    }

    /**
     * @return the mTargetClassName
     */
    public String getTargetClassName() {
        return mTargetClassName;
    }

    /**
     * @param mTargetClassName the mTargetClassName to set
     */
    public void setTargetClassName(String mTargetClassName) {
        this.mTargetClassName = mTargetClassName;
    }

    /**
     * @return the mTargetClass
     */
    public ClassDescriptor getTargetClass() {
        return mTargetClass;
    }

    /**
     * @param mTargetClass the mTargetClass to set
     */
    public void setTargetClass(ClassDescriptor mTargetClass) {
        this.mTargetClass = mTargetClass;
    }
    /**
     * setter for consumer context
     * @param ctx reference to a SAX event dispatcher used when parsing XML documents
     */
    @Override
    public void setConsumerContext(ConsumerContext ctx) {
        mCtx = ctx;
    }
    /**
     * handle tag start events
     * @param uri namespace for tag being processed
     * @param localName tag name within it's namespace
     * @param qName fully qualified name
     * @param attributes tag attribute collection
     * @throws SAXException if parsing fails
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase(TAG_NAME)){
            mName = attributes.getValue(TAG_ATTRIBUTES.name.toString());
            mOwnerClassName = attributes.getValue(TAG_ATTRIBUTES.owner.toString());
            mTargetClassName = attributes.getValue(TAG_ATTRIBUTES.target.toString());
            mType = RELATIONSHIP_TYPE.valueOf(attributes.getValue(TAG_ATTRIBUTES.type.toString()));
        }
    }
    /**
     * handle end tag events
     * @param uri tag namespace
     * @param localName tag name within it's namespace
     * @param qName fully qualified tag name
     * @throws SAXException if parsing fails
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase(TAG_NAME)){
            mCtx.popHandler();
        }
    }
    /**
     * handle inner tag text
     * @param text inner tag text
     */
    @Override
    public void characters(String text) {
        //DO NOTHING
    }
    /**
     * getter for tag name
     * @return name for tags processed by this class
     */
    @Override
    public String getTagName() {
        return TAG_NAME;
    }
    
}
