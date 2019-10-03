package com.itsaky.preui.adapters;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.itsaky.preui.fragments.Tab1Fragment;
import com.itsaky.preui.fragments.Tab2Fragment;
import com.itsaky.preui.fragments.Tab3Fragment;

public class PreviewTabAdapter extends FragmentPagerAdapter
{
	Context c;
	int tabCount;

	public PreviewTabAdapter(Context c,FragmentManager fm, int tabCount)
	{
		super(fm);
		this.c = c;
		this.tabCount = tabCount;
	}

	@Override
	public int getCount()
	{
		return tabCount;
	}

	@Override
	public Fragment getItem(int p1)
	{
		switch(p1){
			case 0:
				return new Tab1Fragment();
			case 1:
				return new Tab2Fragment();
				
			case 2:
				return new Tab3Fragment();
				
				default :
				return null;
		}
	}
	
}
