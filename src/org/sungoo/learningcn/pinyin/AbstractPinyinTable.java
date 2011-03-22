package org.sungoo.learningcn.pinyin;


public abstract class AbstractPinyinTable implements PinYinTable {
        protected static final String[] vowels = {
                "a",
                "o",
                "e",
                "i",
                "u",
                "Ÿ",
                "ai",
                "ei",
                "ui",
                "ao",
                "ou",
                "iu",
                "ie",
                "Ÿe",
                "er",
                "an",
                "en",
                "in",
                "un",
                "Ÿn",
                "ang",
                "eng",
                "ing",
                "ong",
        };
        protected static final String[]  consonants= {
            "b",
            "p",
            "m",
            "f",
            "d",
            "t",
            "n",
            "l",
            "g",
            "k",
            "h",
            "j",
            "q",
            "x",
            "zh",
            "ch",
            "sh",
            "r",
            "z",
            "c",
            "s",
            "y",
            "w",
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
