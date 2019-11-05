package com.lifetime.pokemonapi_app.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.lifetime.pokemonapi_app.R;
import com.lifetime.pokemonapi_app.fragment.StatFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity_main);

        NestedScrollView scrollView = findViewById(R.id.scrollable);
        scrollView.setFillViewport(true);

        SmartTabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        final LinearLayout lyTabs = (LinearLayout) tabLayout.getChildAt(0);

        FragmentPagerItems pages = new FragmentPagerItems(this);

        pages.add(FragmentPagerItem.of("stats",StatFragment.class));
        pages.add(FragmentPagerItem.of("evolutions",StatFragment.class));
        pages.add(FragmentPagerItem.of("moves",StatFragment.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),pages);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viewPager.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        changeTabsTitleColor(lyTabs,0);
        setTextStyle(lyTabs);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabsTitleColor(lyTabs,position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setTextStyle(LinearLayout ly){
        for(int j = 0; j < ly.getChildCount();j++){
            TextView tvTabTitle = (TextView) ly.getChildAt(j);
            tvTabTitle.setTypeface(null, Typeface.NORMAL);
        }
    }

    private void changeTabsTitleColor(LinearLayout ly, int position){
        for(int j = 0; j < ly.getChildCount();j++){
            TextView tvTabTitle = (TextView) ly.getChildAt(j);
            tvTabTitle.setTextColor(getResources().getColor(R.color.dark_blue));
            tvTabTitle.setTypeface(null, Typeface.NORMAL);
            if(j == position) tvTabTitle.setTextColor(getResources().getColor(R.color.white_two));
        }
    }
}
