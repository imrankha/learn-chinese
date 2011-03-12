package org.sungoo.learningcn;

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
