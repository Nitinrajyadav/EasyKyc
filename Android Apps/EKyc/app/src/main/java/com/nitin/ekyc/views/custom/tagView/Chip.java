package com.nitin.ekyc.views.custom.tagView;

import android.graphics.drawable.Drawable;

/**
 * Created by Nitin on 3/16/2017.
 */

public class Chip {

    public int id;
    public String text;
    public int tagTextColor;
    public float tagTextSize;
    public int layoutColor;
    public int layoutColorPress;
    public boolean isDeletable;
    public int deleteIndicatorColor;
    public float deleteIndicatorSize;
    public float radius;
    public String deleteIcon;
    public float layoutBorderSize;
    public int layoutBorderColor;
    public Drawable background;


    public Chip(String text) {
        init(0, text, ChipsConstants.DEFAULT_TAG_TEXT_COLOR, ChipsConstants.DEFAULT_TAG_TEXT_SIZE, ChipsConstants.DEFAULT_TAG_LAYOUT_COLOR, ChipsConstants.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
                ChipsConstants.DEFAULT_TAG_IS_DELETABLE, ChipsConstants.DEFAULT_TAG_DELETE_INDICATOR_COLOR, ChipsConstants.DEFAULT_TAG_DELETE_INDICATOR_SIZE, ChipsConstants.DEFAULT_TAG_RADIUS, ChipsConstants.DEFAULT_TAG_DELETE_ICON, ChipsConstants.DEFAULT_TAG_LAYOUT_BORDER_SIZE, ChipsConstants.DEFAULT_TAG_LAYOUT_BORDER_COLOR);
    }

    private void init(int id, String text, int tagTextColor, float tagTextSize,
                      int layoutColor, int layoutColorPress, boolean isDeletable,
                      int deleteIndicatorColor, float deleteIndicatorSize, float radius,
                      String deleteIcon, float layoutBorderSize, int layoutBorderColor) {
        this.id = id;
        this.text = text;
        this.tagTextColor = tagTextColor;
        this.tagTextSize = tagTextSize;
        this.layoutColor = layoutColor;
        this.layoutColorPress = layoutColorPress;
        this.isDeletable = isDeletable;
        this.deleteIndicatorColor = deleteIndicatorColor;
        this.deleteIndicatorSize = deleteIndicatorSize;
        this.radius = radius;
        this.deleteIcon = deleteIcon;
        this.layoutBorderSize = layoutBorderSize;
        this.layoutBorderColor = layoutBorderColor;
    }
}
