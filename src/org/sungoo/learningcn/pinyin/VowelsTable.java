package org.sungoo.learningcn.pinyin;


public class VowelsTable extends AbstractPinyinTable {
	public String getPinyinAt(int index) {
		index = getCircularIndex(index);
		return vowels[index];
	}

    public String[] getAllInOrder() {
            return vowels;
    }

    public int getAllSize() {
            return vowels.length;
    }
}
