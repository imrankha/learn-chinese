package org.sungoo.learningcn.hanzi;

public interface HanziTable {
	// Get hanzi with a index
	public String getHanziAt(int index);
	
	// Get all hanzi
	public String[] getAllInOrder();

	// Get hanzi list size
	public int getAllSize();
}
