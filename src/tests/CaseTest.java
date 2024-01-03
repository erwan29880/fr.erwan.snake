package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import calculs.Case;
import calculs.Coords;
import enums.Direction;

public class CaseTest {
 
    @Test
    public void newPosTest3() {
        int xInit = 10;
        int yInit = 10;
        Case c = new Case(xInit, yInit, Direction.S);
        int dim = c.getDim();
        Coords c1 = c.newPos();
        assertEquals(xInit, c1.getX());
        assertEquals(yInit + dim, c1.getY());
    } 

}
