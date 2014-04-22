package com.example.geoquiz;

public class trueFalse {
	
	private int mQuestion;
	private boolean mTrueQuestion;
	
	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}

	public trueFalse(int question, boolean trueQuestion){
		mQuestion = question;
		mTrueQuestion = trueQuestion;
		
	}

}
