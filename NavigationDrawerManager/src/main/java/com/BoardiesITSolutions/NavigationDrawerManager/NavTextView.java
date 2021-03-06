package com.BoardiesITSolutions.NavigationDrawerManager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 06/11/2014.
 */
public class NavTextView extends TextView implements View.OnClickListener
{
    //Each NavTextView that is created is stored in this list view, this is so we can
    //control style elements through the entire list


    OnClickListener wrappedOnClickListener;
    Context context;
    OnClickListener onClickListener;
    LinearLayout linearLayout;
    ImageView imageView;
    ListView listView;

    public NavTextView(Context context) {
        super(context);
        super.setOnClickListener(this);
        this.context = context;
        init(context);
    }

    public NavTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnClickListener(this);
        this.context = context;
        init(context);
    }

    public NavTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setOnClickListener(this);
        this.context = context;
        init(context);
    }

    public NavTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context)
    {
        super.setOnClickListener(this);
        this.context = context;
    }

    /**
     * This static function clears the NavTextViewList. Note: If you run this function
     * to clear the list afte the navigation drawer has been created it will mean that
     * selection state will not be cleared
     */
    public static void clearOutNavTextViewList()
    {
        NavigationManagerAdapter.navTextViewList.clear();
    }

    @Override
    public void setOnClickListener(OnClickListener l)
    {
        if (l != null) {
            wrappedOnClickListener = l;
        }
    }

    @Override
    public void onClick(View v) {
        setNavTextViewAsSelected();
        if (wrappedOnClickListener != null)
        {
            wrappedOnClickListener.onClick(this);
        }
    }

    /**
     * Finds the NavTextView item from the NavigationDrawer and returns this control.
     * @param tagName The tag name of the menu item - usually the second parameter of the NavigationMenuItem constructor
     * @return The NavTextView control
     * @throws NavItemNotFoundException
     */
    public static NavTextView getNavTextViewFromTag(String tagName) throws NavItemNotFoundException {
        for (int i = 0; i < NavigationManagerAdapter.navTextViewList.size(); i++)
        {
            if (NavigationManagerAdapter.navTextViewList.get(i).getTag().equals(tagName))
            {
                return NavigationManagerAdapter.navTextViewList.get(i);
            }
        }
        throw new NavItemNotFoundException(String.format("Navigation item tag name '%s' not found", tagName));
    }

    public void setNavTextViewAsSelected()
    {
        unselectEachMenuItem();
        LinearLayout linearLayout = null;
        if (this.getParent() instanceof LinearLayout)
        {
            linearLayout = (LinearLayout)this.getParent();
        }
        if (linearLayout != null)
        {
            int childrenCount = linearLayout.getChildCount();
            for (int i = 0; i < childrenCount; i++)
            {
                if (linearLayout.getChildAt(i) instanceof ImageView)
                {
                    imageView = (ImageView)linearLayout.getChildAt(i);
                }
            }
        }

        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.navSelectedItemBackground));

        if (imageView.getTag() instanceof GradientDrawable)
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.menu_icon);
            drawable.setColorFilter(context.getResources().getColor(R.color.primaryColor), PorterDuff.Mode.SRC_OVER);
            imageView.setImageDrawable(drawable);
        }


        this.setTextColor(context.getResources().getColor(R.color.primaryColor));
    }

    /**
     * This goes throw each NavTextView that has been created and gets the parent and
     * children elements in order to reset all of the navigation items are now
     * unselected
     */
    private void unselectEachMenuItem()
    {
        LinearLayout itemsLinearLayout = null;
        //Loop through all stored
        for (int i = 0; i < NavigationManagerAdapter.navTextViewList.size(); i++)
        {

            if ((NavigationManagerAdapter.navTextViewList.get(i).getParent() != null) && NavigationManagerAdapter.navTextViewList.get(i).getParent() instanceof LinearLayout) {
                //Get the linear layout from each Navigation Drawer menu item
                itemsLinearLayout = (LinearLayout) NavigationManagerAdapter.navTextViewList.get(i).getParent();
                itemsLinearLayout.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                for (int j = 0; j < itemsLinearLayout.getChildCount(); j++)
                {
                    //Get the children from each item list and check if the instance is an image
                    //view or a NavTextView and reset the colours to be unselected
                    if (itemsLinearLayout.getChildAt(j) instanceof ImageView)
                    {
                        ImageView itemsImageView = (ImageView)itemsLinearLayout.getChildAt(j);
                        if (itemsImageView.getTag() instanceof GradientDrawable) {
                            Drawable drawable = context.getResources().getDrawable(R.drawable.menu_icon);
                            drawable.setColorFilter(context.getResources().getColor(R.color.navImageIconColor), PorterDuff.Mode.SRC_OVER);
                            itemsImageView.setImageDrawable(drawable);
                        }
                    }

                    if (itemsLinearLayout.getChildAt(j) instanceof  NavTextView)
                    {
                        NavTextView itemsNavTextView = (NavTextView)itemsLinearLayout.getChildAt(j);
                        itemsNavTextView.setTextColor(context.getResources().getColor(R.color.navUnselectedColor));
                    }
                }
            }
        }
    }
}
