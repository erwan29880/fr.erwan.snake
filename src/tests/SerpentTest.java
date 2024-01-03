package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calculs.Serpent;

public class SerpentTest {
    
    @Test
    public void initTest() {
        Serpent s = new Serpent();
        boolean check = s.testInit();
        assertEquals(true, check);
    }

}
