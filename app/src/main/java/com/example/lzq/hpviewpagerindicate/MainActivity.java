package com.example.lzq.hpviewpagerindicate;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewpager;
    HpViewPagerIndicate mHpViewPagerIndicate;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewpager = (ViewPager) findViewById(R.id.viewpager_launch);
        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                textView = new TextView(MainActivity.this);
                textView.setText("111");
                container.addView(textView);
                return 1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(textView);
            }
        });

        mHpViewPagerIndicate = (HpViewPagerIndicate) findViewById(R.id.id_viewpagerindicate);
        mHpViewPagerIndicate.setViewPager(mViewpager);
    }
}
