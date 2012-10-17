/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serempre.codegen.metamodel.parsers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.xml.sax.SAXException;
import serempre.codegen.metamodel.Merger;
import static resources.ResourceUtility.*;
import org.junit.*;
import static org.junit.Assert.*;
import serempre.codegen.metamodel.ClassDescriptor;
/**
 *
 * @author Thor
 */
public class MergerTest extends Merger {
    public enum TEMPLATE_TEST { JME, DJANGO };
    protected java.util.Stack<String> mFilesToDelete = new Stack<String>();
    protected boolean mDeleteTargetFiles = false;
    protected TEMPLATE_TEST mTemplateToTest = TEMPLATE_TEST.JME;
    
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
        if(mDeleteTargetFiles){
            while(!mFilesToDelete.empty()){
                File toDelete = new File(mFilesToDelete.pop());
                toDelete.delete();
            }
        }
    }
    
    @Test
    public void testMerge(){
        MergerTest test = new MergerTest();
        test.mTemplateToTest = TEMPLATE_TEST.JME;
        try {
            test.init();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        try {
            test.start("SampleModel3.xml", "output");
            String [] expectedFilenames = new String[]{ ".java","2.java" };
            Iterator<String> producedFiles = mFilesToDelete.iterator();
            int index = 0;
            while(producedFiles.hasNext()){
                String file = producedFiles.next();
                assertEquals(expectedFilenames[index++], file);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testMerge2(){
        MergerTest test = new MergerTest();
        test.mTemplateToTest = TEMPLATE_TEST.JME;
        try {
            test.init();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        try {
            test.mVelocityContext.put("packagename", "hive.models");
            test.start("Orders.xml", "output");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testMerge3(){
        MergerTest test = new MergerTest();
        test.mTemplateToTest = TEMPLATE_TEST.DJANGO;
        try {
            test.init();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        try {
            test.mVelocityContext.put("packagename", "hive.models");
            test.start("Orders.xml", "output");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testMerge4(){
        MergerTest test = new MergerTest();
        test.mTemplateToTest = TEMPLATE_TEST.DJANGO;
        try {
            test.init();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
        try {
            test.mVelocityContext.put("packagename", "goal.models");
            test.start("Goal.xml", "output");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (SAXException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        } catch (IOException ex) {
            Logger.getLogger(MergerTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Override
    public String getTemplateName() {
        //template location is regarded as the template's name
        String templateName = null;
        switch(mTemplateToTest){
            case JME:
                templateName = "resources/jme/class.vsl";
                break;
            case DJANGO:
                templateName = "resources/django/class.vsl";
                break;
        }
        return templateName;
    }

    @Override
    public boolean bulkTransformation() {
        boolean bulk = true;
        switch(mTemplateToTest){
            case JME:
                bulk = false;
                break;
            case DJANGO:
                bulk = true;
                break;
        }
        return bulk;
    }

    @Override
    public String getPackageName() {
        return "hive.models";
    }

    @Override
    public String getTargetFileExtension() {
        String fileExtension = "";
        switch(mTemplateToTest){
            case JME:
                fileExtension = "java";
                break;
            case DJANGO:
                fileExtension = "py";
                break;
        }
        return fileExtension;
    }
}
