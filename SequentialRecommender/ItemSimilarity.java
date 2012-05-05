/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

/**
 *
 * @author sarahejones, sns
 */
public class ItemSimilarity {
    private Item mainItem;
    private Item neighborItem;
    private double similarity;

    public ItemSimilarity(Item main, Item neighbor, double s) {
        mainItem = main;
        neighborItem = neighbor;
        similarity = s;
    }

    public double getSimilarity() {
        return similarity;
    }

    public Item getNeighborItem() {
        return neighborItem;
    }
    

}
