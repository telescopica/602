/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import serempre.codegen.metamodel.ClassDescriptor;
import serempre.codegen.metamodel.RelationshipDescriptor;

/**
 *
 * @author Thor
 */
public class ModelParser implements IParsingContext {
    public static String TAG_NAME = "DataModel";
    //this will handle parsing operations
    protected ConsumerContext mCtx;
    //keeps track of all known classes
    protected HashMap<String, ClassDescriptor> mClasses;
    //keeps track of all known relationships
    protected ArrayList<RelationshipDescriptor> mRelationships;
    /**
     * default parameterless constructor
     */
    public ModelParser(){
        mClasses = new HashMap<String, ClassDescriptor>();
        mRelationships = new ArrayList<RelationshipDescriptor>();
    }
    /**
     * getter for relationship property
     * @return value of relationship property
     */
    public ArrayList<RelationshipDescriptor> getRelationships(){
        return mRelationships;
    }
    /**
     * setter for relationship property
     * @param relationships value of relationship property
     */
    public void setRelationships(ArrayList<RelationshipDescriptor> relationships){
        mRelationships = relationships;
    }
    /**
     * every item key should match it's name attribute
     * @param classes classes held by this model
     */
    public void setClasses(HashMap<String, ClassDescriptor> classes){
        mClasses = classes;
    }
    /**
     * every item key matches the item's name attribute
     * @return classes that make up this model
     */
    public HashMap<String, ClassDescriptor> getClasses(){
        return mClasses;
    }
    /**
     * this is to keep track of a context that dispatches SAX events
     * @param ctx sax event dispatcher
     */
    @Override
    public void setConsumerContext(ConsumerContext ctx) {
        mCtx = ctx;
    }
    /**
     * this handles a start tag event
     * @param uri namespace for tag being parsed
     * @param localName tag name
     * @param qName fully qualified tag name
     * @param attributes tag attributes
     * @throws SAXException if parsing fails
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase(TAG_NAME)){
        
        }
        //if the element stands for a class
        else if(qName.equalsIgnoreCase(ClassDescriptor.TAG_NAME)){
            //parse a class
            parseAClass(uri,localName,qName,attributes);
        }
        //if the element stands for a relationship
        else if(qName.equalsIgnoreCase(RelationshipDescriptor.TAG_NAME)){
            //parse a relationship
            parseARelationship(uri,localName,qName,attributes);
        }
    }
    /**
     * handle tag end events
     * @param uri namespace for tag being parsed
     * @param localName tag name
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
     * name for a tag that can be parsed by this class
     * @return tag name for a tag that can be parsed by this class
     */
    @Override
    public String getTagName() {
        return TAG_NAME;
    }
    /**
     * parses a class
     * @param uri tag namespace
     * @param localName tag name wihin it's namespace
     * @param qName fully qualified name
     * @param attributes tag attributes
     * @throws SAXException if parsing fails
     */
    private void parseAClass(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //get the class name
        String class_name = attributes.getValue(ClassDescriptor.TAG_ATTRIBUTES.name.toString());
        //if it's a unique class name
        if(!mClasses.containsKey(class_name)){
            //create a class descriptor
            ClassDescriptor classDescriptor = new ClassDescriptor();
            //add the new class descriptor to the class collection
            mClasses.put(class_name, classDescriptor);
            //let the class descriptor handle parse operations from now on
            mCtx.pushHandler(classDescriptor);
            //forward start tag event to the class descriptor
            classDescriptor.startElement(uri, localName, qName, attributes);
        }else{
            throw new SAXException(String.format("duplicate class definition for: %s", class_name));
        }
    }
    /**
     * parses a relationship
     * @param uri tag namespace
     * @param localName tag's name within it's namespace
     * @param qName fully qualified name
     * @param attributes tag's attributes
     * @throws SAXException if parsing fails
     */
    private void parseARelationship(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //create a relationshp descriptor
        RelationshipDescriptor relationship = new RelationshipDescriptor();
        //add the new relationship to the set of relationships for this model
        mRelationships.add(relationship);
        //delegate parsing to the relationship
        mCtx.pushHandler(relationship);
        //forward start tag event to the relationship
        relationship.startElement(uri, localName, qName, attributes);
        //find the class that owns this relationship
        relationship.setOwnerClass(mClasses.get(relationship.getOwnerClassName()));
        //find the class that's referenced by this relationship
        relationship.setTargetClass(mClasses.get(relationship.getTargetClassName()));
        //make sure both owning and targeted sides exist
        if(relationship.getTargetClass() == null || relationship.getOwnerClass() == null){
            //abort parsing if either owner or target types don't exist
            throw new SAXException("Invalid model definition: "+relationship.getName()+":"+relationship.getOwnerClassName()+"->"+relationship.getTargetClassName()+" is referencing non-existing/yet-to-be-defined classes");
        }
        //save class references
        else{
            relationship.getOwnerClass().getOwnedTypes().add(relationship);
            relationship.getTargetClass().getTargetedByTypes().add(relationship);
        }
    }
    
}
