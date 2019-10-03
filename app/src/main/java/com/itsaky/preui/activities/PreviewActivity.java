package com.itsaky.preui.activities;

import android.support.v7.app.*;
import android.view.*;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import com.itsaky.preui.R;
import com.itsaky.preui.adapters.PreviewTabAdapter;
import com.itsaky.preui.utils.PreUtils;
import android.support.design.widget.FloatingActionButton;
import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.drawable.ColorDrawable;
import android.widget.LinearLayout;
import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import android.Manifest;
import android.content.pm.PackageManager;

public class PreviewActivity extends AppCompatActivity 
{
    private Toolbar toolbar;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private DrawerLayout drawer;
	private FloatingActionButton fab;
	private NavigationView nav;
	private TextView headerT;
	private TextView headerD;
	private ActionBarDrawerToggle toggle;
	
	private PreUtils utils;
	
	private int colorPrimary, colorPrimaryDark, colorSecondary;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
		
        toolbar = (Toolbar) findViewById(R.id.previewToolbar);
		tabLayout = (TabLayout) findViewById(R.id.previewTabLayout);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		drawer = (DrawerLayout) findViewById(R.id.previewDrawerLayout);
		fab = (FloatingActionButton) findViewById(R.id.previewFab);
		nav = (NavigationView) findViewById(R.id.previewNavigation);
		
		tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
		tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
		tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

        setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
		toggle.syncState();
		drawer.addDrawerListener(toggle);
		
		utils = new PreUtils(this);
		
		Bundle extras = getIntent().getExtras();
		colorPrimary = extras.getInt("colorPrimary");
		colorPrimaryDark = extras.getInt("colorPrimaryDark");
		colorSecondary = extras.getInt("colorSecondary");
		
		toolbar.setBackgroundColor(colorPrimary);
		tabLayout.setSelectedTabIndicatorColor(colorSecondary);
		tabLayout.setBackgroundColor(colorPrimary);
		
		Window window = getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		window.setStatusBarColor(colorPrimaryDark);
		
		tabLayout.setSelectedTabIndicatorHeight(10);
		
		fab.setBackgroundTintList(ColorStateList.valueOf(colorSecondary));
		
		View header = nav.getHeaderView(0);
		RelativeLayout rl = header.findViewById(R.id.previewNavigationHeaderBackground);
		headerT = header.findViewById(R.id.previewNavHeaderTitleText);
		headerD = header.findViewById(R.id.previewNavHeaderDesText);
		rl.setBackgroundDrawable(new ColorDrawable(colorPrimary));
		if(utils.isColorDark(colorPrimary)){
			determineDarkerLayout();
		} else {
			determineLighterLayout();
		}
		
		final PreviewTabAdapter a = new PreviewTabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(a);
		
		viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		
		tabLayout.setOnTabSelectedListener (new TabLayout.OnTabSelectedListener (){

				@Override
				public void onTabSelected(TabLayout.Tab p1)
				{
					viewPager.setCurrentItem(p1.getPosition());
				}

				@Override
				public void onTabUnselected(TabLayout.Tab p1)
				{}

				@Override
				public void onTabReselected(TabLayout.Tab p1)
				{}
			});
    }

	private void determineDarkerLayout()
	{
		toolbar.setTitleTextColor(Color.WHITE);
		toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
		utils.setOverflowButtonColor(toolbar, Color.WHITE);
		tabLayout.setTabTextColors(utils.lighten(colorPrimary, 0.4f), Color.WHITE);
		headerT.setTextColor(Color.WHITE);
		headerD.setTextColor(Color.WHITE);
	}

	private void determineLighterLayout()
	{
		tabLayout.setTabTextColors(utils.lighten(colorPrimary, 0.4f), Color.BLACK);
		utils.setOverflowButtonColor(toolbar, Color.BLACK);
		toolbar.setTitleTextColor(Color.BLACK);
		toggle.getDrawerArrowDrawable().setColor(Color.BLACK);
		headerT.setTextColor(Color.BLACK);
		headerD.setTextColor(Color.BLACK);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.preview_options_menu, menu);
		return super.onCreateOptionsMenu (menu);
	}
	
}
