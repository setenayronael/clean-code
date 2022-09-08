package com.b.simple.design.business.student;

public class StudentHelper {

    private static final int P1_GRADE_B_LOWER_LIMIT = 51;
    private static final int P1_GRADE_B_UPPER_LIMIT = 80;
    private static final int P1_EXTRA_LIMIT_FOR_MATH = 10;
    private static final int P2_EXTRA_LIMIT_FOR_MATH = 5;
    public static final String NOT_QUALIFY = "NO";
    public static final String QUALIFY = "YES";
    public static final String UNKNOWN_QUALIFY = "MAYBE";
    private static final int P3_EXTRA_LIMIT_FOR_MATH = 5;
    public static final int P3_FAIL_LIMIT = 20;
    public static final int P3_SUCCESS_LIMIT = 80;

    /* PROBLEM 1 */
    /*
     * You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
     */
    public boolean isGradeB(int marks, boolean isMaths) {
        int extraLimit = isMaths ? P1_EXTRA_LIMIT_FOR_MATH : 0;
        int gradeBUpperLimit = P1_GRADE_B_UPPER_LIMIT + extraLimit;
        return marks >= P1_GRADE_B_LOWER_LIMIT && marks <= gradeBUpperLimit;
    }

    /* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	*/

    public Grade getGrade(int mark, boolean isMaths) {

        int extraLimit = isMaths ? P2_EXTRA_LIMIT_FOR_MATH : 0;

        int lowerLimitForAGrade = Grade.A.getLowerLimit() + extraLimit;
        if (mark >= lowerLimitForAGrade)
            return Grade.A;

        int lowerLimitGradeB = Grade.B.getLowerLimit() + extraLimit;
        if (mark >= lowerLimitGradeB) {
            return Grade.B;
        }
        return Grade.C;
    }

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     *
     * Return value can be YES, NO or MAYBE.
     *
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     *
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     *
     * marks1 - your marks
     * marks2 - your friends marks
     */
    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
        int extraLimit = isMaths ? P3_EXTRA_LIMIT_FOR_MATH : 0;

        if (isNotGood(marks1, marks2, extraLimit)) return NOT_QUALIFY;

        if (isGood(marks1, marks2, extraLimit)) return QUALIFY;

        return UNKNOWN_QUALIFY;
    }

    private boolean isNotGood(int marks1, int marks2, int extraLimit) {
        int failLimit = P3_FAIL_LIMIT + extraLimit;
        return marks1 <= failLimit || marks2 <= failLimit;
    }

    private boolean isGood(int marks1, int marks2, int extraLimit) {
        int successLimit = P3_SUCCESS_LIMIT + extraLimit;
        return marks1 >= successLimit || marks2 >= successLimit;
    }
}