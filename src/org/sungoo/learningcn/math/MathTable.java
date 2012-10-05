package org.sungoo.learningcn.math;

import java.util.LinkedList;
import java.util.List;

import org.sungoo.learningcn.hanzi.HanziTable;

public class MathTable {
	public interface Question {
		int a();
		int b();
		int calc();
		String answer();
	}
	
	public class Addition implements Question {
		public int arg1;
		public int arg2;

		public int a() { return arg1; }
		public int b() { return arg2; }
		public int calc() { return a() + b();}
		public String answer() { return "" + calc(); }
		public String toString() { return "" + arg1 + "+" + arg2 + "=?"; } 
	}
	
	private List<Question> allQuestions = new LinkedList<Question>();
	private final int NUM_QUESTIONS = 10;
	private final int NUMBER_RANGE = 10;
	public MathTable() {
		for (int i=0; i<NUM_QUESTIONS; i++) {
			Addition q = new Addition();
			q.arg1 = (int) (NUMBER_RANGE * Math.random());
			q.arg2 = (int) (NUMBER_RANGE * Math.random());
			allQuestions.add(q);
		}
	}
	
	public Question getQuestionAt(int index) {
		index = getCircularIndex(index);
		return allQuestions.get(index);
	}

	public Question[] getAllInOrder() {
            return allQuestions.toArray(null);
    }

    public int getAllSize() {
    	return allQuestions.size();
    }
    
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
