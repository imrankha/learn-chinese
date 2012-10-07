package org.sungoo.learningcn.hanzi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.sungoo.learningcn.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;

public class HanziEditor extends Activity implements View.OnClickListener {
	private EditText mEditText;
	private String mHanziFile;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hanzi_editor);
		mEditText = (EditText) findViewById(R.id.hanzi_editor_input);
		Intent data = getIntent();
		mHanziFile = data.getDataString();
		String text = readHanziFile();
		if (!text.isEmpty()) {
			mEditText.setText(text);
		} else {
			mEditText.setHint("One word a line");
		}
    }
    
    String readHanziFile() {
		System.out.println("read file:" + mHanziFile);
		try {
			FileInputStream fin = new FileInputStream(new File(mHanziFile));
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new DataInputStream(fin)));
			String line;
	        StringBuilder text = new StringBuilder();
	        while (( line = br.readLine()) != null) {
	        	text.append(line);
	        	text.append('\n');
	        }
	        return text.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
    }

	void saveHanziFile() {
		try {
			System.out.println("Write file=" + mHanziFile);
			FileOutputStream fos = new FileOutputStream(new File(mHanziFile));
			fos.write(mEditText.getText().toString().getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	// Create the menu
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hanzi_editor_menu, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	boolean status = false;
    	// Handle item selection
	    switch (item.getItemId()) {
	    case R.id.hanzi_editor_save:
	    	saveHanziFile();
	    	status = true;
	        break;
	     default:
	    	 status = true;
	    	 break;
	    }
	    return status;
	}

};
