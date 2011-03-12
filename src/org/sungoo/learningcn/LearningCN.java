package org.sungoo.learningcn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class LearningCN extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // click listeners
        View pinyinButton = this.findViewById(R.id.pinyin);
        pinyinButton.setOnClickListener(this);
        View hanziButton = this.findViewById(R.id.hanzi);
        hanziButton.setOnClickListener(this);   
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.pinyin:
    		Intent i = new Intent(this, Pinyin.class);
    		startActivity(i);
    		break;
    	case R.id.hanzi:
    		Intent i2 = new Intent(this, Hanzi.class);
    		startActivity(i2);
    		break;    	
    	}
    }
}