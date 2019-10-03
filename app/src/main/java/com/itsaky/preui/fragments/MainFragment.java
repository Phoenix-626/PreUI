package com.itsaky.preui.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View.OnClickListener;
import com.itsaky.preui.R;
import com.itsaky.preui.activities.PreviewActivity;
import com.itsaky.preui.utils.PreUtils;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.LayoutInflater;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;
import com.orhanobut.dialogplus.DialogPlus;
import android.view.ViewGroup;
import com.orhanobut.dialogplus.ViewHolder;

public class MainFragment extends Fragment
{
	
	private CardView selectP, selectA;
	private Button preview, getColorsList;
	private TextView cP, cA;
	
	private View view;
	
	private PreUtils utils;

	private String colorPrimary = "#FFFFFFFF";
	private String colorAccent = "#FFB0AEA7";
	private int pC, pCL, pCD, sC, sCL, sCD;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if(view == null){
			view = inflater.inflate(R.layout.fragment_main, container, false);
		}
		
		selectP = view.findViewById(R.id.colorPrimaryContainer);
		selectA = view.findViewById(R.id.colorAccentContainer);
		preview = view.findViewById(R.id.mainButtonPreview);
		cP = view.findViewById(R.id.mainColorPrimaryTextView);
		cA = view.findViewById(R.id.mainColorAccentTextView);
		getColorsList = view.findViewById(R.id.mainButtonGetColors);
		
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		pC = Color.parseColor(colorPrimary);
		pCL = utils.lighten(pC, 0.3f);
		pCD = utils.darkenColor(pC, 0.8f);

		sC = Color.parseColor(colorAccent);
		sCL = utils.lighten(sC, 0.3f);
		sCD = utils.darkenColor(sC, 0.8f);
		
		selectP.setCardBackgroundColor(pC);
		selectA.setCardBackgroundColor(sC);
		
		cP.setTextColor(utils.isColorDark(Color.parseColor(colorPrimary)) ? Color.WHITE : Color.BLACK);
		cA.setTextColor(utils.isColorDark(Color.parseColor(colorPrimary)) ? Color.WHITE : Color.BLACK);
		
		selectP.setOnClickListener (new OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					int color = colorPrimary == null ? getResources().getColor(R.color.colorPrimary) : Color.parseColor(colorPrimary);

					int colorR = Color.red(color);
					int colorG = Color.green(color);
					int colorB = Color.blue(color);

					final ColorPicker cp = new ColorPicker(getActivity(), colorR, colorG, colorB);
					cp.enableAutoClose();
					cp.show();					
					cp.setCallback (new ColorPickerCallback (){

							@Override
							public void onColorChosen(int p1)
							{
								String pCP = String.format("#%08X", (0xFFFFFFFF & p1));
								selectP.setCardBackgroundColor(Color.parseColor(pCP));
								cP.setText(cP.getText().toString().replace(colorPrimary, pCP));
								colorPrimary = pCP;
								
								pC = Color.parseColor(colorPrimary);
								pCL = utils.lighten(pC, 0.4f);
								pCD = utils.darkenColor(pC, 0.8f);

								sC = Color.parseColor(colorAccent);
								sCL = utils.lighten(sC, 0.4f);
								sCD = utils.darkenColor(sC, 0.8f);
								
								cP.setTextColor(utils.isColorDark(Color.parseColor(colorPrimary)) ? Color.WHITE : Color.BLACK);
							}
						});
				}
			});

		selectA.setOnClickListener (new OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					int color = colorAccent == null ? getResources().getColor(R.color.colorAccent) : Color.parseColor(colorAccent);

					int colorR = Color.red(color);
					int colorG = Color.green(color);
					int colorB = Color.blue(color);

					final ColorPicker cp = new ColorPicker(getActivity(), colorR, colorG, colorB);
					cp.enableAutoClose();
					cp.show();					
					cp.setCallback (new ColorPickerCallback (){

							@Override
							public void onColorChosen(int p1)
							{
								String pCA = String.format("#%08X", (0xFFFFFFFF & p1));
								selectA.setCardBackgroundColor(Color.parseColor(pCA));
								cA.setText(cA.getText().toString().replace(colorAccent, pCA));
								colorAccent = pCA;
								
								pC = Color.parseColor(colorPrimary);
								pCL = utils.lighten(pC, 0.3f);
								pCD = utils.darkenColor(pC, 0.8f);

								sC = Color.parseColor(colorAccent);
								sCL = utils.lighten(sC, 0.3f);
								sCD = utils.darkenColor(sC, 0.8f);
								
								cA.setTextColor(utils.isColorDark(Color.parseColor(colorAccent)) ? Color.WHITE : Color.BLACK);
							}
						});
				}
			});

		preview.setOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(getActivity(), PreviewActivity.class)
								  .putExtra("colorPrimary", pC)
								  .putExtra("colorPrimaryDark", pCD)
								  .putExtra("colorSecondary", sC));
				}
			});

		getColorsList.setOnClickListener (new OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_get_colors, null);
					DialogPlus dialog = DialogPlus.newDialog(getActivity())
						.setContentHolder(new ViewHolder(view))
						.setPadding(10, 20, 10, 20)
						.create();

					CardView cP, cPL, cPD, cS, cSL, cSD;
					TextView cPT, cPLT, cPDT, cST, cSLT, csDT;

					cP = view.findViewById(R.id.getColorsPrimary);
					cPL = view.findViewById(R.id.getColorsPrimaryLight);
					cPD = view.findViewById(R.id.getColorsPrimaryDark);
					cPT = view.findViewById(R.id.getColorsPrimaryText);
					cPLT = view.findViewById(R.id.getColorsPrimaryLightText);
					cPDT = view.findViewById(R.id.getColorsPrimaryDarkText);

					cS = view.findViewById(R.id.getColorsSecondary);
					cSL = view.findViewById(R.id.getColorsSecondaryLight);
					cSD = view.findViewById(R.id.getColorsSecondaryDark);
					cST = view.findViewById(R.id.getColorsSecondaryText);
					cSLT = view.findViewById(R.id.getColorsSecondaryLightText);
					csDT = view.findViewById(R.id.getColorsSecondaryDarkText);

					cP.setCardBackgroundColor(pC);
					cPL.setCardBackgroundColor(pCL);
					cPD.setCardBackgroundColor(pCD);

					cPT.setText(utils.getHexColorCode(pC));
					cPLT.setText("Light\n" + utils.getHexColorCode(pCL));
					cPDT.setText("Dark\n" + utils.getHexColorCode(pCD));
					cPT.setTextColor(utils.isColorDark(pC) ? Color.WHITE : Color.BLACK);
					cPLT.setTextColor(utils.isColorDark(pCL) ? Color.WHITE : Color.BLACK);
					cPDT.setTextColor(utils.isColorDark(pCD) ? Color.WHITE : Color.BLACK);

					cST.setText(utils.getHexColorCode(sC));
					cSLT.setText("Light\n" + utils.getHexColorCode(sCL));
					csDT.setText("Dark\n" + utils.getHexColorCode(sCD));
					cST.setTextColor(utils.isColorDark(sC) ? Color.WHITE : Color.BLACK);
					cSLT.setTextColor(utils.isColorDark(sCL) ? Color.WHITE : Color.BLACK);
					csDT.setTextColor(utils.isColorDark(sCD) ? Color.WHITE : Color.BLACK);

					cS.setCardBackgroundColor(sC);
					cSL.setCardBackgroundColor(sCL);
					cSD.setCardBackgroundColor(sCD);

					dialog.show();

					cP.setOnClickListener (new OnClickListener (){

							@Override
							public void onClick(View p1)
							{
								utils.copyToClipboard(utils.getHexColorCode(pC));
								utils.toast("Copied successfully!");
							}
						});

					cPL.setOnClickListener (new OnClickListener (){

							@Override
							public void onClick(View p1)
							{
								utils.copyToClipboard(utils.getHexColorCode(pCL));
								utils.toast("Copied successfully!");
							}
						});

					cPD.setOnClickListener (new OnClickListener (){

							@Override
							public void onClick(View p1)
							{
								utils.copyToClipboard(utils.getHexColorCode(pCD));
								utils.toast("Copied successfully!");
							}
						});

					cS.setOnClickListener (new OnClickListener (){

							@Override
							public void onClick(View p1)
							{
								utils.copyToClipboard(utils.getHexColorCode(sC));
								utils.toast("Copied successfully!");
							}
						});

					cSL.setOnClickListener (new OnClickListener (){

							@Override
							public void onClick(View p1)
							{
								utils.copyToClipboard(utils.getHexColorCode(sCL));
								utils.toast("Copied successfully!");
							}
						});

					cSD.setOnClickListener (new OnClickListener (){

							@Override
							public void onClick(View p1)
							{
								utils.copyToClipboard(utils.getHexColorCode(sCD));
								utils.toast("Copied successfully!");
							}
						});
				}
			});
			
		super.onViewCreated (view, savedInstanceState);
	}
	
	public MainFragment(){}
}
