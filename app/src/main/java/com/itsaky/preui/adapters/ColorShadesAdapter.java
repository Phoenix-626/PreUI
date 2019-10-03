package com.itsaky.preui.adapters;
import android.support.v7.widget.*;

import android.view.View;
import android.widget.TextView;
import com.itsaky.preui.R;
import android.view.ViewGroup;
import android.content.Context;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.graphics.Color;
import com.itsaky.preui.utils.PreUtils;

public class ColorShadesAdapter extends RecyclerView.Adapter<ColorShadesAdapter.ColorShadesViewHolder>
{
	Context c;
	ArrayList<String> colors;

	public ColorShadesAdapter(Context c, ArrayList<String> colors)
	{
		this.c = c;
		this.colors = colors;
	}

	@Override
	public ColorShadesAdapter.ColorShadesViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		return new ColorShadesViewHolder(LayoutInflater.from(c).inflate(R.layout.layout_colors_list_item, p1, false));
	}

	@Override
	public void onBindViewHolder(ColorShadesAdapter.ColorShadesViewHolder p1, int p2)
	{
		p1.card.setCardBackgroundColor(Color.parseColor(colors.get(p2)));
		p1.name.setText(colors.get(p2));
		p1.code.setVisibility(View.GONE);
		p1.name.setTextColor(new PreUtils(c).isColorDark(Color.parseColor(colors.get(p2))) ? Color.WHITE : Color.BLACK);
	}

	@Override
	public int getItemCount()
	{
		return colors.size();
	}
	
	
	public class ColorShadesViewHolder extends RecyclerView.ViewHolder {
		CardView card;
		TextView code, name;
		
		public ColorShadesViewHolder(View v){
			super(v);
			
			card = v.findViewById(R.id.colorsCard);
			code = v.findViewById(R.id.colorCode);
			name = v.findViewById(R.id.colorName);
		}
	}
}
