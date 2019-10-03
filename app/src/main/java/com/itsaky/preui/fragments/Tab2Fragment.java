package com.itsaky.preui.fragments;
import android.view.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.itsaky.preui.R;

public class Tab2Fragment extends Fragment
{
	public Tab2Fragment()
	{}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_tab_preview, container, false);

		((TextView)v.findViewById(R.id.fragmenttabpreviewTextView1)).setText(((TextView)v.findViewById(R.id.fragmenttabpreviewTextView1)).getText().toString().replace("1", "2"));

		return v;
	}
}
