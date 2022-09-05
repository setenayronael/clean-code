package com.b.simple.design.business.student;

public class StudentHelper {

    private static final int P1_GRADE_B_LOWER_LIMIT = 51;
    private static final int P1_GRADE_B_UPPER_LIMIT = 80;
    private static final int P1_GRADE_B_UPPER_LIMIT_EXTRA_FOR_MATH = 10;
    private static final int P2_GRADE_EXTRA_LIMIT_FOR_MATH = 5;

    /* PROBLEM 1 */
    /*
     * You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
     */
    public boolean isGradeB(int marks, boolean isMaths) {
        int extraLimit = isMaths ? P1_GRADE_B_UPPER_LIMIT_EXTRA_FOR_MATH : 0;
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

        int extraLimit = isMaths ? P2_GRADE_EXTRA_LIMIT_FOR_MATH : 0;

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
        if ((isMaths ? marks1 <= 25 : marks1 <= 20)
                || (isMaths ? marks2 <= 25 : marks2 <= 20)) return "NO";
        if ((isMaths ? marks1 >= 85 : marks1 >= 80)
                || (isMaths ? marks2 >= 85 : marks2 >= 80)) return "YES";
        return "MAYBE";
    }
}