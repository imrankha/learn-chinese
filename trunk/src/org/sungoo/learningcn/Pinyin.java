package org.sungoo.learningcn;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
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
import android.widget.ViewSwitcher;

public class Pinyin extends Activity implements ViewSwitcher.ViewFactory,
	View.OnClickListener, OnTouchListener {
	
	private TextSwitcher mSwitcher;
	private int mIndex = 0;
	private Animation in, out;
	private PinYinTable mPinyinTable;
	private TextView mIndexText;
	private float downXValue;

    private MediaPlayer mp;
    private AssetManager am;
        
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
        
        mIndexText = (TextView) findViewById(R.id.pinyin_index);
        mp = new MediaPlayer();
        am = getResources().getAssets();
        
        try {
			updateWord();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private void updateWord() throws IllegalArgumentException, IllegalStateException, IOException {
    	String curr = mPinyinTable.getPinyinAt(mIndex);
    	mSwitcher.setText(curr);
    	mIndexText.setText(Integer.toString(mIndex + 1) + "/" + Integer.toString(mPinyinTable.getAllSize()));
    	
    	String fn = getFileName(curr);
    	AssetFileDescriptor afd = am.openFd(fn + ".mp3");
    	afd.toString();
    	afd.getLength();
    	mp.reset();
    	mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
    	mp.prepare();
		mp.start();
	}

	private String getFileName(String curr) {
		if (curr == "ü") {
			return "v";
		} else if(curr == "ün") {
			return "vn";
		} else {
			return curr;
		}
	}

	public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.previous_py:
    		try {
				showPrevious();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		break;
    	case R.id.next_py:
    		try {
				showNext();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				try {
					showPrevious();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (downXValue > currentX) {
				try {
					showNext();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		
		return true;
	}

	private void showPrevious() throws IllegalArgumentException, IllegalStateException, IOException {
        in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
		if (mIndex > 0) {
			mIndex -= 1;	
		} else {
			mIndex = mPinyinTable.getAllSize() - 1;
		}
		updateWord();
	}

	private void showNext() throws IllegalArgumentException, IllegalStateException, IOException {
        in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
		if (mIndex < mPinyinTable.getAllSize() - 1) {
			mIndex += 1;
		} else {
			mIndex = 0;
		}
		updateWord();
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
	    
	    try {
			updateWord();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
