package hive.models;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import serempre.jme.rms.*;
import serempre.jme.xml.*;

public class OrderItem extends IPersistable implements IParsingContext, IServerSideIdable {
    protected int mAmount;
    protected float mDiscount;
    protected String mSKU;
    protected static String OWNED_RELATIONSHIP_ITEM = "item";
    protected static String TARGETED_BY_RELATIONSHIP_ITEMS = Order.class.getName()+".items";
    public static String TAG_NAME = "OrderItem";
    protected int mId;
    protected ConsumerContext mCtx;
    
    /**
    * default parameterless constructor
    */
    public OrderItem() {
        super();
        getOneToOneMappingFor(OWNED_RELATIONSHIP_ITEM,CatalogItem.class);
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_ITEMS,Order.class);
    }
    /**
    * value constructor
    * @param amount initializes this class using this value for the corresponding attribute
    * @param discount initializes this class using this value for the corresponding attribute
    * @param SKU initializes this class using this value for the corresponding attribute
    */
    public OrderItem(int amount, float discount, String SKU){
        super();
        getOneToOneMappingFor(OWNED_RELATIONSHIP_ITEM,CatalogItem.class);
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_ITEMS,Order.class);
        mAmount = amount;
        mDiscount = discount;
        mSKU = SKU;
    }
    /**
    * @return value of amount property
    */
    public int getAmount(){
        return mAmount;
    }
    /**
    * @param amount new value for amount property
    */
    public void setAmount(int amount){
        mAmount = amount;
    }
    /**
    * @return value of discount property
    */
    public float getDiscount(){
        return mDiscount;
    }
    /**
    * @param discount new value for discount property
    */
    public void setDiscount(float discount){
        mDiscount = discount;
    }
    /**
    * @return value of SKU property
    */
    public String getSKU(){
        return mSKU;
    }
    /**
    * @param SKU new value for SKU property
    */
    public void setSKU(String SKU){
        mSKU = SKU;
    }
    /**
    * @return value for item property
    */
    public CatalogItem getItem(){
        return (CatalogItem)getOneToOneMappingFor(OWNED_RELATIONSHIP_ITEM).getTarget();
    }
    /**
    * @param item new value for item property
    */
    public void setItem(CatalogItem item){
        getOneToOneMappingFor(OWNED_RELATIONSHIP_ITEM).setTarget(item);
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
        dos.writeInt(mAmount);
        dos.writeFloat(mDiscount);
        dos.writeUTF(mSKU);
    }
    /**
    * interface implementation
    */
    public void readFromByteStream(DataInputStream dis) throws IOException {
        mAmount = dis.readInt();
        mDiscount = dis.readFloat();
        mSKU = dis.readUTF();
    }
    /**
    * interface implementation
    */
    public String getRecordStoreName() {
        //TODO: change this for something better
        return "OrderItem".substring(1,5);
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
        if(qName.equalsIgnoreCase("amount")){
            parser = new IParsingContext(){
                String TAG_NAME = "amount";
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
                    setAmount(Integer.parseInt(text));
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
        else if(qName.equalsIgnoreCase("discount")){
            parser = new IParsingContext(){
                String TAG_NAME = "discount";
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
                    setDiscount(Float.parseFloat(text));
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
                String TAG_NAME = "SKU";
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
                    setSKU(text);
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
