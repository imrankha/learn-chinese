package org.sungoo.learningcn;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Hanzi extends Activity implements ViewSwitcher.ViewFactory,
	View.OnClickListener {
	
	private TextSwitcher mSwitcher;
	private char mWord = 'a';
	private Animation in, out;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hanzi);
        
        mSwitcher = (TextSwitcher) findViewById(R.id.hanzi_switcher);
        mSwitcher.setFactory(this);
        
        in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        
        Button prevButton = (Button) findViewById(R.id.previous);
        prevButton.setOnClickListener(this);
        Button nextButton = (Button) findViewById(R.id.next);
        nextButton.setOnClickListener(this);
        
        updateWord();
    }
    
    private void updateWord() {
    	mSwitcher.setText(Character.toString(mWord));
		
	}

	public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.previous:
    		if (mWord > 'a') {
    			mWord -= 1;	
    			updateWord();
    		}
    		break;
    	case R.id.next:
    		if (mWord < 'z') {
    			mWord += 1;
    			updateWord();
    		}
    		break;
    	}		
    }

	public View makeView() {
		TextView t = new TextView(this);
		t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
		t.setTextSize(96) ;
		return t;
	}
}
