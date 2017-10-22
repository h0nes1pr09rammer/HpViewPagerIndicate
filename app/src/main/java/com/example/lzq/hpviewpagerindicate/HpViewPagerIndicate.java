package com.example.lzq.hpviewpagerindicate;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lzq.hpviewpagerindicate.R;


/**
 * Created by B41-80 on 2016/7/4.
 */
public class HpViewPagerIndicate extends FrameLayout implements ViewPager.OnPageChangeListener{
    /**
     *圆点数量
     */
    private int mTabCount;
    private int mBackgroundItem;
    private int mShowGroundItem;
    private int mTablength;
//    private Paint mPaint;
    private Context mContext;
    private int mInitTranslationX;
    private float mTranslationX;
    TextView orageImageView;
    private Animation animation;
    public HpViewPagerIndicate(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicate);
        mTabCount = a.getInt(R.styleable.ViewPagerIndicate_item_count,2);
        mBackgroundItem = a.getColor(R.styleable.ViewPagerIndicate_background_tab_color,0);
        mShowGroundItem = a.getColor(R.styleable.ViewPagerIndicate_slide_tab_color,0);
        mTablength = a.getInt(R.styleable.ViewPagerIndicate_tab_length,20);
        a.recycle();
        addTabItem();
    }
    public HpViewPagerIndicate(Context context) {
        super(context);
        addTabItem();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mInitTranslationX = getWidth() / mTabCount / 2 - 8
                / 2;
    }

    public void setViewPager(ViewPager viewPager){
        viewPager.setOnPageChangeListener(this);
    }
    public void addTabItem(){
        LinearLayout linearLayout = new LinearLayout(mContext);
        FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(fLayoutParams);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int i=0;i<mTabCount;i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mTablength, mTablength);
            layoutParams.setMargins(8,8,8,8);
            TextView imageView = new TextView(mContext);
            imageView.setBackgroundColor(mBackgroundItem);
//            imageView.setText("柳");
            imageView.setLayoutParams(layoutParams);
            linearLayout.addView(imageView);
        }
        orageImageView = new TextView(mContext);
        orageImageView.setBackgroundColor(mShowGroundItem);
//        orageImageView.setText("柳");
        FrameLayout.LayoutParams f1LayoutParams = new FrameLayout.LayoutParams(mTablength,mTablength);
        f1LayoutParams.setMargins(8,8,8,8);
        orageImageView.setLayoutParams(f1LayoutParams);
        addView(linearLayout);
        addView(orageImageView);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mTranslationX = getWidth() / mTabCount * (position + positionOffset);
        orageImageView.setTranslationX(mTranslationX);
        if (onSelectedPageListener != null){
            onSelectedPageListener.onScroll(position,positionOffset,positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (onSelectedPageListener != null){
            onSelectedPageListener.onSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    OnSelectedPageListener onSelectedPageListener;
    public interface OnSelectedPageListener{
        void onSelected(int position);
        void onScroll(int position, float positionOffset, int positionOffsetPixels);
    }
    public void setOnSelectedPageListener(OnSelectedPageListener onSelectedPageListener){
        this.onSelectedPageListener = onSelectedPageListener;
    }
}
