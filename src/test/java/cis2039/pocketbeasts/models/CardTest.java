/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cis2039.pocketbeasts.models;

import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Steven Mead
 * @author Rhy Kemp
 */
public class CardTest {
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Card.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Card instance = new Card("id", "name", 1, 2, 3);
        String expResult = "id";
        try {
            String result = instance.getId();
            assertEquals(expResult, result);
            System.out.println("getId: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("getId: Test 1 failed");
            throw e;
        }
    }

    /**
     * Test of getName method, of class Card.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Card instance = new Card("id", "name", 1, 2, 3);
        String expResult = "name";
        try {
            String result = instance.getName();
            assertEquals(expResult, result);
            System.out.println("getName: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("getName: Test 1 failed");
            throw e;
        }
    }

    /**
     * Test of getManaCost method, of class Card.
     */
    @Test
    public void testGetManaCost() {
        System.out.println("getManaCost");
        Card instance = new Card("id", "name", 1, 2, 3);
        int expResult = 1;
        try {
            int result = instance.getManaCost();
            assertEquals(expResult, result);
            System.out.println("getManaCost: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("getManaCost: Test 1 failed");
            throw e;
        }
    }

    /**
     * Test of getAttack method, of class Card.
     */
    @Test
    public void testGetAttack() {
        System.out.println("getAttack");
        Card instance = new Card("id", "name", 1, 2, 3);
        int expResult = 2;
        try {
            int result = instance.getAttack();
            assertEquals(expResult, result);
            System.out.println("getAttack: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("getAttack: Test 1 failed");
            throw e;
        }
    }

    /**
     * Test of getHealth method, of class Card.
     */
    @Test
    public void testGetHealth() {
        System.out.println("getHealth");
        Card instance = new Card("id", "name", 1, 2, 3);
        int expResult = 3;
        try {
            int result = instance.getHealth();
            assertEquals(expResult, result);
            System.out.println("getHealth: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("getHealth: Test 1 failed");
            throw e;
        }
    }

    /**
     * Test of damage method, of class Card.
     */
    @Test
    public void testDamage() {
        System.out.println("damage");
        Card instance = new Card("id", "name", 1, 2, 3);
        int amount = 1;
        int expResult = 2;
        try {
            instance.damage(amount);
            assertEquals(expResult, instance.getHealth());
        } catch (AssertionError e) {
            System.out.println("damage: Test 1 failed");
            throw e;
        }
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card("id", "name", 1, 2, 3);
        String expResult = "name (id) Mana Cost/1 Attack/2 Health/3";
        try {
            String result = instance.toString();
            assertEquals(expResult, result);
            System.out.println("toString: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("toString: Test 1 failed");
            throw e;
        }

    }

    /**
     * Test of compareTo method, of class Card.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Card o = new Card("id2", "name", 1, 2, 3);
        Card instance = new Card("id", "name", 1, 2, 3);
        int expResult = 0;
        try {
            int result = instance.compareTo(o);
            assertEquals(expResult, result);
            System.out.println("compareTo: Test 1 passed");
        } catch (AssertionError e) {
            System.out.println("compareTo: Test 1 failed");
            throw e;
        }
    }
    
}
