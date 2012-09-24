package hive.models;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import serempre.jme.rms.*;
import serempre.jme.xml.*;

public class OrderEvent extends IPersistable implements IParsingContext, IServerSideIdable {
    protected String mDescription;
    protected long mTime;
    protected int mLastStatus;
    protected int mStatus;
    protected int mModifiedBy;
    protected static String TARGETED_BY_RELATIONSHIP_EVENTS = Order.class.getName()+".events";
    public static String TAG_NAME = "OrderEvent";
    protected int mId;
    protected ConsumerContext mCtx;
    
    /**
    * default parameterless constructor
    */
    public OrderEvent() {
        super();
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_EVENTS,Order.class);
    }
    /**
    * value constructor
    * @param description initializes this class using this value for the corresponding attribute
    * @param time initializes this class using this value for the corresponding attribute
    * @param lastStatus initializes this class using this value for the corresponding attribute
    * @param status initializes this class using this value for the corresponding attribute
    * @param modifiedBy initializes this class using this value for the corresponding attribute
    */
    public OrderEvent(String description, long time, int lastStatus, int status, int modifiedBy){
        super();
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_EVENTS,Order.class);
        mDescription = description;
        mTime = time;
        mLastStatus = lastStatus;
        mStatus = status;
        mModifiedBy = modifiedBy;
    }
    /**
    * @return value of description property
    */
    public String getDescription(){
        return mDescription;
    }
    /**
    * @param description new value for description property
    */
    public void setDescription(String description){
        mDescription = description;
    }
    /**
    * @return value of time property
    */
    public long getTime(){
        return mTime;
    }
    /**
    * @param time new value for time property
    */
    public void setTime(long time){
        mTime = time;
    }
    /**
    * @return value of lastStatus property
    */
    public int getLastStatus(){
        return mLastStatus;
    }
    /**
    * @param lastStatus new value for lastStatus property
    */
    public void setLastStatus(int lastStatus){
        mLastStatus = lastStatus;
    }
    /**
    * @return value of status property
    */
    public int getStatus(){
        return mStatus;
    }
    /**
    * @param status new value for status property
    */
    public void setStatus(int status){
        mStatus = status;
    }
    /**
    * @return value of modifiedBy property
    */
    public int getModifiedBy(){
        return mModifiedBy;
    }
    /**
    * @param modifiedBy new value for modifiedBy property
    */
    public void setModifiedBy(int modifiedBy){
        mModifiedBy = modifiedBy;
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
        dos.writeUTF(mDescription);
        dos.writeLong(mTime);
        dos.writeInt(mLastStatus);
        dos.writeInt(mStatus);
        dos.writeInt(mModifiedBy);
    }
    /**
    * interface implementation
    */
    public void readFromByteStream(DataInputStream dis) throws IOException {
        mDescription = dis.readUTF();
        mTime = dis.readLong();
        mLastStatus = dis.readInt();
        mStatus = dis.readInt();
        mModifiedBy = dis.readInt();
    }
    /**
    * interface implementation
    */
    public String getRecordStoreName() {
        //TODO: change this for something better
        return "OrderEvent".substring(1,5);
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
        if(qName.equalsIgnoreCase("description")){
            parser = new IParsingContext(){
                String TAG_NAME = "description";
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
                    setDescription(text);
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
        else if(qName.equalsIgnoreCase("time")){
            parser = new IParsingContext(){
                String TAG_NAME = "time";
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
                    setTime(Long.parseLong(text));
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
        else if(qName.equalsIgnoreCase("lastStatus")){
            parser = new IParsingContext(){
                String TAG_NAME = "lastStatus";
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
                    setLastStatus(Integer.parseInt(text));
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
        else if(qName.equalsIgnoreCase("status")){
            parser = new IParsingContext(){
                String TAG_NAME = "status";
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
                    setStatus(Integer.parseInt(text));
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
        else{
            parser = new IParsingContext(){
                String TAG_NAME = "modifiedBy";
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
                    setModifiedBy(Integer.parseInt(text));
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
