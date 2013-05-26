package com.BoardiesITSolutions.NavigationDrawerManager;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class NavigationManagerAdapter extends ArrayAdapter<NavigationMenuItem>{

	public NavigationManagerAdapter(Context context)
	{
		super(context, 0);
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		try
		{
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_menu_item, null);
			
			if (getItem(position).iconRes != -1)
			{
				ImageView icon = (ImageView)convertView.findViewById(R.id.row_icon);
				icon.setVisibility(View.VISIBLE);
				icon.setImageResource(getItem(position).iconRes);
				if (getItem(position).onClickListener != null)
				{
					icon.setOnClickListener(getItem(position).onClickListener);
				}
			}
			
			switch (getItem(position).guiType)
			{
				case TEXTVIEW:
					TextView standardTextView = (TextView)convertView.findViewById(R.id.row_textview);
					standardTextView.setText(getItem(position).menuName);
					standardTextView.setVisibility(View.VISIBLE);
					if (getItem(position).onClickListener != null)
					{
						standardTextView.setOnClickListener(getItem(position).onClickListener);
					}
					if (getItem(position).tag != null)
					{
						standardTextView.setTag(getItem(position).tag);
					}
					break;
				case HEADER:
					TextView textViewHeader = (TextView)convertView.findViewById(R.id.row_header);
					textViewHeader.setText(getItem(position).menuName);
					textViewHeader.setVisibility(View.VISIBLE);
					break;
				case SWITCH:
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB)
					{
						Switch switchButton = (Switch)convertView.findViewById(R.id.row_switch);
						switchButton.setText(getItem(position).menuName);
						if (getItem(position).tag != null)
						{
							switchButton.setTag(getItem(position).tag);
						}
						switchButton.setVisibility(View.VISIBLE);
						switchButton.setOnCheckedChangeListener(getItem(position).onCheckedChangeListener);
						switchButton.setChecked(getItem(position).defaultValue);
					}
					else
					{
						throw new ComponentNotSupportedOnApiException("Switches are not supported in API Version: " + android.os.Build.VERSION.SDK_INT);
					}
					break;
				case TEXTVIEW_SUMMARY:
					TextView spannedTextView = (TextView)convertView.findViewById(R.id.row_textview);
					String menuTitle = getItem(position).menuName;
					String summary = getItem(position).summary;
					spannedTextView.setText(Html.fromHtml(menuTitle + "<br /><small><font color='#9f9f9f'>" + summary + "</font></small>"));
					spannedTextView.setVisibility(View.VISIBLE);
					if (getItem(position).onClickListener != null)
					{
						spannedTextView.setOnClickListener(getItem(position).onClickListener);
					}
					if (getItem(position).tag != null)
					{
						spannedTextView.setTag(getItem(position).tag);
					}
					break;
				case HEADER_SUMMARY:
					TextView spannedHeader = (TextView)convertView.findViewById(R.id.row_header);
					String headerTitle = getItem(position).menuName;
					String headerSummary= getItem(position).summary;
					spannedHeader.setText(Html.fromHtml(headerTitle + "<br /><small><font color='#000000'>" + headerSummary + "</font></small>"));
					spannedHeader.setVisibility(View.VISIBLE);
					break;
			}
		}
		catch (ComponentNotSupportedOnApiException ex)
		{
			Log.e("Adapter Error", ex.toString());
		}
		return convertView;
	}

}
