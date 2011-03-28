package org.sungoo.learningcn.hanzi;

import org.sungoo.learningcn.R;
import org.sungoo.learningcn.R.id;
import org.sungoo.learningcn.R.layout;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Hanzi extends Activity implements ViewSwitcher.ViewFactory,
	View.OnClickListener, OnTouchListener {
	
	private TextSwitcher mSwitcher;
	private int mIndex = 0;
	private Animation in, out;
	private HanziTable mHanziTable;
	private TextView mIndexText;
	private float downXValue;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hanzi);
        mSwitcher = (TextSwitcher) findViewById(R.id.hanzi_switcher);
        mSwitcher.setFactory(this);
        
        // Add Click listener for buttons.
        Button prevButton = (Button) findViewById(R.id.hanzi_previous_py);
        prevButton.setOnClickListener(this);
        Button nextButton = (Button) findViewById(R.id.hanzi_next_py);
        nextButton.setOnClickListener(this);
        Button firstButton = (Button) findViewById(R.id.hanzi_first_py);
        firstButton.setOnClickListener(this);
        Button lastButton = (Button) findViewById(R.id.hanzi_last_py);
        lastButton.setOnClickListener(this);
        
        // Add Touch listener for hanzi layout.
        RelativeLayout hanziLayout = (RelativeLayout) findViewById(R.id.hanzi_layout);
        hanziLayout.setOnTouchListener(this);
        mHanziTable = new AllHanziTable();
        
        mIndexText = (TextView) findViewById(R.id.hanzi_index);
        updateWord();
    }
 
    private void updateWord() {
    	mSwitcher.setText(mHanziTable.getHanziAt(mIndex));
    	mIndexText.setText(Integer.toString(mIndex + 1) + "/" + Integer.toString(mHanziTable.getAllSize()));
	}


	public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.hanzi_previous_py:
    		showPrevious();
    		break;
    	case R.id.hanzi_next_py:
    		showNext();
    		break;
    	case R.id.hanzi_first_py:
    		mIndex = 1;
    		showPrevious();
    		break;
    	case R.id.hanzi_last_py:
    		mIndex = mHanziTable.getAllSize() - 2;
    		showNext();
    		break;
    	}		
    }

	public View makeView() {
		TextView t = new TextView(this);
		t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
		t.setTextColor(Color.WHITE);
		t.setTextSize(150) ;
		t.setShadowLayer(1.2f, 1.2f, 1.2f, Color.BLUE);
		return t;
	}

	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downXValue = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			float currentX = event.getX();
			if (downXValue < currentX) {
				showPrevious();
			} else if (downXValue > currentX) {
				showNext();
			}
			break;
		}
		
		return true;
	}

	private void showPrevious() {
		mIndex -= 1;	
		mIndex = ((AbstractHanziTable) mHanziTable).getCircularIndex(mIndex);
        in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
		updateWord();
	}

	private void showNext() {
		mIndex += 1;
		mIndex = ((AbstractHanziTable) mHanziTable).getCircularIndex(mIndex);
		in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
		updateWord();
	}
}
