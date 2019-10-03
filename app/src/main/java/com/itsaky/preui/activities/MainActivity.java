package com.itsaky.preui.activities;

import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.view.*;
import com.downloader.*;
import com.itsaky.preui.fragments.*;
import com.itsaky.preui.utils.*;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import com.itsaky.preui.R;
import java.lang.Error;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private Toolbar toolbar;
	private DrawerLayout drawer;
	private NavigationView navigation;
	
	private ActionBarDrawerToggle toggle;
	private PreUtils utils;
	
	private MainFragment mFrag;
	private ColorsFragment cFrag;
	
	private String version = null;
	private String apkUrl = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
		
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
		drawer = (DrawerLayout) findViewById(R.id.mainDrawer);
		navigation = (NavigationView) findViewById(R.id.mainNavigation);
		
		utils = new PreUtils(this);
		
		toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
		toggle.syncState();
		drawer.addDrawerListener(toggle);
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationIcon(R.drawable.ic_drawer_indicator);
		
		try
		{
			version = getPackageManager ().getPackageInfo (getApplicationContext ().getPackageName (), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{}
		
		toolbar.setNavigationOnClickListener (new OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					if(drawer.isDrawerOpen(GravityCompat.START)){
						drawer.closeDrawer(GravityCompat.START);
					} else {
						drawer.openDrawer(GravityCompat.START);
					}
				}
			});
		
		mFrag = new MainFragment();
		cFrag = new ColorsFragment();
		
		navigation.setNavigationItemSelectedListener(this);
		
		displayScreen(R.id.previewUI);
    }
	
	private void displayScreen(int itemId){
		Fragment fragment = null;
		
		switch(itemId){
			case R.id.previewUI:
				fragment = mFrag;
			break;
			case R.id.materialColors:
				fragment = cFrag;
			break;
			case R.id.navReportBug:
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Package name : " + getApplicationContext().getPackageName().toString());
				stringBuilder.append("\nAPI : " + Build.VERSION.SDK);
				stringBuilder.append("\nDevice : " + Build.DEVICE);
				stringBuilder.append("\nModel : " + Build.MODEL);
				stringBuilder.append("\nProduct : " + Build.PRODUCT);
				stringBuilder.append("\nManufacturer : " + Build.MANUFACTURER);
				stringBuilder.append("\nBuild fingerprint : " + Build.FINGERPRINT);
				stringBuilder.append("\n\n Please describe what happened :\n");
				utils.sendEmail("PreUI : Bug Report", stringBuilder.toString());
			break;
			case R.id.navSuggest:
				utils.sendEmail("PreUI : Suggestion", null);
			break;
			case R.id.navAboutApp:
				final AlertDialog b = new AlertDialog.Builder(MainActivity.this).create();
				b.setIcon(R.drawable.ic_preui);
				b.setTitle("PreUI" + " v" + version);
				b.setMessage(getString(R.string.aboutMessage));
				b.setButton (AlertDialog.BUTTON_POSITIVE, "I understood", new DialogInterface.OnClickListener (){

						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							b.dismiss();
						}
					});
					
				b.show();
				
			break;
			case R.id.navAboutDev:
				startActivity(new Intent(MainActivity.this, AboutDevActivity.class));
			break;
		}
		
		if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.setCustomAnimations(R.anim.anim_enter, R.anim.anim_exit, R.anim.anim_pop_enter, R.anim.anim_pop_exit);
            ft.replace(R.id.mainFrameLayout, fragment);
            ft.commit();
        }
		
		drawer.closeDrawer(GravityCompat.START);
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem p1)
	{
		displayScreen(p1.getItemId());
		return true;
	}

	@Override
	protected void onPause()
	{
		super.onPause ();
	}

	@Override
	protected void onResume()
	{
		super.onResume ();
	}
	
}
