package com.itsaky.preui.adapters;
import android.support.v7.widget.*;
import android.view.*;

import android.content.Context;
import android.widget.TextView;
import com.itsaky.preui.R;
import java.util.ArrayList;
import android.graphics.Color;
import com.itsaky.preui.utils.PreUtils;
import com.itsaky.preui.models.Colors;
import android.graphics.Typeface;

public class ColorsListAdapter extends RecyclerView.Adapter<ColorsListAdapter.ColorsListViewHolder>
{
	Context c;
	ArrayList<Colors> colors;
	private PreUtils utils;

	public ColorsListAdapter(Context c, ArrayList<Colors> colors)
	{
		this.c = c;
		this.colors = colors;
		utils = new PreUtils(c);
	}

	@Override
	public ColorsListViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View view = LayoutInflater.from(c).inflate(R.layout.layout_colors_list_item, p1, false);
		return new ColorsListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ColorsListViewHolder p1, int p2)
	{
		Colors c = colors.get(p2);
		p1.card.setCardBackgroundColor(Color.parseColor(c.getCode()));
		p1.color.setText(c.getCode());
		p1.name.setText(c.getName());
		p1.color.setTextColor(utils.isColorDark(Color.parseColor(c.getCode())) ? Color.WHITE : Color.BLACK);
		p1.name.setTextColor(utils.isColorDark(Color.parseColor(c.getCode())) ? Color.WHITE : Color.BLACK);
	}

	@Override
	public int getItemCount()
	{
		return colors.size();
	}
	
	
	public class ColorsListViewHolder extends RecyclerView.ViewHolder{
		CardView card;
		TextView color, name;
		
		public ColorsListViewHolder(View v){
			super(v);
			
			card = v.findViewById(R.id.colorsCard);
			color = v.findViewById(R.id.colorCode);
			name = v.findViewById(R.id.colorName);
		}
	}
}
