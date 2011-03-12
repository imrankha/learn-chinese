package org.sungoo.learningcn;

public class ConsonantsTable extends AbstractPinyinTable {

	public String getPinyinAt(int index) {
		index = getCircularIndex(index);
		return consonants[index];
	}

	public String[] getAllInOrder() {
            return consonants;
    }

    public int getAllSize() {
            return consonants.length;
    }
}
