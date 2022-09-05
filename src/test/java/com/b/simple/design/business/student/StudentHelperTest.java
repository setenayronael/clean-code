package com.b.simple.design.business.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StudentHelperTest {

	StudentHelper helper = new StudentHelper();
	
	@Test
	void testIsGradeB() {
		assertFalse(helper.isGradeB(30, false));
		assertFalse(helper.isGradeB(50, false));
		assertTrue(helper.isGradeB(51, false));
		assertTrue(helper.isGradeB(80, false));
		assertFalse(helper.isGradeB(81, false));
		
		assertFalse(helper.isGradeB(30, true));
		assertFalse(helper.isGradeB(50, true));
		assertTrue(helper.isGradeB(51, true));
		assertTrue(helper.isGradeB(80, true));
		assertTrue(helper.isGradeB(81, true));
		assertTrue(helper.isGradeB(89, true));
		assertTrue(helper.isGradeB(90, true));
		assertFalse(helper.isGradeB(91, true));

	}

	@Test
	void testGetGrade() {

		assertEquals(Grade.A,helper.getGrade(99, false));
		assertEquals(Grade.A,helper.getGrade(91, false));
		assertEquals(Grade.B,helper.getGrade(85, false));
		assertEquals(Grade.B,helper.getGrade(51, false));
		assertEquals(Grade.C,helper.getGrade(50, false));
		assertEquals(Grade.C,helper.getGrade(45, false));

		assertEquals(Grade.A,helper.getGrade(99, true));
		assertEquals(Grade.A,helper.getGrade(96, true));
		assertEquals(Grade.B,helper.getGrade(89, true));
		assertEquals(Grade.B,helper.getGrade(56, true));
		assertEquals(Grade.C,helper.getGrade(55, true));
		assertEquals(Grade.C,helper.getGrade(50, true));
		assertEquals(Grade.C,helper.getGrade(45, true));

		assertEquals(Grade.B,helper.getGrade(95, true));
		assertEquals(Grade.B,helper.getGrade(90, false));
	}

	@Test
	void testWillQualifyForQuiz() {
		assertEquals("NO",helper.willQualifyForQuiz(15, 25, false));
		assertEquals("NO",helper.willQualifyForQuiz(20, 20, false));
		assertEquals("MAYBE",helper.willQualifyForQuiz(21, 21, false));
		assertEquals("MAYBE",helper.willQualifyForQuiz(25, 25, false));
		assertEquals("MAYBE",helper.willQualifyForQuiz(79, 79, false));
		assertEquals("YES",helper.willQualifyForQuiz(80, 30, false));
		assertEquals("YES",helper.willQualifyForQuiz(85, 30, false));
		assertEquals("YES",helper.willQualifyForQuiz(30, 90, false));
		
		assertEquals("NO",helper.willQualifyForQuiz(15, 25, true));
		assertEquals("NO",helper.willQualifyForQuiz(20, 20, true));
		assertEquals("NO",helper.willQualifyForQuiz(21, 21, true));
		assertEquals("NO",helper.willQualifyForQuiz(25, 25, true));
		assertEquals("MAYBE",helper.willQualifyForQuiz(27, 34, true));
		assertEquals("MAYBE",helper.willQualifyForQuiz(79, 79, true));
		assertEquals("MAYBE",helper.willQualifyForQuiz(80, 30, true));
		assertEquals("MAYBE",helper.willQualifyForQuiz(80, 84, true));
		assertEquals("YES",helper.willQualifyForQuiz(85, 30, true));
		assertEquals("YES",helper.willQualifyForQuiz(30, 90, true));

	}

}
