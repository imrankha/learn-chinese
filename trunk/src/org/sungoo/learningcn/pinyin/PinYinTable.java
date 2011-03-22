package org.sungoo.learningcn.pinyin;

public interface PinYinTable {
	// Get pinyin with a index
	public String getPinyinAt(int index);
	
	// Get all pinyin
	public String[] getAllInOrder();

	// Get pinyin list size
	public int getAllSize();
}
