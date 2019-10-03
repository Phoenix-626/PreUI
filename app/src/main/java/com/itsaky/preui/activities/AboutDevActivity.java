package com.itsaky.preui.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.itsaky.preui.R;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.RelativeLayout;
import android.net.Uri;
import android.content.Intent;
import android.content.ActivityNotFoundException;

public class AboutDevActivity extends AppCompatActivity
{
	private Toolbar toolbar;
	private RelativeLayout git, insta, tele, email, yt;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView(R.layout.activity_about_dev);
		
		toolbar = (Toolbar) findViewById(R.id.activityaboutdevToolbar1);
		git = (RelativeLayout) findViewById(R.id.activityaboutdevRelativeLayout1);
		insta = (RelativeLayout) findViewById(R.id.activityaboutdevRelativeLayout2);
		tele = (RelativeLayout) findViewById(R.id.activityaboutdevRelativeLayout3);
		email = (RelativeLayout) findViewById(R.id.activityaboutdevRelativeLayout4);
		yt = (RelativeLayout) findViewById(R.id.activityaboutdevRelativeLayout5);
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Developer");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
		
		toolbar.setNavigationOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					onBackPressed();
				}
			});
			
		git.setOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					Uri uri = Uri.parse("https://github.com/itsaky");
					Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(likeIng);
				}
			});
			
		insta.setOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					Uri uri = Uri.parse("http://instagram.com/_u/__itsaky");
					Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

					likeIng.setPackage("com.instagram.android");

					try {
						startActivity(likeIng);
					} catch (ActivityNotFoundException e) {
						startActivity(new Intent(Intent.ACTION_VIEW,
												 Uri.parse("http://instagram.com/__itsaky")));
					}
				}
			});
			
		tele.setOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					Uri uri = Uri.parse("http://t.me/itsaky01");
					Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

					likeIng.setPackage("org.telegram.messenger");

					try {
						startActivity(likeIng);
					} catch (ActivityNotFoundException e) {
						startActivity(new Intent(Intent.ACTION_VIEW,
												 Uri.parse("http://t.me/itsaky01")));
					}
				}
			});
			
		email.setOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					Intent i = new Intent(Intent.ACTION_SEND);
					i.putExtra(Intent.EXTRA_EMAIL, new String[]{"itsaky01@gmail.com"});
					i.putExtra(Intent.EXTRA_SUBJECT, "");
					i.setType("message/rfc882");
					i.setPackage("com.google.android.gm");
					try {
						startActivity(i);
					} catch (ActivityNotFoundException e){
						Intent i1 = new Intent(Intent.ACTION_SEND);
						i1.putExtra(Intent.EXTRA_EMAIL, new String[]{"itsaky01@gmail.com"});
						i1.putExtra(Intent.EXTRA_SUBJECT, "");
						i1.setType("message/rfc882");
						startActivity(i1);
					}
				}
			});
			
		yt.setOnClickListener (new View.OnClickListener (){

				@Override
				public void onClick(View p1)
				{
					Uri uri = Uri.parse("https://youtube.com/c/ItsAky");
					Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(likeIng);
				}
			});
	}

	@Override
	public void onBackPressed()
	{
		// TODO: Implement this method
		super.onBackPressed ();
		finish();
	}
}
