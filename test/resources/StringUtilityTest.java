/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Thor
 */
public class StringUtilityTest {
    
    public StringUtilityTest() {
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
     * Test of toU1Case method, of class StringUtility.
     */
    @Test
    public void testToU1Case() {
        String source = "myTestString";
        String expected = "MyTestString";
        StringUtility instance = new StringUtility();
        String result = instance.toU1Case(source);
        assertEquals(expected, result);
    }

    /**
     * Test of toL1Case method, of class StringUtility.
     */
    @Test
    public void testToL1Case() {
        String source = "MyTestString";
        StringUtility instance = new StringUtility();
        String expResult = "myTestString";
        String result = instance.toL1Case(source);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testToStaticVarName(){
        StringUtility instance = new StringUtility();
        String expected = "STATIC_NAME";
        String input = "staticName";
        String actual = instance.toStaticVarName(input);
        assertEquals(expected, actual);
    }
}
