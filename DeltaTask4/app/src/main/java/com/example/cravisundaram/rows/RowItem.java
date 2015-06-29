package com.example.cravisundaram.rows;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by C RAVI SUNDARAM on 28-05-2015.
 */
public class RowItem {
    private int imageId;
    private String title;
    Button b;

    public RowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }
}

