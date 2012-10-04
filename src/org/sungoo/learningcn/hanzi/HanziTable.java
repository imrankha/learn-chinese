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
	
//    protected static final String[] hanziList = {
//    	"清","江","法","汇","洽","浮","汉","潼","洞","海","洋","油","游","滥","濑","濶","漷","淋","泠","泷","流","泪","滴","沙","漠","泪","渐","涨","潮","湿","浪","汗","深","洁","洗","澡","涌","泳","泽","冰","没","沫","泌","激","浮","洼","涡","汽","漆","泣","汔","液"};
}
