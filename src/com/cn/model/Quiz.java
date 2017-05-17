package com.cn.model;

import java.util.ArrayList;

public class Quiz {
	private ArrayList<Question>questions;
	private int score=0;
	private int currentIndex=0;
	public Quiz() {
		questions = new ArrayList<Question>();
		questions.add(new Question("[3,1,4,1,5,?]","9"));
		questions.add(new Question("[1,1,2,3,5,?]","8"));
		questions.add(new Question("[1,4,9,16,25,?]","36"));
		questions.add(new Question("[2,3,5,7,11,?]","13"));
		questions.add(new Question("[1,2,4,8,16,?]","32"));
	}
	
	public String getCurrentQuestion() {
		return questions.get(currentIndex).getQuestion();
	}
	public String getCurrentQestionAnswer(){
		return questions.get(currentIndex).getAnswer();
	}

	public int getNumCorrect() {
		return this.score;
	}

	public boolean isCorrect(String answer) {
		return questions.get(currentIndex).getAnswer().equals(answer);
	}

	public void scoreAnswer() {
		this.score++;
		this.currentIndex++;
	}

	public int getNumQuestions() {
		return questions.size();
	}

	public int getCurrentQuestionIndex() {
		return this.currentIndex;
	}

}
