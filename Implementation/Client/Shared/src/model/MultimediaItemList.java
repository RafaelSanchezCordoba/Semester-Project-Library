package model;

import java.util.ArrayList;

/**
 * The multimedia item class.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 08/04/22.
 */
public class MultimediaItemList {
    private ArrayList<MultimediaItem> multimediaItems = new ArrayList<MultimediaItem>();

    /**
     * Empty constructor.
     */
    public MultimediaItemList() {

    }

    /**
     * Add the multimedia item given as an argument to the list.
     * @param multimediaItem
     * The multimedia item.
     */
    public void addMultimediaItem(MultimediaItem multimediaItem) {
         multimediaItems.add(multimediaItem);
    }

    /**
     * Remove the multimedia item given as an argument from the list
     * @param multimediaItem
     * The multimedia item.         
     */
    public void removeMultimediaItem(MultimediaItem multimediaItem) {
        multimediaItems.remove(multimediaItem);
    }
}
