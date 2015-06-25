package com.powerpoint45.lucidbrowser;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.ViewConfiguration;

public abstract class Tools {
	
	public static boolean isDownloadManagerAvailable(Context context) {
	    try {
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);
	        intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
	        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
	                PackageManager.MATCH_DEFAULT_ONLY);
	        return list.size() > 0;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public static void setActionBarColor(int c){
		ColorDrawable colorDrawable = new ColorDrawable(c);
  		MainActivity.actionBar.setBackgroundDrawable(colorDrawable);
	}


	@SuppressLint("NewApi")
	   public static boolean hasSoftNavigation(Context context)
	   {
		   if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
		        return !ViewConfiguration.get(context).hasPermanentMenuKey();
		    }
		    return true;
	   }
	public static int getStatusBarHeight(Resources res) {
        int result = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        } 
        return result;
  } 
  public static int getNavBarHeight(Resources res){
	   int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
	   if (resourceId > 0) {
	       return res.getDimensionPixelSize(resourceId);
	   }
	   return 0;
  }
  
  public static float pxToDp(Context context, float px) {
      if (context == null) {
          return -1;
      }
      return px / context.getResources().getDisplayMetrics().density;
  }
  
  public static int getStatusMargine(){
	  int margine =0;
		int id = MainActivity.activity.getResources().getIdentifier("config_enableTranslucentDecor", "bool", "android");
		if (Properties.appProp.transparentNav || Properties.appProp.TransparentStatus){
			if (id != 0) {
		        if (Properties.appProp.fullscreen && Properties.appProp.transparentNav){
		        	//do nothing
		        }else if (Properties.appProp.fullscreen){
		        	margine=Properties.ActionbarSize;
		        }else if (Properties.appProp.transparentNav){
		        	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		        		margine=Properties.ActionbarSize;
		        	else
		        		margine=Properties.ActionbarSize+Tools.getStatusBarHeight(MainActivity.activity.getResources());
		        }else{
		        	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		        		margine=Properties.ActionbarSize;
		        	else
		        		margine=Properties.ActionbarSize+Tools.getStatusBarHeight(MainActivity.activity.getResources());
		        }

		        if (Properties.appProp.fullscreen){
		        	margine=Properties.ActionbarSize;
		        }
		    }else
		    	margine = Properties.ActionbarSize;
		}else
			margine = Properties.ActionbarSize;
		
		return margine;
	}
  
  public static int getStatusSize(){
	  int margine =0;
		int id = MainActivity.activity.getResources().getIdentifier("config_enableTranslucentDecor", "bool", "android");
		if (Properties.appProp.transparentNav || Properties.appProp.TransparentStatus)
			if (id != 0) {
		        if (Properties.appProp.fullscreen && Properties.appProp.transparentNav){
		        	//do nothing
		        }else if (Properties.appProp.fullscreen){
		        	margine=0;
		        }else if (Properties.appProp.transparentNav){
		        	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		        		margine=0;
		        	else
		        		margine=Tools.getStatusBarHeight(MainActivity.activity.getResources());
		        }else{
		        	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		        		margine=0;
		        	else
		        		margine=Tools.getStatusBarHeight(MainActivity.activity.getResources());
		        }
		    }
		return margine;
	}
  
  public static int getActionBarSize(){
//	  int actionBarHeight = LayoutParams.MATCH_PARENT;//fallback size
//		TypedValue tv = new TypedValue();
//		if (MainActivity.activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
//		{
//		    actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,MainActivity.activity.getResources().getDisplayMetrics());
//		}
		
		return (int) MainActivity.activity.getResources().getDimension(R.dimen.actionBarSize);
  }

  
}
