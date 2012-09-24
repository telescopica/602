/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel.parsers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;
import serempre.codegen.metamodel.ClassDescriptor;
import static serempre.codegen.metamodel.ClassDescriptorTest.*;
import static resources.ResourceUtility.*;
/**
 *
 * @author Thor
 */
public class ModelParserTest {
    
    public ModelParserTest() {
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

    @Test
    public void parseAModel(){
        //expected class count
        int expected_class_count = 2;
        //expected class names
        String expected_class_names[] = {"TestClassName","TestClassName2"};
        //xml document file name
        String xml_document_file_name = "SampleModel.xml";
        //create a model parsr that is to handle parsing operations
        ModelParser parser = new ModelParser();
        try {
            //parse a model
            parseFileUsingParsingContext(parser, xml_document_file_name);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ModelParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(ModelParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(ModelParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        //make sure there were 2 classes in the result set
        assertEquals(expected_class_count, parser.getClasses().size());
        //check parsed class names
        for(int i = 0; i < expected_class_count;i ++){
            assertNotNull(parser.getClasses().get(expected_class_names[i]));
        }
    }
    
    @Test
    public void parseAModelWithRelationships(){
        //expected class count
        int expected_class_count = 2;
        //expected class names
        String expected_class_names[] = {"TestClassName","TestClassName2"};
        //xml document file name
        String xml_document_file_name = "SampleModel2.xml";
        //create a model parsr that is to handle parsing operations
        ModelParser parser = new ModelParser();
        try {
            //parse a model
            parseFileUsingParsingContext(parser, xml_document_file_name);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ModelParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(ModelParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(ModelParserTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        //make sure there were 2 classes in the result set
        assertEquals(expected_class_count, parser.getClasses().size());
        //check parsed class names
        for(int i = 0; i < expected_class_count;i ++){
            assertNotNull(parser.getClasses().get(expected_class_names[i]));
        }
        //get owning side for the relationship
        ClassDescriptor owner = parser.getClasses().get(expected_class_names[0]);
        //make sure there is at least one relationship defined for owning type
        assertTrue(owner.getOwnedTypes().size()>0);
        //make sure the reference within TestClassName points to TestClassName2
        assertTrue(owner.getOwnedTypes().get(0).getTargetClass() == parser.getClasses().get(expected_class_names[1]));
        //make sure relationship type is one to one
        assertTrue(owner.getOwnedTypes().get(0).getType().toString().equals("one_to_one"));
    }
}
