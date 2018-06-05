package com.esipovich.data_structures.basic_data_structures.brackets_in_the_code;

import java.util.*;

/**
 * @author Artem Esipovich 6/4/2018
 *
 * Check if the bracketsPairs in the code are properly placed
 *
 * input: sourcce code
 * output: check if the bracketsPairs are right. If not, output the index of the first error.
 *
 * If the bracketsPairs in the line are correctly positioned, print "Success."
 * Otherwise, output the index (use indexing from one) of the first closing parenthesis,
 * for which there is no corresponding opening. If there is no such, output the index of the first opening bracket
 * for which there is no corresponding closing.
 *
 * examples:
 * in: [] out: Success
 * in: {}[] out: Success
 * in: [()] out: Success
 * in: ( out: 1
 * in: {[} out: 3
 * in: foo(bar[i) out: 10
 */

public class BracketsInTheCode {
    private static Map<Character, Character> bracketsPairs = new HashMap<>();

    static {
        bracketsPairs.put('}', '{');
        bracketsPairs.put(']', '[');
        bracketsPairs.put(')', '(');
    }
    private static List<Character> openingBrackets = Arrays.asList('[', '{', '(');

    public static void main(String[] args) {
        checkBrackets("{}[]");
        checkBrackets("[()]");
        checkBrackets("(");
        checkBrackets("{[}");
        checkBrackets("foo(bar[i)");
        checkBrackets("foo(bar);");
        checkBrackets("()({}");
        checkBrackets("()[]}");
    }

    private static void checkBrackets(String line) {
        Stack<Character> bracketsFromLine = new Stack<>();
        Stack<Integer> indexes = new Stack<>();
        int i = 1;
        for (char currentCharacter : line.toCharArray()){
            if (openingBrackets.contains(currentCharacter)){
                bracketsFromLine.push(currentCharacter);
                indexes.push(i);
                i++;
                continue;
            }
            if (bracketsPairs.containsKey(currentCharacter)){
                if (!bracketsFromLine.empty() && bracketsFromLine.peek() == bracketsPairs.get(currentCharacter)){
                    bracketsFromLine.pop();
                    indexes.pop();
                } else {
                    indexes.push(i);
                    break;
                }
            }
            i++;
        }

        System.out.println(indexes.empty() ? "Success" : indexes.pop());
    }
}
