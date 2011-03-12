package org.sungoo.learningcn;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Pinyin extends Activity implements ViewSwitcher.ViewFactory,
	View.OnClickListener, OnTouchListener {
	
	private TextSwitcher mSwitcher;
	private int mIndex = 0;
	private Animation in, out;
	private PinYinTable mPinyinTable;
	private TextView mIndexText;
	private Boolean mToastShown = false;
	private float downXValue;

    private MediaPlayer song1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinyin);
        mSwitcher = (TextSwitcher) findViewById(R.id.pinyin_switcher);
        mSwitcher.setFactory(this);
        
        // Add Click listener for buttons.
        Button prevButton = (Button) findViewById(R.id.previous_py);
        prevButton.setOnClickListener(this);
        Button nextButton = (Button) findViewById(R.id.next_py);
        nextButton.setOnClickListener(this);
        
        // Add Touch listener for Pinyin layout.
        RelativeLayout pinyinLayout = (RelativeLayout) findViewById(R.id.pinyin_layout);
        pinyinLayout.setOnTouchListener(this);
        mPinyinTable = new AllPinyinTable();

        song1 = MediaPlayer.create(this, R.raw.jay);
        
        mIndexText = (TextView) findViewById(R.id.pinyin_index);
        updateWord();
    }
    
    private void updateWord() {
    	mSwitcher.setText(mPinyinTable.getPinyinAt(mIndex));
    	mIndexText.setText(Integer.toString(mIndex + 1) + "/" + Integer.toString(mPinyinTable.getAllSize()));
		//song1.seekTo(0);
		//song1.start();
	}

	public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.previous_py:
    		showPrevious();
    		break;
    	case R.id.next_py:
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
		if (mIndex > 0) {
			mToastShown = false;
			mIndex -= 1;	
	        in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
	        out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
	        mSwitcher.setInAnimation(in);
	        mSwitcher.setOutAnimation(out);
			updateWord();
		} else {
			if (!mToastShown) {
				Toast.makeText(Pinyin.this, "1st", Toast.LENGTH_SHORT).show();
				mToastShown = true;
			}
		}
	}

	private void showNext() {
		if (mIndex < mPinyinTable.getAllSize() - 1) {
			mToastShown = false;
			mIndex += 1;
	        in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
	        out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
	        mSwitcher.setInAnimation(in);
	        mSwitcher.setOutAnimation(out);
			updateWord();
		} else {
			if (!mToastShown) {
				Toast.makeText(Pinyin.this, "last", Toast.LENGTH_SHORT).show();
				mToastShown = true;
			}
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	mIndex = 0;	        

    	boolean status = false;
    	// Handle item selection
	    switch (item.getItemId()) {
	    case R.id.vowels:
	    	mPinyinTable = new VowelsTable();
	        status = true;
	        break;
	    case R.id.consonants:
	    	mPinyinTable = new ConsonantsTable();
	        status = true;
	        break;
	    case R.id.all_pinyin:
	    	mPinyinTable = new AllPinyinTable();
	        status = true;
	        break;
	    default:
	        status = super.onOptionsItemSelected(item);
	    }
	    
	    updateWord();
	    return status;
	}
	
	// Create the menu
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pinyin_menu, menu);
        return true;
    }
}
