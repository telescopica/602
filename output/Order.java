package hive.models;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import serempre.jme.rms.*;
import serempre.jme.xml.*;

public class Order extends IPersistable implements IParsingContext, IServerSideIdable {
    protected String mOrderNumber;
    protected int mStatus;
    protected long mPlacementDate;
    protected long mDeliveryDate;
    protected long mEta;
    protected int mCreatedBy;
    protected int mClientId;
    protected static String OWNED_RELATIONSHIP_LAST_EVENT = "lastEvent";
    protected static String OWNED_RELATIONSHIP_EVENTS = "events";
    protected static String OWNED_RELATIONSHIP_ITEMS = "items";
    public static String TAG_NAME = "Order";
    protected int mId;
    protected ConsumerContext mCtx;
    
    /**
    * default parameterless constructor
    */
    public Order() {
        super();
        getOneToOneMappingFor(OWNED_RELATIONSHIP_LAST_EVENT,OrderEvent.class);
        getOneToManyMappingFor(OWNED_RELATIONSHIP_EVENTS,OrderEvent.class);
        getOneToManyMappingFor(OWNED_RELATIONSHIP_ITEMS,OrderItem.class);
    }
    /**
    * value constructor
    * @param orderNumber initializes this class using this value for the corresponding attribute
    * @param status initializes this class using this value for the corresponding attribute
    * @param placementDate initializes this class using this value for the corresponding attribute
    * @param deliveryDate initializes this class using this value for the corresponding attribute
    * @param eta initializes this class using this value for the corresponding attribute
    * @param createdBy initializes this class using this value for the corresponding attribute
    * @param clientId initializes this class using this value for the corresponding attribute
    */
    public Order(String orderNumber, int status, long placementDate, long deliveryDate, long eta, int createdBy, int clientId){
        super();
        getOneToOneMappingFor(OWNED_RELATIONSHIP_LAST_EVENT,OrderEvent.class);
        getOneToManyMappingFor(OWNED_RELATIONSHIP_EVENTS,OrderEvent.class);
        getOneToManyMappingFor(OWNED_RELATIONSHIP_ITEMS,OrderItem.class);
        mOrderNumber = orderNumber;
        mStatus = status;
        mPlacementDate = placementDate;
        mDeliveryDate = deliveryDate;
        mEta = eta;
        mCreatedBy = createdBy;
        mClientId = clientId;
    }
    /**
    * @return value of orderNumber property
    */
    public String getOrderNumber(){
        return mOrderNumber;
    }
    /**
    * @param orderNumber new value for orderNumber property
    */
    public void setOrderNumber(String orderNumber){
        mOrderNumber = orderNumber;
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
    * @return value of placementDate property
    */
    public long getPlacementDate(){
        return mPlacementDate;
    }
    /**
    * @param placementDate new value for placementDate property
    */
    public void setPlacementDate(long placementDate){
        mPlacementDate = placementDate;
    }
    /**
    * @return value of deliveryDate property
    */
    public long getDeliveryDate(){
        return mDeliveryDate;
    }
    /**
    * @param deliveryDate new value for deliveryDate property
    */
    public void setDeliveryDate(long deliveryDate){
        mDeliveryDate = deliveryDate;
    }
    /**
    * @return value of eta property
    */
    public long getEta(){
        return mEta;
    }
    /**
    * @param eta new value for eta property
    */
    public void setEta(long eta){
        mEta = eta;
    }
    /**
    * @return value of createdBy property
    */
    public int getCreatedBy(){
        return mCreatedBy;
    }
    /**
    * @param createdBy new value for createdBy property
    */
    public void setCreatedBy(int createdBy){
        mCreatedBy = createdBy;
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
    * @return value for lastEvent property
    */
    public OrderEvent getLastEvent(){
        return (OrderEvent)getOneToOneMappingFor(OWNED_RELATIONSHIP_LAST_EVENT).getTarget();
    }
    /**
    * @param lastEvent new value for lastEvent property
    */
    public void setLastEvent(OrderEvent lastEvent){
        getOneToOneMappingFor(OWNED_RELATIONSHIP_LAST_EVENT).setTarget(lastEvent);
    }
    /**
    * @return value for events property
    */
    public Vector getEvents(){
        return getOneToManyMappingFor(OWNED_RELATIONSHIP_EVENTS);
    }
    /**
    * @param newElements new value for events property
    */
    public void setEvents(Vector newElements){
        getOneToManyMappingFor(OWNED_RELATIONSHIP_EVENTS).replaceContents(newElements);
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
        dos.writeUTF(mOrderNumber);
        dos.writeInt(mStatus);
        dos.writeLong(mPlacementDate);
        dos.writeLong(mDeliveryDate);
        dos.writeLong(mEta);
        dos.writeInt(mCreatedBy);
        dos.writeInt(mClientId);
    }
    /**
    * interface implementation
    */
    public void readFromByteStream(DataInputStream dis) throws IOException {
        mOrderNumber = dis.readUTF();
        mStatus = dis.readInt();
        mPlacementDate = dis.readLong();
        mDeliveryDate = dis.readLong();
        mEta = dis.readLong();
        mCreatedBy = dis.readInt();
        mClientId = dis.readInt();
    }
    /**
    * interface implementation
    */
    public String getRecordStoreName() {
        //TODO: change this for something better
        return "Order".substring(1,5);
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
        if(qName.equalsIgnoreCase("orderNumber")){
            parser = new IParsingContext(){
                String TAG_NAME = "orderNumber";
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
                    setOrderNumber(text);
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
        else if(qName.equalsIgnoreCase("placementDate")){
            parser = new IParsingContext(){
                String TAG_NAME = "placementDate";
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
                    setPlacementDate(Long.parseLong(text));
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
        else if(qName.equalsIgnoreCase("deliveryDate")){
            parser = new IParsingContext(){
                String TAG_NAME = "deliveryDate";
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
                    setDeliveryDate(Long.parseLong(text));
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
        else if(qName.equalsIgnoreCase("eta")){
            parser = new IParsingContext(){
                String TAG_NAME = "eta";
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
                    setEta(Long.parseLong(text));
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
        else if(qName.equalsIgnoreCase("createdBy")){
            parser = new IParsingContext(){
                String TAG_NAME = "createdBy";
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
                    setCreatedBy(Integer.parseInt(text));
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
