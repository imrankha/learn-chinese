package org.sungoo.learningcn;

public class PinYinTable {
	// Initials
//	public static final String INIT_a = "a";
//	public static final String INIT_o = "o";
//	public static final String INIT_e = "e";
//	public static final String INIT_i = "i";
//	public static final String INIT_u = "u";
//	public static final String INIT_v = "";
//	public static final String INIT_ai = "ai";
//	public static final String INIT_ei = "ei";
//	public static final String INIT_ui = "ui";
//	public static final String INIT_ao = "ao";
//	public static final String INIT_ou = "ou";
//	public static final String INIT_iu = "iu";
//	public static final String INIT_ie = "ie";
//	public static final String INIT_ve = "e";
//	public static final String INIT_er = "er";
//	public static final String INIT_an = "an";
//	public static final String INIT_en = "en";
//	public static final String INIT_in = "in";
//	public static final String INIT_un = "un";
//	public static final String INIT_vn = "n";
//	public static final String INIT_ang = "ang";
//	public static final String INIT_eng = "eng";
//	public static final String INIT_ing = "ing";
//	public static final String INIT_ong = "ong";
	private static final String[] finals = {
		"a",
		"o",	
		"e",
		"i",
		"u",
		"",
		"ai",
		"ei",
		"ui",
		"ao",
		"ou",
		"iu",
		"ie",
		"e",
		"er",
		"an",
		"en",
		"in",
		"un",
		"n",
		"ang",
		"eng",
		"ing",
		"ong",
	};

	public static final String[]  initials= {
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
	
	public String[] getInitials() {
		return initials;
	}
	
	public String[] getFinals() {
		return finals;
	}
	
	public String[] getAllInOrder() {
		String[] all = new String[initials.length + finals.length];
		int ind = 0;
		for (int i = 0; i < 6 && i < finals.length; i++) {
			all[ind++] = finals[i];
		}
		for (int i  = 0; i < initials.length; i++) {
			all[ind++] = initials[i];
		}
		for (int i = 6; i < finals.length; i++) {
			all[ind++] = finals[i];
		}
		return all;
	}
	
	public int getAllSize() {
		return initials.length + finals.length;
	}
}
