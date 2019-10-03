package com.itsaky.preui.fragments;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import com.itsaky.preui.R;
import android.widget.TextView;

public class Tab1Fragment extends Fragment
{
	public Tab1Fragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_tab_preview, container, false);
		return v;
	}
}
