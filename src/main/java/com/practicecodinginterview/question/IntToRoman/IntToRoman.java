package com.practicecodinginterview.question.IntToRoman;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class IntToRoman {

	/**
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * Chart at http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
	 * 
	 * I - 1
	 * II - 2
	 * III - 3
	 * IV - 4
	 * V - 5
	 * VI - 6
	 * VII - 7
	 * VIII - 8
	 * IX - 9
	 * X - 10
	 * 
	 * 
	 * Looking at the above, when two symbols are the same or smaller in value
	 * in order they are summed. When they are inverted order they are subtracted.
	 * 
	 * 1 - 9 is obvious
	 * 
	 * Same is true of tens place
	 * 
	 * X - 10
	 * XX - 20
	 * XXX - 30
	 * XL - 40
	 * L - 50
	 * LX - 60
	 * LXX - 70
	 * LXXX - 80
	 * XC - 90
	 * C - 100
	 * 
	 * Is the same true of hundreds?
	 * 
	 * D - 500
	 * M - 1000
	 * 
	 * Summary:
	 * Every power of 10 follows the same rules.
	 * The first 3 in that power are additive
	 * 4 is subtractive
	 * 5 is new symbol
	 * 6 - 8 are additive
	 * 9 is subtractive
	 * 10 is new symbol
	 */
	public String intToRoman(int num) {
		Stack<List<String>> maps = new Stack<>();

		String[] onesMap = { "I", "V" };
		String[] tensMap = { "X", "L" };
		String[] hundredsMap = { "C", "D" };
		String[] thousandsMap = { "M" };
		String[] placeholder = { "DOESNT MATTER" };

		maps.add(Arrays.asList(placeholder));
		maps.add(Arrays.asList(thousandsMap));
		maps.add(Arrays.asList(hundredsMap));
		maps.add(Arrays.asList(tensMap));
		maps.add(Arrays.asList(onesMap));

		StringBuilder b = new StringBuilder();

		int workingNum = num;
		while (workingNum > 0) {
			int leastSig = workingNum % 10;
			b.insert(0, getSymbols(leastSig, maps.pop(), maps.peek().get(0)));
			workingNum = workingNum / 10;
		}

		return b.toString();
	}

	private String getSymbols(int num, List<String> map, String nextLevel) {
		StringBuilder b = new StringBuilder();
		if (num < 4) {
			for (int i = 0; i < num; i++) {
				b.append(map.get(0));
			}
		} else if (num == 4) {
			b.append(map.get(0));
			b.append(map.get(1));
		} else if (num == 5) {
			b.append(map.get(1));
		} else if (num > 5 && num < 9) {
			b.append(map.get(1));

			int remainder = num - 5;
			for (int i = 0; i < remainder; i++) {
				b.append(map.get(0));
			}
		} else if (num == 9) {
			b.append(map.get(0));
			b.append(nextLevel);
		}

		return b.toString();
	}
}
