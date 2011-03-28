package org.sungoo.learningcn.hanzi;

import org.sungoo.learningcn.hanzi.HanziTable;

public abstract class AbstractHanziTable implements HanziTable {
    protected static final String[] hanzi = {
            "的",
            "一",
            "他",
            "我",
            "是",
            "了",
            "不",
            "在",
            "这",
            "人",
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
