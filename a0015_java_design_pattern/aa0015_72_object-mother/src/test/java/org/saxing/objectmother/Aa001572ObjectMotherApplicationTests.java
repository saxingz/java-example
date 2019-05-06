package org.saxing.objectmother;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Aa001572ObjectMotherApplicationTests {

    @Test
    public void unsuccessfulKingFlirt() {
        King soberUnhappyKing = RoyaltyObjectMother.createSoberUnhappyKing();
        Queen flirtyQueen = RoyaltyObjectMother.createFlirtyQueen();
        soberUnhappyKing.flirt(flirtyQueen);
        assertFalse(soberUnhappyKing.isHappy());
    }

    @Test
    public void queenIsBlockingFlirtCauseDrunkKing() {
        King drunkUnhappyKing = RoyaltyObjectMother.createDrunkKing();
        Queen notFlirtyQueen = RoyaltyObjectMother.createNotFlirtyQueen();
        drunkUnhappyKing.flirt(notFlirtyQueen);
        assertFalse(drunkUnhappyKing.isHappy());
    }

    @Test
    public void queenIsBlockingFlirt() {
        King soberHappyKing = RoyaltyObjectMother.createHappyKing();
        Queen notFlirtyQueen = RoyaltyObjectMother.createNotFlirtyQueen();
        soberHappyKing.flirt(notFlirtyQueen);
        assertFalse(soberHappyKing.isHappy());
    }

    @Test
    public void successfullKingFlirt() {
        King soberHappyKing = RoyaltyObjectMother.createHappyKing();
        Queen flirtyQueen = RoyaltyObjectMother.createFlirtyQueen();
        soberHappyKing.flirt(flirtyQueen);
        assertTrue(soberHappyKing.isHappy());
    }

    @Test
    public void testQueenType() {
        Royalty flirtyQueen = RoyaltyObjectMother.createFlirtyQueen();
        Royalty notFlirtyQueen = RoyaltyObjectMother.createNotFlirtyQueen();
        assertEquals(flirtyQueen.getClass(), Queen.class);
        assertEquals(notFlirtyQueen.getClass(), Queen.class);
    }

    @Test
    public void testKingType() {
        Royalty drunkKing = RoyaltyObjectMother.createDrunkKing();
        Royalty happyDrunkKing = RoyaltyObjectMother.createHappyDrunkKing();
        Royalty happyKing = RoyaltyObjectMother.createHappyKing();
        Royalty soberUnhappyKing = RoyaltyObjectMother.createSoberUnhappyKing();
        assertEquals(drunkKing.getClass(), King.class);
        assertEquals(happyDrunkKing.getClass(), King.class);
        assertEquals(happyKing.getClass(), King.class);
        assertEquals(soberUnhappyKing.getClass(), King.class);
    }

}
