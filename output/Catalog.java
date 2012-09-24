package hive.models;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import serempre.jme.rms.*;
import serempre.jme.xml.*;

public class Catalog extends IPersistable implements IParsingContext, IServerSideIdable {
    protected int mClientId;
    protected static String OWNED_RELATIONSHIP_ITEMS = "items";
    public static String TAG_NAME = "Catalog";
    protected int mId;
    protected ConsumerContext mCtx;
    
    /**
    * default parameterless constructor
    */
    public Catalog() {
        super();
        getOneToManyMappingFor(OWNED_RELATIONSHIP_ITEMS,CatalogItem.class);
    }
    /**
    * value constructor
    * @param clientId initializes this class using this value for the corresponding attribute
    */
    public Catalog(int clientId){
        super();
        getOneToManyMappingFor(OWNED_RELATIONSHIP_ITEMS,CatalogItem.class);
        mClientId = clientId;
    }
    /**
    * @return value of clientId property
    */
    public int getClientId(){
        return mClientId;
    }
    /**
    * @param clientId new value for clientId property
    */
    public void setClientId(int clientId){
        mClientId = clientId;
    }
    /**
    * @return value for items property
    */
    public Vector getItems(){
        return getOneToManyMappingFor(OWNED_RELATIONSHIP_ITEMS);
    }
    /**
    * @param newElements new value for items property
    */
    public void setItems(Vector newElements){
        getOneToManyMappingFor(OWNED_RELATIONSHIP_ITEMS).replaceContents(newElements);
    }
    /**
    * @return server side entity key
    */
    public int getId(){
        return mId;
    }
    /**
    * @param id server side entity key
    */
    public void setId(int id){
        mId = id;
    }
    /**
    * @param ctx handler for xml content
    */
    public void setConsumerContext(ConsumerContext ctx){
        mCtx = ctx;
    }
    /**
        * overrides default implementation
        */
    public int hashCode(){
        return ((new Integer(mId))).hashCode();
    }
    /**
    * @return string representation for this object
    */
    public String toString(){
        //TODO: change this for something that makes sense for your class
        return "";
    }
    /**
    * interface implementation
    */
    public void writeToByteStream(DataOutputStream dos) throws IOException {
        dos.writeInt(mClientId);
    }
    /**
    * interface implementation
    */
    public void readFromByteStream(DataInputStream dis) throws IOException {
        mClientId = dis.readInt();
    }
    /**
    * interface implementation
    */
    public String getRecordStoreName() {
        //TODO: change this for something better
        return "Catalog".substring(1,5);
    }
    /**
    * @param text text found within this tag
    */
    public void characters(String text) {
        //no text expected within this tag
    }
    /**
    * handles start event for xml tags
    */
    public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
        IParsingContext parser = null;
        if(qName.equalsIgnoreCase("clientId")){
            parser = new IParsingContext(){
                String TAG_NAME = "clientId";
                ConsumerContext mCtx;
                /**
                * interface implementation
                */
                public void setConsumerContext(ConsumerContext ctx) {
                    mCtx = ctx;
                }
                /**
                * @param text text found within this tag
                */
                public void characters(String text) {
                    setClientId(Integer.parseInt(text));
                }
                /**
                * handles start event for xml tags
                */
                public void startElement(String uri, String localName, String qName,
                                Attributes attributes) throws SAXException {
                    //no tags are expected to start while this is handling xml content
                }
                /**
                * handles end event for all Client tags
                */
                public void endElement(String uri, String localName, String qName)
                                throws SAXException {
                    if(qName.equalsIgnoreCase(TAG_NAME)){
                        mCtx.popHandler();
                    }
                }
            };
        }
        if(parser!=null){
            mCtx.pushHandler(parser);
        }
        
    }
    /**
    * handles end event for all Client tags
    */
    public void endElement(String uri, String localName, String qName)
                    throws SAXException {
        if(qName.equalsIgnoreCase(TAG_NAME)){
            //TODO: do something to save or update this entity
            mCtx.popHandler();
        }
    }
}
