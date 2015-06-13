package com.example.android.effectivenavigation;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	static Context ctx;
	TabPagerAdapter tabPagerAdapter;
	ViewPager viewpager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tabs);
		ctx = this;
	
		tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
		
		final ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		viewpager = (ViewPager)findViewById(R.id.pager);
		viewpager.setAdapter(tabPagerAdapter);
		
		viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		actionBar.addTab(actionBar.newTab()
				.setText("MainTab")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab()
				.setText("Tab1")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab()
				.setText("Tab2")
				.setTabListener(this));
		
//		// For each of the sections in the app, add a tab to the action bar.
//        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
//            // Create a tab with text corresponding to the page title defined by the adapter.
//            // Also specify this Activity object, which implements the TabListener interface, as the
//            // listener for when this tab is selected.
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
//                            .setTabListener(this));
//        }
		
	}

	@Override
	public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
		
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction arg1) {
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public static class TabPagerAdapter extends FragmentPagerAdapter {

	        public TabPagerAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int i) {
	        	Fragment fragment = null;
	        	
	            switch (i) {
	                case 0:
	                    // The first section of the app is the most interesting -- it offers
	                    // a launchpad into the other demonstrations in this example application.
	                    return new LaunchedFragement();
				case 1:
					fragment = new PagerFragement1();
					return fragment;
				case 2:
					fragment = new PagerFragement2();
					return fragment;
                default:
                    // The other sections of the app are dummy placeholders.
//                    fragment = new DummySectionFragment();
//                    Bundle args = new Bundle();
//                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
//                    fragment.setArguments(args);
//                    return fragment;
                	   return new LaunchedFragement();
	            }
				
	        }

	        @Override
	        public int getCount() {
	            return 3;
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return "Section " + (position + 1);
	        }
	    }
	
	public static class LaunchedFragement extends Fragment
	{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			
			View view = inflater.inflate(R.layout.fragment_section_launchpad, container,false);
		
			view.findViewById(R.id.demo_collection_button).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					 Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
                     startActivity(intent);
				}
			});
			
			view.findViewById(R.id.demo_external_activity)
	            .setOnClickListener(new View.OnClickListener() {
	                @Override
	                public void onClick(View view) {
	                   
	                	 Intent intent = new Intent(getActivity(), SimpleTab.class);
	                     startActivity(intent);
	                }
	            });
			return view;
		}
	}
	
	public static class PagerFragement1 extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_section_dummy_one, container,false);
			((TextView)view.findViewById(R.id.fragementtext1)).setText("This is Tab1");
			return view;
		}
	}
	
	public static class PagerFragement2 extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_section_dummy_two, container,false);
			((TextView)view.findViewById(R.id.fragementtext2)).setText("This is Tab2");
			return view;
		}
	}
	
	public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy_one, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(R.id.fragementtext1)).setText("Working" + args.getInt(ARG_SECTION_NUMBER));
            return rootView;
        }
    }
}
