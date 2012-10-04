package org.sungoo.learningcn.hanzi;

import org.sungoo.learningcn.hanzi.HanziTable;

public abstract class AbstractHanziTable implements HanziTable {
    protected static final String[] hanzi = {
            "大为",
            "小云",
            "云",
            "白",
            "山",
            "风",
            "口",
            "人",
            "水",
            "火",
    };
    public abstract String[] getAllInOrder();
    public abstract int getAllSize();
    
    // Avoid out of bound array index
    protected int getCircularIndex(int index) {
    	final int SIZE = getAllSize();
    	if (index < 0 || index >= SIZE) {
    		return (index + SIZE) % SIZE;
    	} else {
    		return index;
    	}
    }
}
