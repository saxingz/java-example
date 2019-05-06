package org.saxing.objectmother;

/**
 * Object Mother Pattern generating Royalty Types
 *
 * @author saxing 2019/5/6 11:32
 */
public final class RoyaltyObjectMother {

    public static King createSoberUnhappyKing() {
        return new King();
    }

    public static King createDrunkKing() {
        King king = new King();
        king.makeDrunk();
        return king;
    }

    public static King createHappyKing() {
        King king = new King();
        king.makeHappy();
        return king;
    }

    public static King createHappyDrunkKing() {
        King king = new King();
        king.makeHappy();
        king.makeDrunk();
        return king;
    }

    public static Queen createFlirtyQueen() {
        Queen queen = new Queen();
        queen.setFlirtiness(true);
        return queen;
    }

    public static Queen createNotFlirtyQueen() {
        return new Queen();
    }


}
