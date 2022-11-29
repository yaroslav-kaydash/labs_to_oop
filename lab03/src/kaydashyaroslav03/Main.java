package kaydashyaroslav03;

import java.util.Scanner;

public class Main {

    private static String str = "sad ,sda , tt,22,!,23a4 3 4 6 ^ a b c a,b,c";

    //method removes all numbers and characters except space and ','
	
    static void removeSigns() {
		
	//create a variable to store values
        StringBuilder stringBuilder = new StringBuilder();
		
	//from an external variable we make an array char
        char[] chars = str.toString().toCharArray();

	//loop through each element of the array
        for (int i = 0; i < chars.length; i++) {
			
	//if the condition is met, add the value from the array to the stringBuilder variable
	//Character.isAlphabetic() - returns true if character(char) is a letter
            if (Character.isAlphabetic(chars[i]) || chars[i] == ' ' || chars[i] == ',') {
                stringBuilder.append(chars[i]);
            }
        }
        str = stringBuilder.toString();
    }

    //method removes duplicate characters(',') and spaces
    static void removeDuplicate() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                break;
            }
            if (chars[i] != chars[i + 1] || Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
            }
        }
        str = stringBuilder.toString();
    }

    //method makes spaces between letters and symbols(',')
    static void depthBeetwen() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                break;
            }
            stringBuilder.append(chars[i]);
            //add space
            if (Character.isAlphabetic(chars[i + 1]) && chars[i] == ',') {
                stringBuilder.append(" ");
            }
        }
        str = stringBuilder.toString();
    }

    //the method assigns the value s to the static variable str
    static void fillString(String s) {
        str = s;
    }

    public static void main(String[] args) {
        //create an object of the Scanner class in order to read data from the console
        Scanner myInput = new Scanner(System.in);
        //create a variable that will receive data from the console
        String myStr = myInput.nextLine();
        //assign value to external variable
        fillString(myStr);
        //remove symbols and numbers
        removeSigns();
        System.out.println(str);
        //remove duplicate characters and spaces
        removeDuplicate();
        System.out.println(str);
        //make spaces between commas and letters
        depthBeetwen();
        System.out.println(str);
    }

}