package com.fykj.gpc.bmi;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceActivityTest extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
	
}
