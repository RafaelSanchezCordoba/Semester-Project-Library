package model;

import java.util.ArrayList;

public class MultimediaItemList {
    private ArrayList<MultimediaItem> multimediaItems = new ArrayList<MultimediaItem>();

    public MultimediaItemList() {

    }

    public void addMultimediaItem(MultimediaItem multimediaItem) {
         multimediaItems.add(multimediaItem);
    }

    public void removeMultimediaItem(MultimediaItem multimediaItem) {
        multimediaItems.remove(multimediaItem);
    }
}
