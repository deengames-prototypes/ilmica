package com.deengames.ilmica.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.deengames.ilmica.GdxTestRunner;
import com.deengames.ilmica.model.Question;

@RunWith(GdxTestRunner.class)
public class MainControllerTest {

	private final String REAL_SET_NAME = "Fundamentals of Tawheed 1.0";
	
	@Test
	public void makeQuizForSetReturnsARandomSetOfQuestions() {
		Question[] set1 = MainController.makeQuizForSet(REAL_SET_NAME);
		Question[] set2 = MainController.makeQuizForSet(REAL_SET_NAME);
		
		assertEquals(10, set1.length);
		
		assertEquals(set1.length, set2.length);
		boolean foundDifference = false;
		
		// Check that any of the ten questions are different
		for (int i = 0; i < set1.length; i++) {
			if (!set1[i].getText().equals(set2[i].getText())) {
				foundDifference = true;
				break;
			}
		}
		
		assertTrue(foundDifference);
	}
	
	@Test
	public void makeQuizForSetReturnsCorrectQuestionsOnly() {
		Question[] arithmeticQuestions = MainController.makeQuizForSet("Arithmetic Questions");
	
		for (Question q : arithmeticQuestions) {
			assertTrue(q.getText().contains("+"));
			assertTrue(q.getText().contains("="));
		}
	}
	
}
