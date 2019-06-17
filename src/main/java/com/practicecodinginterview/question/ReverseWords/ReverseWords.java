package com.practicecodinginterview.question.ReverseWords;

public class ReverseWords {

	/**
	 * Solution assuming you have no libraries
	 */
	public String reverse(String string) {
		char[] charArray = string.toCharArray();

		int lIdx = 0;
		int rIdx = Integer.MAX_VALUE;
		int sentenceIdx = 0;

		while (sentenceIdx < charArray.length) {
			if (charArray[sentenceIdx] == ' ') {
				sentenceIdx++; // skip whitespace
			}

			// find beginning of the word
			lIdx = sentenceIdx;

			for (int i = lIdx; i <= charArray.length; i++) {
				if (i == charArray.length || charArray[i] == ' ') {
					sentenceIdx = i;
					break;
				}
			}

			// remember where last word character was
			rIdx = sentenceIdx - 1;

			// meet in the middle swapping
			while (lIdx < rIdx) {
				char hold = charArray[lIdx];
				charArray[lIdx] = charArray[rIdx];
				charArray[rIdx] = hold;

				lIdx++;
				rIdx--;
			}
		}

		return new String(charArray);
	}

	/**
	 * Simple solution using built in libraries
	 */
	private String reverseWithLibraries(String string) {
		String words[] = string.split(" ");
		StringBuilder res = new StringBuilder();
		for (String word : words)
			res.append(new StringBuffer(word).reverse().toString() + " ");
		return res.toString().trim();
	}
}
