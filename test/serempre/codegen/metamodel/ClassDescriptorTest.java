/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;
import serempre.codegen.metamodel.parsers.ConsumerContext;
import serempre.codegen.metamodel.parsers.IParsingContext;
import static resources.ResourceUtility.*;

/**
 *
 * @author Thor
 */
public class ClassDescriptorTest {
    
    public ClassDescriptorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    
    
    /**
     * test parsing an xml document that has a simple class description
     */
    @Test
    public void testParseClassDescriptor(){
        try {
            //expected class name, when parsing is done
            String expected_class_name = "TestClassName";
            //xml document file name
            String xml_document_file_name = "ClassSample.xml";
            //create a delegate parser which is to process sample document
            ClassDescriptor target = new ClassDescriptor();
            //parse an xml document using a classDescriptor
            parseFileUsingParsingContext(target, xml_document_file_name);
            //make sure expected class name matches actual class name
            assertTrue(expected_class_name.equals(target.getClassName()));
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testParseClassDescriptorWithAttributes(){
        //expected names for attributes
        String expected_attribute_names[] = {"testAttribute","testAttribute2"};
        //expected number of attributes
        int expected_attribute_count = 2;
        //expected class name, when parsing is done
        String expected_class_name = "TestClassName";
        //xml document file name
        String xml_document_file_name = "ClassSample2.xml";
        //create a delegate parser which is to process sample document
        ClassDescriptor target = new ClassDescriptor();
        try {
            //parse an xml document using a classDescriptor
            parseFileUsingParsingContext(target, xml_document_file_name);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        //make sure expected class name matches actual class name
        assertTrue(expected_class_name.equals(target.getClassName()));
        //make sure there are 2 attributes within the parsed class
        assertEquals(expected_attribute_count, target.getAttributes().size());
        //check attribute names
        for(int i = 0; i < expected_attribute_count; i++){
            assertTrue(target.getAttributes().get(i).getAttributeName().equals(expected_attribute_names[i]));
        }
        
    }
    
    @Test
    public void testPathToJarResource(){
        try {
            URI toFile = getURIToFile("resources/test.vsl", this.getClass());
            assertTrue(toFile != null);
            File testVelocityTemplate = new File(toFile);
            assertTrue(testVelocityTemplate.exists());
        } catch (URISyntaxException ex) {
            Logger.getLogger(ClassDescriptorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
}
