/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel.parsers;

import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Thor
 */
public class ConsumerContext extends DefaultHandler {
    protected Stack<IParsingContext> mStack = null;
    public Hashtable<String,Object> Helpers = null;
    /**
     * parameterless constructor
     */
    public ConsumerContext(){
        mStack = new Stack<IParsingContext>();
        Helpers = new Hashtable<String, Object>();
    }
    /**
     * throws unsupported operation exception if there are no parsing contexts available
     */
    private void assertParsingContextIsAvailable(){
        IParsingContext context = mStack.size()>0?mStack.peek():null;
        if(context == null){
            throw new UnsupportedOperationException("parsing context stack is empty");
        }
    }
    /**
     * registers a handler to which all SAX events will be passed on to
     * @param parsingContext concrete implementation for IParsingContext
     */
    public void pushHandler(IParsingContext parsingContext){
        if(parsingContext==null){
            throw new UnsupportedOperationException("can't use null parsing context");
        }
        parsingContext.setConsumerContext(this);
        mStack.push(parsingContext);
    }
    /**
     * removes top handler so that SAX events are passed on to the previously registered element
     * @return concrete IParsingContext implementation
     */
    public IParsingContext popHandler(){
        IParsingContext removed = mStack.pop();
        removed.setConsumerContext(null);
        return removed;
    }
    /**
     * creates a handler for sax events and adds it to the stack, then forwards this method on to the new handler
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        assertParsingContextIsAvailable();
        mStack.peek().startElement(uri, localName, qName, attributes);
    }
    /**
     * removes a handler from the stack
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        assertParsingContextIsAvailable();
        mStack.peek().endElement(uri, localName, qName);
    }
    /**
     * forwards inner tag text to the handler on top of the stack
     */
    @Override
    public void characters(char ch[], int start, int length){
        assertParsingContextIsAvailable();
        mStack.peek().characters(new String(ch,start,length));
    }
}
