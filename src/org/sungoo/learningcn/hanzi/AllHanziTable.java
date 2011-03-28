package org.sungoo.learningcn.hanzi;

import org.sungoo.learningcn.hanzi.AbstractHanziTable;


public class AllHanziTable extends AbstractHanziTable {
    private final String[] allHanzi;

    public AllHanziTable() {
            this.allHanzi = hanzi;
    }


	public String getHanziAt(int index) {
		index = getCircularIndex(index);
		return allHanzi[index];
	}

	public String[] getAllInOrder() {
            return allHanzi;
    }

    public int getAllSize() {
            return allHanzi.length;
    }
}
