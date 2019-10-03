package com.itsaky.preui.fragments;

import android.view.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.itsaky.preui.R;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import com.itsaky.preui.adapters.ColorsListAdapter;
import android.support.v7.widget.GridLayoutManager;
import com.itsaky.preui.utils.PreUtils;
import android.support.v7.widget.LinearLayoutManager;
import com.itsaky.preui.models.Colors;
import com.itsaky.preui.recycler.RecyclerTouchListener;
import com.itsaky.preui.recycler.ClickListener;
import android.content.Intent;
import com.itsaky.preui.activities.ShadesActivity;

public class ColorsFragment extends Fragment
{
	private RecyclerView recyclerView;
	private PreUtils utils;
	
	public ColorsFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_colors, container, false);
		
		recyclerView = view.findViewById(R.id.colorsRecyclerView);
		utils = new PreUtils(getActivity());
		
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated (view, savedInstanceState);
		
		final ArrayList<Colors> colors = new ArrayList<Colors>();
		
		colors.add(new Colors("RED", "#F44336"));
		colors.add(new Colors("PINK", "#E91E63"));
		colors.add(new Colors("PURPLE", "#9C27B0"));
		colors.add(new Colors("DEEP PURPLE", "#673AB7"));
		colors.add(new Colors("INDIGO", "#3F51B5"));
		colors.add(new Colors("BLUE", "#2196F3"));
		colors.add(new Colors("LIGHT BLUE", "#03A9F4"));
		colors.add(new Colors("CYAN", "#00BCD4"));
		colors.add(new Colors("TEAL", "#009688"));
		colors.add(new Colors("GREEN", "#4CAF50"));
		colors.add(new Colors("LIGHT GREEN", "#8BC34A"));
		colors.add(new Colors("LIME", "#CDDC39"));
		colors.add(new Colors("YELLOW", "#FFEB3B"));
		colors.add(new Colors("AMBER", "#FFC107"));
		colors.add(new Colors("ORANGE", "#FF9800"));
		colors.add(new Colors("DEEP ORANGE", "#FF5722"));
		colors.add(new Colors("BROWN", "#795548"));
		colors.add(new Colors("GREY", "#9E9E9E"));
		colors.add(new Colors("BLUE GREY", "#607D8B"));
		
		ColorsListAdapter a = new ColorsListAdapter(getActivity(), colors);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
		recyclerView.setAdapter(a);
		
		recyclerView.addOnItemTouchListener (new RecyclerTouchListener (getActivity (), recyclerView, new ClickListener (){

													 @Override
													 public void onClick(View view, int position)
													 {
														 startActivity(new Intent(getActivity(), ShadesActivity.class)
														 		.putExtra("color", colors.get(position).getCode()));
													 }

													 @Override
													 public void onLongClick(View view, int position)
													 {
														 utils.copyToClipboard(colors.get(position).getCode());
														 utils.toast(colors.get(position).getCode() + " copied!");
													 }
												 }));
	}
}
