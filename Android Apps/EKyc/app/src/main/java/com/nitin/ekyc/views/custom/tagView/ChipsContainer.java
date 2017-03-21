package com.nitin.ekyc.views.custom.tagView;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nitin.ekyc.R;
import com.nitin.ekyc.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin on 3/16/2017.
 */

public class ChipsContainer extends RelativeLayout {

    /**
     * tag list
     */
    private List<Chip> mChips = new ArrayList<>();

    /**
     * System Service
     */
    private LayoutInflater mInflater;
    private ViewTreeObserver mViewTreeObserber;

    /**
     * listeners
     */
    private OnTagClickListener mClickListener;
    private OnTagDeleteListener mDeleteListener;
    private OnTagLongClickListener mTagLongClickListener;

    /**
     * view size param
     */
    private int mWidth;

    /**
     * layout initialize flag
     */
    private boolean mInitialized = false;

    /**
     * custom layout param
     */
    private int lineMargin;
    private int tagMargin;
    private int textPaddingLeft;
    private int textPaddingRight;
    private int textPaddingTop;
    private int textPaddingBottom;


    /**
     * constructor
     *
     * @param ctx
     */
    public ChipsContainer(Context ctx) {
        super(ctx, null);
        initialize(ctx, null, 0);
    }

    /**
     * constructor
     *
     * @param ctx
     * @param attrs
     */
    public ChipsContainer(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        initialize(ctx, attrs, 0);
    }

    /**
     * constructor
     *
     * @param ctx
     * @param attrs
     * @param defStyle
     */
    public ChipsContainer(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        initialize(ctx, attrs, defStyle);
    }

    /**
     * initalize instance
     *
     * @param ctx
     * @param attrs
     * @param defStyle
     */
    private void initialize(Context ctx, AttributeSet attrs, int defStyle) {
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewTreeObserber = getViewTreeObserver();
        mViewTreeObserber.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!mInitialized) {
                    mInitialized = true;
                    Log.e("onGlobalLayout: ", "-->" + mInitialized);
                    drawTags();
                }
            }
        });

        // get AttributeSet
        TypedArray typeArray = ctx.obtainStyledAttributes(attrs, R.styleable.ChipsContainer, defStyle, defStyle);
        this.lineMargin = (int) typeArray.getDimension(R.styleable.ChipsContainer_lineMargin, CommonUtils.dipToPx(this.getContext(), ChipsConstants.DEFAULT_LINE_MARGIN));
        this.tagMargin = (int) typeArray.getDimension(R.styleable.ChipsContainer_tagMargin, CommonUtils.dipToPx(this.getContext(), ChipsConstants.DEFAULT_TAG_MARGIN));
        this.textPaddingLeft = (int) typeArray.getDimension(R.styleable.ChipsContainer_textPaddingLeft, CommonUtils.dipToPx(this.getContext(), ChipsConstants.DEFAULT_TAG_TEXT_PADDING_LEFT));
        this.textPaddingRight = (int) typeArray.getDimension(R.styleable.ChipsContainer_textPaddingRight, CommonUtils.dipToPx(this.getContext(), ChipsConstants.DEFAULT_TAG_TEXT_PADDING_RIGHT));
        this.textPaddingTop = (int) typeArray.getDimension(R.styleable.ChipsContainer_textPaddingTop, CommonUtils.dipToPx(this.getContext(), ChipsConstants.DEFAULT_TAG_TEXT_PADDING_TOP));
        this.textPaddingBottom = (int) typeArray.getDimension(R.styleable.ChipsContainer_textPaddingBottom, CommonUtils.dipToPx(this.getContext(), ChipsConstants.DEFAULT_TAG_TEXT_PADDING_BOTTOM));
        typeArray.recycle();
    }

    /**
     * onSizeChanged
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        if (width <= 0)
            return;
        mWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTags();
    }

    /**
     * tag draw
     */
    private void drawTags() {

        if (!mInitialized) {
            Log.e("drawTags: ", "-->" + mInitialized);

//            return;
        }


        // layout padding left & layout padding right
        float total = getPaddingLeft() + getPaddingRight();

        int listIndex = 1;// List Index
        int indexBottom = 1;// The Chip to add below
        int indexHeader = 1;// The header tag of this line
        Chip chipPre = null;
        for (Chip item : mChips) {
            final int position = listIndex - 1;
            final Chip chip = item;
            // inflate chip layout
            View tagLayout = mInflater.inflate(R.layout.chip, null);
            tagLayout.setId(listIndex);
            tagLayout.setBackground(getSelector(chip));
            // chip text
            TextView tagView = (TextView) tagLayout.findViewById(R.id.tv_tag_item_contain);
            tagView.setText(chip.text);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tagView.getLayoutParams();
            params.setMargins(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);
            tagView.setLayoutParams(params);
            tagView.setTextColor(chip.tagTextColor);
            tagView.setTextSize(TypedValue.COMPLEX_UNIT_SP, chip.tagTextSize);

            // calculate　of chip layout width
            float tagWidth = tagView.getPaint().measureText(chip.text) + textPaddingLeft + textPaddingRight;
            // tagView padding (left & right)
            // deletable text
            TextView deletableView = (TextView) tagLayout.findViewById(R.id.tv_tag_item_delete);
            if (chip.isDeletable) {
                deletableView.setVisibility(View.VISIBLE);
                deletableView.setText(chip.deleteIcon);
                int offset = CommonUtils.dipToPx(getContext(), 2f);
                deletableView.setPadding(offset, textPaddingTop, textPaddingRight + offset, textPaddingBottom);
                deletableView.setTextColor(chip.deleteIndicatorColor);
                deletableView.setTextSize(TypedValue.COMPLEX_UNIT_SP, chip.deleteIndicatorSize);
                deletableView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mDeleteListener != null) {
                            mDeleteListener.onTagDeleted(ChipsContainer.this, chip, position);
                        }
                    }
                });
                tagWidth += deletableView.getPaint().measureText(chip.deleteIcon) + textPaddingLeft + textPaddingRight;
                // deletableView Padding (left & right)
            } else {
                deletableView.setVisibility(View.GONE);
            }

            LayoutParams tagParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //add margin of each line
            tagParams.bottomMargin = lineMargin;

            if (mWidth <= total + tagWidth + CommonUtils.dipToPx(this.getContext(), ChipsConstants.LAYOUT_WIDTH_OFFSET)) {
                //need to add in new line
                if (chipPre != null) tagParams.addRule(RelativeLayout.BELOW, indexBottom);
                // initialize total param (layout padding left & layout padding right)
                total = getPaddingLeft() + getPaddingRight();
                indexBottom = listIndex;
                indexHeader = listIndex;
            } else {
                //no need to new line
                tagParams.addRule(RelativeLayout.ALIGN_TOP, indexHeader);
                //not header of the line
                if (listIndex != indexHeader) {
                    tagParams.addRule(RelativeLayout.RIGHT_OF, listIndex - 1);
                    tagParams.leftMargin = tagMargin;
                    total += tagMargin;
                    if (chipPre.tagTextSize < chip.tagTextSize) {
                        indexBottom = listIndex;
                    }
                }
            }
            total += tagWidth;
            addView(tagLayout, tagParams);
            chipPre = chip;
            listIndex++;
        }
    }

    private Drawable getSelector(Chip chip) {
        if (chip.background != null)
            return chip.background;

        StateListDrawable states = new StateListDrawable();
        GradientDrawable gdNormal = new GradientDrawable();
        gdNormal.setColor(chip.layoutColor);
        gdNormal.setCornerRadius(chip.radius);
        if (chip.layoutBorderSize > 0) {
            gdNormal.setStroke(CommonUtils.dipToPx(getContext(), chip.layoutBorderSize), chip.layoutBorderColor);
        }
        GradientDrawable gdPress = new GradientDrawable();
        gdPress.setColor(chip.layoutColorPress);
        gdPress.setCornerRadius(chip.radius);
        states.addState(new int[]{android.R.attr.state_pressed}, gdPress);
        //must add state_pressed first，or state_pressed will not take effect
        states.addState(new int[]{}, gdNormal);
        return states;
    }


    //public methods
    //----------------- separator  -----------------//

    /**
     * @param chip
     */
    public void addChip(Chip chip) {

        mChips.add(chip);
        drawTags();
    }

    public void addChips(List<Chip> chips) {
        if (chips == null) return;
        mChips = new ArrayList<>();
        if (chips.isEmpty())
            drawTags();
        for (Chip item : chips) {
            addChip(item);
        }
    }

    public void addTags(String[] tags) {
        if (tags == null) return;
        for (String item : tags) {
            Chip chip = new Chip(item);
            addChip(chip);
        }
    }


    /**
     * get tag list
     *
     * @return mChips TagObject List
     */
    public List<Chip> getTags() {
        return mChips;
    }

    /**
     * remove tag
     *
     * @param position
     */
    public void remove(int position) {
        if (position < mChips.size()) {
            mChips.remove(position);
            drawTags();
        }
    }

    /**
     * remove all views
     */
    public void removeAll() {
        mChips.clear(); //clear all of tags
        removeAllViews();
    }

    public int getLineMargin() {
        return lineMargin;
    }

    public void setLineMargin(float lineMargin) {
        this.lineMargin = CommonUtils.dipToPx(getContext(), lineMargin);
    }

    public int getTagMargin() {
        return tagMargin;
    }

    public void setTagMargin(float tagMargin) {
        this.tagMargin = CommonUtils.dipToPx(getContext(), tagMargin);
    }

    public int getTextPaddingLeft() {
        return textPaddingLeft;
    }

    public void setTextPaddingLeft(float textPaddingLeft) {
        this.textPaddingLeft = CommonUtils.dipToPx(getContext(), textPaddingLeft);
    }

    public int getTextPaddingRight() {
        return textPaddingRight;
    }

    public void setTextPaddingRight(float textPaddingRight) {
        this.textPaddingRight = CommonUtils.dipToPx(getContext(), textPaddingRight);
    }

    public int getTextPaddingTop() {
        return textPaddingTop;
    }

    public void setTextPaddingTop(float textPaddingTop) {
        this.textPaddingTop = CommonUtils.dipToPx(getContext(), textPaddingTop);
    }

    public int gettextPaddingBottom() {
        return textPaddingBottom;
    }

    public void settextPaddingBottom(float textPaddingBottom) {
        this.textPaddingBottom = CommonUtils.dipToPx(getContext(), textPaddingBottom);
    }


    /**
     * setter for OnTagLongClickListener
     *
     * @param longClickListener
     */
    public void setOnTagLongClickListener(OnTagLongClickListener longClickListener) {
        mTagLongClickListener = longClickListener;
    }

    /**
     * setter for OnTagSelectListener
     *
     * @param clickListener
     */
    public void setOnTagClickListener(OnTagClickListener clickListener) {
        mClickListener = clickListener;
    }

    /**
     * setter for OnTagDeleteListener
     *
     * @param deleteListener
     */
    public void setOnTagDeleteListener(OnTagDeleteListener deleteListener) {
        mDeleteListener = deleteListener;
    }

    /**
     * Listeners
     */
    public interface OnTagDeleteListener {
        void onTagDeleted(ChipsContainer view, Chip chip, int position);
    }

    public interface OnTagClickListener {
        void onTagClick(Chip chip, int position);
    }

    public interface OnTagLongClickListener {
        void onTagLongClick(Chip chip, int position);
    }
}
