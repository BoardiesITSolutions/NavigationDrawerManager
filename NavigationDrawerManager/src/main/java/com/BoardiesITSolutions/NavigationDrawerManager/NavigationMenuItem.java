package com.BoardiesITSolutions.NavigationDrawerManager;

/**
 * 
 * @author Chris Board
 * Copyright Boardies IT Solutions 2013
 * 
 * This software is provided as OpenSource and you are able to modify
 * it in any way you see fit. The only thing that we request is that
 * all copyright notices are retained
 *
 */

import java.util.ArrayList;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class NavigationMenuItem
{
	public enum GuiType {HEADER, SUBHEADER, SUB_TEXTVIEW, TEXTVIEW, SWITCH, TEXTVIEW_SUMMARY, HEADER_SUMMARY, BUTTON, DIVIDER};
	
	public String menuName;
	public String tag;
	public GuiType guiType;
	public String summaryOn;
	public String summaryOff;
	public OnClickListener onClickListener;
	public OnCheckedChangeListener onCheckedChangeListener;
	public String summary;
	public int iconRes = -1;
	public Boolean defaultValue;
	public ArrayList<String> spinnerItems;
	public boolean isBorderless = false;
	public ArrayList<String> listItems;
	public Context context;
    public boolean selected;


    /**
     * This empty constructor is to add a divider into your navigation drawer
     */
    public NavigationMenuItem()
    {
        this.guiType = GuiType.DIVIDER;
    }

	/**
	 * This is used for a text view that doesn't have an image
	 * @param menuName The text to display to the user
	 * @param tag The text that is used to determine what menu item was pressed
	 * @param guiType NavigationMenuType.GuiType.TEXTVIEW should be passed
	 * @param onClickListener The specific on click listener for this menu item if not using a generic ListView on click listener (Pass null if using the standard listview onclick listener
	 */
	public NavigationMenuItem(String menuName, String tag, GuiType guiType, boolean selected, OnClickListener onClickListener)
	{
		this.menuName = menuName;
		this.tag = tag;
		this.guiType = guiType;
        this.selected = selected;
		this.onClickListener = onClickListener;
	}
	
	/**
	 * This is used for a textview with a summary. I.e. small description about the menu item
	 * @param menuName The text that is displayed to the user
	 * @param tag The tag that is used to determine the menu item that is clicked
	 * @param guiType NavigationMenuItem.GuiType.TEXTVIEW_SUMMARY should be passed
	 * @param summary The summary description that should be used 
	 * @param onClickListener The specific onClickListener to be used. Pass null if using the generic list view on click listener 
	 */
	public NavigationMenuItem(String menuName, String tag, GuiType guiType, String summary, boolean selected, OnClickListener onClickListener)
	{
		this.menuName = menuName;
		this.tag = tag;
		this.guiType = guiType;
		this.summary = summary;
        this.selected = selected;
		this.onClickListener = onClickListener;
	}
	
	/**
	 * This is used for standard text views with an icon. An R.drawable should be passed
	 * @param menuName The text to display to the user
	 * @param tag The tag to determine what menu item was clicked for generic onclick listener
	 * @param guiType NavigationMenuItem.GuiType.TEXTVIEW should be passed
	 * @param iconRes An int resource from R.drawable
	 * @param onClickListener The specific onclicklistener that should be used. Pass null if using the list view onclick listener
	 */
	public NavigationMenuItem(String menuName, String tag, GuiType guiType, int iconRes, OnClickListener onClickListener)
	{
		this.menuName = menuName;
		this.tag = tag;
		this.guiType = guiType;
		this.iconRes = iconRes;
		this.onClickListener = onClickListener;
	}
	
	/**
	 * This is used for a textview with summary and icon
	 * @param menuName What should be displayed to the user
	 * @param tag Used to determine what item was clicked from the generic List onclick listener
	 * @param guiType NavigationMenuItem.GuiType.TEXTVIEW_SUMMARY should be passed in here
	 * @param summary The summary to be displayed
	 * @param iconRes The R.drawable int for the icon that should be displayed
	 * @param onClickListener The specific onclicklistener, pass null if using the generic listview onclick listner
	 */
	public NavigationMenuItem(String menuName, String tag, GuiType guiType, String summary, boolean selected, int iconRes, OnClickListener onClickListener)
	{
		this.menuName = menuName;
		this.tag = tag;
		this.guiType = guiType;
		this.summary = summary;
		this.iconRes = iconRes;
        this.selected = selected;
		this.onClickListener = onClickListener;
	}
	
	/**
	 * This constructor is used for switches
	 * @param menuName The text that is displayed to the user
	 * @param tag The tag that can be used for generalised preference click listener
	 * @param summaryOn Summary to be displayed when switch is turned on
	 * @param summaryOff Summary to be displayed when switch is turned off
	 * @param The onpreferenceclick handler to use
	 */
	public NavigationMenuItem(String menuName, String tag, String summaryOn, String summaryOff, OnCheckedChangeListener onCheckedChangedListener, Boolean defaultValue)
	{
		this.menuName = menuName;
		this.tag = tag;
		this.summaryOn = summaryOn;
		this.summaryOff = summaryOff;
		this.guiType = GuiType.SWITCH;
		this.onCheckedChangeListener = onCheckedChangedListener;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * This menu item should be used for headers within the menu
	 * @param menuName The text that is shown to the user
	 */
	public NavigationMenuItem(String menuName)
	{
		this.menuName = menuName;
		this.guiType = GuiType.HEADER;
	}
	
	public NavigationMenuItem(String menuName, GuiType guiType)
	{
		this.menuName = menuName;
		this.guiType = GuiType.SUBHEADER;
	}
	
	/**
	 * The menu item is used for headers that also show summaries
	 * @param The text that is displayed to the user
	 * @param The summary or description of the menu header
     * @deprecated Header with summaries will not be supported in a later version. Headers with summaries are not part of Google Design Specs
	 */
    @Deprecated
	public NavigationMenuItem(String menuName, String summary)
	{
		this.menuName = menuName;
		this.summary = summary;
		this.guiType = GuiType.HEADER_SUMMARY;
	}
	
	public NavigationMenuItem(String menuName, boolean isBorderless, OnClickListener onClickListener)
	{
		this.menuName = menuName;
		this.isBorderless = isBorderless;
		this.onClickListener = onClickListener;
		this.guiType = GuiType.BUTTON;
	}
}