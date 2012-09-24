package hive.models;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import serempre.jme.rms.*;
import serempre.jme.xml.*;

public class Item extends IPersistable implements IParsingContext, IServerSideIdable {
    protected String mCode;
    protected int mPackagingId;
    protected String mSKU;
    protected String mParentSKU;
    protected String mMasterSKU;
    protected String mThumbnailURL;
    protected String mPictureURL;
    public static String TAG_NAME = "Item";
    protected int mId;
    protected ConsumerContext mCtx;
    
    /**
    * default parameterless constructor
    */
    public Item() {
        super();
    }
    /**
    * value constructor
    * @param code initializes this class using this value for the corresponding attribute
    * @param packagingId initializes this class using this value for the corresponding attribute
    * @param SKU initializes this class using this value for the corresponding attribute
    * @param parentSKU initializes this class using this value for the corresponding attribute
    * @param masterSKU initializes this class using this value for the corresponding attribute
    * @param thumbnailURL initializes this class using this value for the corresponding attribute
    * @param pictureURL initializes this class using this value for the corresponding attribute
    */
    public Item(String code, int packagingId, String SKU, String parentSKU, String masterSKU, String thumbnailURL, String pictureURL){
        super();
        mCode = code;
        mPackagingId = packagingId;
        mSKU = SKU;
        mParentSKU = parentSKU;
        mMasterSKU = masterSKU;
        mThumbnailURL = thumbnailURL;
        mPictureURL = pictureURL;
    }
    /**
    * @return value of code property
    */
    public String getCode(){
        return mCode;
    }
    /**
    * @param code new value for code property
    */
    public void setCode(String code){
        mCode = code;
    }
    /**
    * @return value of packagingId property
    */
    public int getPackagingId(){
        return mPackagingId;
    }
    /**
    * @param packagingId new value for packagingId property
    */
    public void setPackagingId(int packagingId){
        mPackagingId = packagingId;
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
    * @return value of parentSKU property
    */
    public String getParentSKU(){
        return mParentSKU;
    }
    /**
    * @param parentSKU new value for parentSKU property
    */
    public void setParentSKU(String parentSKU){
        mParentSKU = parentSKU;
    }
    /**
    * @return value of masterSKU property
    */
    public String getMasterSKU(){
        return mMasterSKU;
    }
    /**
    * @param masterSKU new value for masterSKU property
    */
    public void setMasterSKU(String masterSKU){
        mMasterSKU = masterSKU;
    }
    /**
    * @return value of thumbnailURL property
    */
    public String getThumbnailURL(){
        return mThumbnailURL;
    }
    /**
    * @param thumbnailURL new value for thumbnailURL property
    */
    public void setThumbnailURL(String thumbnailURL){
        mThumbnailURL = thumbnailURL;
    }
    /**
    * @return value of pictureURL property
    */
    public String getPictureURL(){
        return mPictureURL;
    }
    /**
    * @param pictureURL new value for pictureURL property
    */
    public void setPictureURL(String pictureURL){
        mPictureURL = pictureURL;
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
        dos.writeUTF(mCode);
        dos.writeInt(mPackagingId);
        dos.writeUTF(mSKU);
        dos.writeUTF(mParentSKU);
        dos.writeUTF(mMasterSKU);
        dos.writeUTF(mThumbnailURL);
        dos.writeUTF(mPictureURL);
    }
    /**
    * interface implementation
    */
    public void readFromByteStream(DataInputStream dis) throws IOException {
        mCode = dis.readUTF();
        mPackagingId = dis.readInt();
        mSKU = dis.readUTF();
        mParentSKU = dis.readUTF();
        mMasterSKU = dis.readUTF();
        mThumbnailURL = dis.readUTF();
        mPictureURL = dis.readUTF();
    }
    /**
    * interface implementation
    */
    public String getRecordStoreName() {
        //TODO: change this for something better
        return "Item".substring(1,5);
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
        if(qName.equalsIgnoreCase("code")){
            parser = new IParsingContext(){
                String TAG_NAME = "code";
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
                    setCode(text);
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
        else if(qName.equalsIgnoreCase("packagingId")){
            parser = new IParsingContext(){
                String TAG_NAME = "packagingId";
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
                    setPackagingId(Integer.parseInt(text));
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
        else if(qName.equalsIgnoreCase("SKU")){
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
        else if(qName.equalsIgnoreCase("parentSKU")){
            parser = new IParsingContext(){
                String TAG_NAME = "parentSKU";
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
                    setParentSKU(text);
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
        else if(qName.equalsIgnoreCase("masterSKU")){
            parser = new IParsingContext(){
                String TAG_NAME = "masterSKU";
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
                    setMasterSKU(text);
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
        else if(qName.equalsIgnoreCase("thumbnailURL")){
            parser = new IParsingContext(){
                String TAG_NAME = "thumbnailURL";
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
                    setThumbnailURL(text);
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
                String TAG_NAME = "pictureURL";
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
                    setPictureURL(text);
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
