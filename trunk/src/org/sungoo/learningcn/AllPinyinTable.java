package org.sungoo.learningcn;

public class AllPinyinTable extends AbstractPinyinTable {
    private final String[] allPinyin;

    public AllPinyinTable() {
            this.allPinyin = new String[consonants.length + vowels.length];
            int ind = 0;
            for (int i = 0; i < vowels.length; i++) {
                    allPinyin[ind++] = vowels[i];
            }
            for (int i  = 0; i < consonants.length; i++) {
                    allPinyin[ind++] = consonants[i];
            }
    }


	public String getPinyinAt(int index) {
		index = getCircularIndex(index);
		return allPinyin[index];
	}

	public String[] getAllInOrder() {
            return allPinyin;
    }

    public int getAllSize() {
            return allPinyin.length;
    }
}
