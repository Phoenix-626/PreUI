package com.itsaky.preui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.itsaky.preui.R;
import android.view.View.OnClickListener;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import com.itsaky.preui.utils.PreUtils;
import com.itsaky.preui.adapters.ColorShadesAdapter;
import android.support.v7.widget.LinearLayoutManager;
import com.itsaky.preui.recycler.RecyclerTouchListener;
import com.itsaky.preui.recycler.ClickListener;

public class ShadesActivity extends AppCompatActivity 
{
    private Toolbar toolbar;
	private RecyclerView rView;
	private ArrayList<String> colors = new ArrayList<>();
	private PreUtils utils;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shades);
        toolbar = (Toolbar) findViewById(R.id.shadesToolbar);
		rView = (RecyclerView) findViewById(R.id.shadesRecyclerView);
		
        setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
		
		utils = new PreUtils(this);
		
		colors = utils.getColorShades(getIntent().getExtras().getString("color"));
		ColorShadesAdapter a = new ColorShadesAdapter(this, colors);
		rView.setHasFixedSize(true);
		rView.setLayoutManager(new LinearLayoutManager(ShadesActivity.this));
		rView.setAdapter(a);
		
		toolbar.setNavigationOnClickListener (new OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					onBackPressed();
				}
			});
			
			
		rView.addOnItemTouchListener (new RecyclerTouchListener (this, rView, new ClickListener (){

											  @Override
											  public void onClick(View view, int position)
											  {
												  utils.copyToClipboard(colors.get(position));
												  utils.toast(colors.get(position) + " copied!");
											  }

											  @Override
											  public void onLongClick(View view, int position)
											  {}
										  }));
    }

	@Override
	public void onBackPressed()
	{
		super.onBackPressed ();
		finish();
	}
}

