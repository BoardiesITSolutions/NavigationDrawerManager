package com.BoardiesITSolutions.NavigationDrawerManager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

public class NavigationManager {

	Activity activity;
	Context context;
	DrawerLayout drawerLayout;
	int drawerOpen;
	int drawerClosed;
	int drawerIcon;
	
	public NavigationManager(Activity activity, DrawerLayout drawerLayout, int drawerOpen, int drawerClosed)
	{
		this.activity = activity;
		this.drawerLayout = drawerLayout;
		this.drawerIcon = R.drawable.ic_drawer;
		this.drawerOpen = drawerOpen;
		this.drawerClosed = drawerClosed;
	}
	
	public NavigationManager(Activity activity, DrawerLayout drawerLayout, int drawerIcon, int drawerOpen, int drawerClosed)
	{
		this.activity = activity;
		this.drawerLayout = drawerLayout;
		this.drawerIcon = drawerIcon;
		this.drawerOpen = drawerOpen;
		this.drawerClosed = drawerClosed;
	}
	
	public ActionBarDrawerToggle setDrawerToggle()
	{	
		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
				activity, 
				drawerLayout, 
				R.drawable.ic_drawer, 
				drawerOpen, 
				drawerClosed)
		{
			public void onDrawerClosed(View view)
			{
				activity.invalidateOptionsMenu();
			}
			
			public void onDrawerOpened(View drawerView)
			{
				activity.invalidateOptionsMenu();
			}
		};
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
		{
			drawerLayout.setDrawerListener(actionBarDrawerToggle);
		}
		
		return actionBarDrawerToggle;
	}
	
	public void prepareActionBar()
	{
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
		{
			activity.getActionBar().setDisplayHomeAsUpEnabled(true);
			activity.getActionBar().setHomeButtonEnabled(true);
		}
	}
}
