package ;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import serempre.jme.rms.*;
import serempre.jme.xml.*;

public class TestClassName2 extends IPersistable implements IParsingContext, IServerSideIdable {
    protected int mTestAttribute;
    protected String mTestAttribute2;
    protected static String TARGETED_BY_RELATIONSHIP_TEST_RELATIONSHIP2 = TestClassName.class.getName()+".TestRelationship2";
    protected static String TARGETED_BY_RELATIONSHIP_TEST_RELATIONSHIP3 = TestClassName.class.getName()+".TestRelationship3";
    public static String TAG_NAME = "TestClassName2";
    protected int mId;
    protected ConsumerContext mCtx;
    
    /**
    * default parameterless constructor
    */
    public TestClassName2() {
        super();
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_TEST_RELATIONSHIP2,TestClassName.class);
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_TEST_RELATIONSHIP3,TestClassName.class);
    }
    /**
    * value constructor
    * @param testAttribute initializes this class using this value for the corresponding attribute
    * @param testAttribute2 initializes this class using this value for the corresponding attribute
    */
    public TestClassName2(int testAttribute, String testAttribute2){
        super();
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_TEST_RELATIONSHIP2,TestClassName.class);
        getManyToOneMappingFor(TARGETED_BY_RELATIONSHIP_TEST_RELATIONSHIP3,TestClassName.class);
        mTestAttribute = testAttribute;
        mTestAttribute2 = testAttribute2;
    }
    /**
    * @return value of testAttribute property
    */
    public int getTestAttribute(){
        return mTestAttribute;
    }
    /**
    * @param testAttribute new value for testAttribute property
    */
    public void setTestAttribute(int testAttribute){
        mTestAttribute = testAttribute;
    }
    /**
    * @return value of testAttribute2 property
    */
    public String getTestAttribute2(){
        return mTestAttribute2;
    }
    /**
    * @param testAttribute2 new value for testAttribute2 property
    */
    public void setTestAttribute2(String testAttribute2){
        mTestAttribute2 = testAttribute2;
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
        dos.writeInt(mTestAttribute);
        dos.writeUTF(mTestAttribute2);
    }
    /**
    * interface implementation
    */
    public void readFromByteStream(DataInputStream dis) throws IOException {
        mTestAttribute = dis.readInt();
        mTestAttribute2 = dis.readUTF();
    }
    /**
    * interface implementation
    */
    public String getRecordStoreName() {
        //TODO: change this for something better
        return "TestClassName2".substring(1,5);
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
        if(qName.equalsIgnoreCase("testAttribute")){
            parser = new IParsingContext(){
                String TAG_NAME = "testAttribute";
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
                    setTestAttribute(Integer.parseInt(text));
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
                String TAG_NAME = "testAttribute2";
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
                    setTestAttribute2(text);
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
