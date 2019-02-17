package org.saxing.aa0015_56_iterator;

import org.saxing.aa0015_56_iterator.bst.BstIterator;
import org.saxing.aa0015_56_iterator.bst.TreeNode;
import org.saxing.aa0015_56_iterator.list.Item;
import org.saxing.aa0015_56_iterator.list.ItemType;
import org.saxing.aa0015_56_iterator.list.TreasureChest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.saxing.aa0015_56_iterator.list.ItemType.*;

/**
 * main
 *
 * @author saxing 2019/2/17 20:59
 */
public class IteratorApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IteratorApplication.class);

    private static final TreasureChest TREASURE_CHEST = new TreasureChest();

    private static void demonstrateTreasureChestIteratorForType(ItemType itemType) {
        LOGGER.info("------------------------");
        LOGGER.info("Item Iterator for ItemType " + itemType + ": ");
        Iterator<Item> itemIterator = TREASURE_CHEST.iterator(itemType);
        while (itemIterator.hasNext()) {
            LOGGER.info(itemIterator.next().toString());
        }
    }

    private static void demonstrateBstIterator() {
        LOGGER.info("------------------------");
        LOGGER.info("BST Iterator: ");
        TreeNode<Integer> root = buildIntegerBst();
        BstIterator bstIterator = new BstIterator<>(root);
        while (bstIterator.hasNext()) {
            LOGGER.info("Next node: " + bstIterator.next().getVal());
        }
    }

    private static TreeNode<Integer> buildIntegerBst() {
        TreeNode<Integer> root = new TreeNode<>(8);

        root.insert(3);
        root.insert(10);
        root.insert(1);
        root.insert(6);
        root.insert(14);
        root.insert(4);
        root.insert(7);
        root.insert(13);

        return root;
    }

    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        demonstrateTreasureChestIteratorForType(RING);
        demonstrateTreasureChestIteratorForType(POTION);
        demonstrateTreasureChestIteratorForType(WEAPON);
        demonstrateTreasureChestIteratorForType(ANY);

        demonstrateBstIterator();
    }

}

