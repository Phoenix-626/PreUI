package com.itsaky.preui.utils;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.os.Environment;
import java.io.FileInputStream;
import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import java.util.ArrayList;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.downloader.PRDownloader;
import com.downloader.OnDownloadListener;
import com.downloader.Error;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.app.ProgressDialog;

public class PreUtils
{
	private static Context c;
	private static String apkUrl = null;

	public PreUtils(Context c)
	{
		this.c = c;
	}
	
	public static void toast(String s){
		Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
	}
	
	public static int darkenColor(int color, float SHADE_FACTOR) {
        return Color.rgb((int)(SHADE_FACTOR * Color.red(color)),
						 (int)(SHADE_FACTOR * Color.green(color)),
						 (int)(SHADE_FACTOR * Color.blue(color)));
    }
	
	public static int lighten(int color, float fraction) {
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		red = lightenColor(red, fraction);
		green = lightenColor(green, fraction);
		blue = lightenColor(blue, fraction);
		int alpha = Color.alpha(color);
		return Color.argb(alpha, red, green, blue);
	}
	
	private static int lightenColor(int color, float fraction) {
		return (int) Math.min(color + (color * fraction), 255);
	}
	
	public static String getHexColorCode(int color){
		return String.format("#%08x", color).toUpperCase();
	}
	
	public static boolean isColorDark(int color){
		double darkness = 1-(0.299*Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
		if(darkness<0.5){
			return false;
		}else{
			return true;
		}
	}
	
	public static void copyToClipboard(String text){
		ClipboardManager clipboard = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE); 
		ClipData clip = ClipData.newPlainText("PreUI", text);
		clipboard.setPrimaryClip(clip);
	}
	
	public static void setOverflowButtonColor(final Toolbar toolbar, final int color) {
		Drawable drawable = toolbar.getOverflowIcon();
		if(drawable != null) {
			drawable = DrawableCompat.wrap(drawable);
			DrawableCompat.setTint(drawable.mutate(), color);
			toolbar.setOverflowIcon(drawable);
		}
	}
	
	public static ArrayList<String> getColorShades(String colorCode){
		ArrayList<String> colors = new ArrayList<>();
		
		final int color = Color.parseColor(colorCode);
		
		int l1 = lighten(color, 0.3f);
		int l2 = lighten(color, 0.4f);
		int l3 = lighten(color, 0.5f);
		int l4 = lighten(color, 0.6f);
		
		int d1 = darkenColor(color, 0.8f);
		int d2 = darkenColor(color, 0.7f);
		int d3 = darkenColor(color, 0.6f);
		int d4 = darkenColor(color, 0.5f);
		int d5 = darkenColor(color, 0.4f);
		colors.add(getHexColorCode(color));
		colors.add(getHexColorCode(l4));
		colors.add(getHexColorCode(l3));
		colors.add(getHexColorCode(l2));
		colors.add(getHexColorCode(l1));
		colors.add(getHexColorCode(color));
		colors.add(getHexColorCode(d1));
		colors.add(getHexColorCode(d2));
		colors.add(getHexColorCode(d3));
		colors.add(getHexColorCode(d4));
		colors.add(getHexColorCode(d5));
		
		return colors;
	}
	
	public static void sendEmail(String subject, String message){
		Intent i = new Intent(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"itsaky01@gmail.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		i.putExtra(Intent.EXTRA_TEXT, message);
		i.setType("message/rfc882");
		i.setPackage("com.google.android.gm");
		try {
			c.startActivity(i);
		} catch (ActivityNotFoundException e){
			Intent i1 = new Intent(Intent.ACTION_SEND);
			i1.putExtra(Intent.EXTRA_EMAIL, new String[]{"itsaky01@gmail.com"});
			i1.putExtra(Intent.EXTRA_SUBJECT, subject);
			i.putExtra(Intent.EXTRA_TEXT, message);
			i1.setType("message/rfc882");
			c.startActivity(i1);
		}
	}
	
	public static boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager 
			= (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
};
