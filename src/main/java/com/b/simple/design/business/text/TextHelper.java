package com.b.simple.design.business.text;

public class TextHelper {

    public String swapLastTwoCharacters(String str) {
        if (str == null || str.length() < 2) return str;

        char[] charArr = str.toCharArray();
		int length = charArr.length;
		char temp = charArr[length - 2];
        charArr[length - 2] = charArr[length - 1];
        charArr[length - 1] = temp;
        return String.valueOf(charArr);
    }

    public String truncateAInFirst2Positions(String str) {
        if (str != null) {
            if (str.length() < 2) return str.replace("A", "");

            String first2Chars = str.substring(0, 2);
			String remainingStr = str.substring(2);

			return first2Chars.replace("A", "") + remainingStr;
        }
        return null;
    }
}
