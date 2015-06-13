package com.example.android.effectivenavigation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SimpleTab extends Activity {

	TabHost th;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_tab);
		th = (TabHost)findViewById(R.id.tabhost);
		th.setup();
		
		TabSpec tabSpec = th.newTabSpec("tab1");
				tabSpec.setContent(R.id.tab1);
				tabSpec.setIndicator("Tab1");
				th.addTab(tabSpec);
				
				tabSpec = th.newTabSpec("tab2");
					tabSpec.setContent(R.id.tab2);
					tabSpec.setIndicator("Tab2");
					th.addTab(tabSpec);
				
				tabSpec = th.newTabSpec("tab3");
					tabSpec.setContent(R.id.tab3);
					tabSpec.setIndicator("Tab3");
					th.addTab(tabSpec);
					
		th.setCurrentTabByTag("tab1");
	}

}
