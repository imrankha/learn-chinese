package org.sungoo.learningcn.hanzi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.sungoo.learningcn.hanzi.AbstractHanziTable;

import android.content.Context;


public class AllHanziTable extends AbstractHanziTable {
 	private static final String LABEL_FILE = "/hanzi.txt"; 
    private final List<String> allHanzi = new ArrayList<String>();
    private final Context mAppContext;
    
    public AllHanziTable(Context appContext) {
//    	SaveHanziToFile();
    	mAppContext = appContext;
    	readHanziFromFile();
    }

	public String getHanziFileName() {
		return mAppContext.getExternalFilesDir(null) + LABEL_FILE;
	}

	void readHanziFromFile() {
		try {
			String externalFileName = getHanziFileName();
			System.out.println("read file=" + externalFileName);
			FileInputStream fin = new FileInputStream(new File(externalFileName));
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new DataInputStream(fin)));
			String word;
			//Read File Line By Line
			while ((word = br.readLine()) != null)   {
				word = word.trim();
				if (!word.isEmpty()) {
					allHanzi.add(word);
					// Print the content on the console
					System.out.println (word);
				}
			}
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	void SaveHanziToFile() {
		try {
			String externalFileName = getHanziFileName();
			System.out.println("Write file=" + externalFileName);
			FileOutputStream fos = new FileOutputStream(new File(externalFileName));
			String [] labels = {" a", "b ",  "  "};
			for (String label : labels) {
				fos.write((label + "\n").getBytes());
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getHanziAt(int index) {
		index = getCircularIndex(index);
		return allHanzi.get(index);
	}

	public String[] getAllInOrder() {
            return allHanzi.toArray(null);
    }

    public int getAllSize() {
            return allHanzi.size();
    }
}
