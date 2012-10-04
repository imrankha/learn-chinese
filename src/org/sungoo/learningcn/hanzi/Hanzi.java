package org.sungoo.learningcn.hanzi;

import java.io.IOException;

import org.sungoo.learningcn.R;
import org.sungoo.learningcn.R.id;
import org.sungoo.learningcn.R.layout;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ViewSwitcher;

public class Hanzi extends Activity implements ViewSwitcher.ViewFactory,
	View.OnClickListener, OnTouchListener {
	
	private TextSwitcher mSwitcher;
	private int mIndex = 0;
	private Animation in, out;
	private HanziTable mHanziTable;
	private TextView mIndexText;
	private EditText mEditText;
	private float downXValue;
	private boolean mWordMatched = false;
	
    private MediaPlayer mp;
    private AssetManager am;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = new MediaPlayer();
        am = getResources().getAssets();
        
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
        Button goButton = (Button) findViewById(R.id.check_word);
        goButton.setOnClickListener(this);
        
        // Add Touch listener for hanzi layout.
        RelativeLayout hanziLayout = (RelativeLayout) findViewById(R.id.hanzi_layout);
        hanziLayout.setOnTouchListener(this);
        mHanziTable = new AllHanziTable(this.getApplicationContext());
        
        mIndexText = (TextView) findViewById(R.id.hanzi_index);
        updateWord(false);
        createEditor();
    }
 
    private void createEditor() {
    	mEditText = (EditText) findViewById(R.id.hanzi_input);
    	mEditText.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
			    System.out.print("actionId=" +actionId + ", event=" + event.getAction());
        	    
				String word;
				if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.KEYCODE_ENTER) {
    	        	word = v.getText().toString();
        	        System.out.print("word=" + word);
        	        String hanzi = mHanziTable.getHanziAt(mIndex);
        	        if (hanzi.equals(word))
        	        	mWordMatched = true;
        	        return true;
    	        }
				
				return false;
    	    }
    	});
    }
    
    private void updateWord(boolean playSound) {
    	if (mWordMatched) {
        	mSwitcher.setText("");
        	if (playSound)
        		playSound("notification/success.mp3");
    	} else {
    		mSwitcher.setText(mHanziTable.getHanziAt(mIndex));
        	if (playSound)
        		playSound("notification/fail.mp3");
    	}
    	mIndexText.setText(Integer.toString(mIndex + 1) + "/" + Integer.toString(mHanziTable.getAllSize()));
	}

    private void playSound(String filename) {
    	AssetFileDescriptor afd;
		try {
			afd = am.openFd(filename);
	    	afd.toString();
	    	afd.getLength();
	    	mp.reset();
	    	mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
	    	mp.prepare();
			mp.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    	case R.id.check_word:
    		String word = mEditText.getText().toString();
	        String hanzi = mHanziTable.getHanziAt(mIndex);
	        System.out.println("word=" + word);
	        System.out.println("hanzi=" + hanzi);
	        if (hanzi.equals(word))
	        	mWordMatched = true;
	        else
	        	mWordMatched = false;
	        updateWord(true);
	        break;
	     default:
	    	 break;
    	}		
    }

	public View makeView() {
		TextView t = new TextView(this);
		t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
		t.setTextColor(Color.WHITE);
		t.setTextSize(50) ;
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
		mWordMatched = false;
		mIndex -= 1;	
		mIndex = ((AbstractHanziTable) mHanziTable).getCircularIndex(mIndex);
        in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
		updateWord(false);
	}

	private void showNext() {
		mWordMatched = false;
		mIndex += 1;
		mIndex = ((AbstractHanziTable) mHanziTable).getCircularIndex(mIndex);
		in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
		updateWord(false);
	}
}
