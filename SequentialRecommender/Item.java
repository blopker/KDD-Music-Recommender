/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

/**
 *
 * @author sarahejones, sns
 */
public class Item {
    private int id;
    private Neighborhood neighborhood;

    public int getId() {
        return id;
    }

    public void addToNeighborhood(ItemSimilarity is) {
        neighborhood.insert(is);
    }

    public void print() {
        System.out.println(id);
        neighborhood.print();
    }


}
