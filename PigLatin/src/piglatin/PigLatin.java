/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piglatin;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jieliang
 */
public class PigLatin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("******Pig Latin******");
        System.out.println("Pig Latin version: " + pigLatin());
    }
    /*
     * pig latin program
     */

    public static String pigLatin() {
        System.out.print("Please Enter sentence: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] words = input.split(" ");
        String pigs = "";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char first = word.charAt(0);
            String pig;
            if (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u') // vowel
            {
                pig = word + "hay";
            } else {
                pig = word.substring(1) + word.charAt(0) + "ay";
            }
            pigs += pig;
            pigs += " ";
        }
        return pigs;
    }

    public static void invalidInput() {
        System.out.println("What you enter is not a valid input, Please enter again.");
    }

    public static void translate(String sentence) {
        ArrayList<String> words = new ArrayList<String>();
        
    }
}
