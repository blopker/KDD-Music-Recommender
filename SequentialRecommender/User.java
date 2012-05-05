/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

import java.util.ArrayList;

/**
 *
 * @author sarahejones, sns
 */
public class User {
    private static int id;
    private ArrayList<RatedItem> ratedItems;
    private int ratingSum;

    public boolean rated(Item item) {
        for (RatedItem ri : ratedItems) {
            if (ri.getItem().getId() == item.getId())
                return true;
        }
        return false;
    }

    public int rating(Item item) {
        for (RatedItem ri : ratedItems) {
            if (ri.getItem().getId() == item.getId())
                return ri.getRating();
        }
        throw new RuntimeException("Checked rating for an item that a user hasn't rated.");
    }

    public double averageRating() {
        return ((double)ratingSum) / ratedItems.size();
    }



}
