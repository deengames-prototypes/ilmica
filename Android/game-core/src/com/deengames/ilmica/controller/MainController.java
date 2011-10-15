package com.deengames.ilmica.controller;

import java.util.ArrayList;
import java.util.Random;

import com.deengames.ilmica.model.DataHelper;
import com.deengames.ilmica.model.Question;

public class MainController {

	private final static int DEFAULT_NUMBER_OF_QUESTIONS = 10;
	
	public static Question[] makeQuizForSet(String setName) {
		return makeQuizForSet(setName, DEFAULT_NUMBER_OF_QUESTIONS);
	}
	
	private static Question[] makeQuizForSet(String setName, int numQuestions) {
		ArrayList<Question> toReturn = new ArrayList<Question>();
		
		ArrayList<Question> allQuestions = DataHelper.getQuestionsForSet(setName);		
		// Pick questions based on difficulty? ... nah ...
		if (allQuestions.size() < numQuestions) {
			// throw new Exception("Asked for " + numQuestions + " questions, but we only have " + allQuestions.size());			// 
		} else if (allQuestions.size() == numQuestions) {
			return allQuestions.toArray(new Question[0]);
		} else {
			// Pick random questions
			Random rGen = new Random();
			while (toReturn.size() < numQuestions) {
				int nextQuestionIndex = rGen.nextInt(allQuestions.size());
				Question nextQuestion = allQuestions.get(nextQuestionIndex); 
				toReturn.add(nextQuestion);
				// Prevent dupes
				allQuestions.remove(nextQuestionIndex);
				nextQuestion.randomizeAnswerOrder();
			}			
		}
		
		return toReturn.toArray(new Question[0]);
	}
	
}
