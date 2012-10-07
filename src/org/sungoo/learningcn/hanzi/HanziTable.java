package org.sungoo.learningcn.hanzi;

public interface HanziTable  {
//    // Avoid out of bound array index
//    public int getCircularIndex(int index) {
//    	final int SIZE = getAllSize();
//    	if (index < 0 || index >= SIZE) {
//    		return (index + SIZE) % SIZE;
//    	} else {
//    		return index;
//    	}
//    }

	// Get hanzi with a index
	public String getHanziAt(int index);
//	{
//		index = getCircularIndex(index);
//		return hanziList[index];
//	}
	
	// Get all hanzi
	public String[] getAllInOrder();
	
//	{
//		return hanziList;
//	}

	// Get the hanzi list size
	public int getAllSize();
	
//	{
//		return hanziList.length;
//	}
	
	// Return the name of the file that stores the Hanzi.
	public String getHanziFileName();
}
