package kaydashyaroslav04;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static String str = "";

    //method removes all numbers and characters except space and ','
    static void removeSigns() {
		
        //create a variable to store values
        StringBuilder stringBuilder = new StringBuilder();
		
        //from an external variable we make an array char
        char[] chars = str.toString().toCharArray();



        //iterate over each element of the array
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

            if (i % 2 == 0 && chars[i] != chars[i + 1] || Character.isAlphabetic(chars[i])) {
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
            // add space
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

        System.out.println("Enter a number to select actions");


// text menus:
        System.out.println("1 - data entry");
        System.out.println("2 - view data");
        System.out.println("3 - performing calculations");
        System.out.println("4 - result display");
        System.out.println("5 - end");

        //first input
        Scanner myInput1 = new Scanner(System.in);
        //second input
        Scanner myInput2 = new Scanner(System.in);

        //main value
        String myStr = "type the number 1 into the console to enter data";

        //end flag
        boolean isEnd = true;

        //debug flag
        boolean deb = false;


// additional info
        String dopInfo = "";

        //cycle is running isEnd = true
        while (isEnd) {
            String numberImp = myInput1.nextLine();
            //switch use first input as a parameter
            //use parametr first withdrawal
            switch (numberImp) {
                //introduction of data
                //if p = 1, then it is done to every case
                case ("1"):
                    System.out.println("Enter the data");
                    myStr = myInput2.nextLine();
                    fillString(myStr);
                    break;
// revisiting data
                case ("2"):
                    //if deb = true , add additional information
                    if (deb) {
                        dopInfo = " \ndata type - String \nlength - " + myStr.length();
                    }
                    System.out.println("data: " + myStr + dopInfo);
                    break;
//performing calculations
                case ("3"):
                    //check for value
                    if (Objects.equals(str, "")) {
                        System.out.println("Enter the initial data!!!");
                        break;
                    }
                    System.out.println("performing calculations...");
                    //if deb = true, add additional information
                    if (deb) {
                        removeSigns();
                        System.out.println("the result of the method removeSigns(): " + str);
                        removeDuplicate();
                        System.out.println("the result of the method removeDuplicate(): " + str);
                        depthBeetwen();
                        System.out.println("the result of the method depthBeetwen(): " + str);
                        break;
                    }
                    removeSigns();
                    removeDuplicate();
                    depthBeetwen();
                    break;
//result display
                case ("4"):
                    //if deb = true add additional information
                    if (deb) {
                        int lenghtDif = myStr.length() - str.length();
                        dopInfo = "\ninitial data: " + myStr + "\nwas deleted: " + lenghtDif + " symbols";
                    }
                    System.out.println("result: " + str + dopInfo);
                    break;
                //end
                case ("5"):
                    isEnd = false;
                    System.out.println("end");
                    break;

// information about the author of the program, purpose (individual task), detailed description of operating modes (menu items and command line parameters) is displayed;
                //write information
                case ("-h"):
                case ("-help"):
                    System.out.println("smt aboute you");
                    System.out.println("Task of the program: Remove all characters from the text, except spaces that are not letters." +
                            " Repeated omissions should be replaced by single ones. " +
                            "Between sequences of letters where there are punctuation marks" +
                            ", leave at least one gap (\"a,b,c\" -> \"a, b, c\"). " +
                            "Output the initial text and the result.");
                    System.out.println("menu item (1) - input of data that will be used for other stages");
                    System.out.println("menu item (2) - input of initial data");
                    System.out.println("menu item (3) - implementation of methods:");
                    System.out.println("removeSigns() - method removes all numbers and symbols except space and ','");
                    System.out.println("removeDuplicate() - the method removes duplicate characters (',') and spaces");
                    System.out.println("depthBeetwen() - the method creates spaces between letters and symbols (',')");
                    System.out.println("menu item (4) - input of final data");
                    System.out.println("menu item (5) - end of program");
                    break;

//during the operation of the program, additional data are displayed that facilitate debugging
// and checking of the program's operability:
// diagnostic messages,
// intermediate values of variables,
// values of temporary variables, etc.


//deb = true
                case ("-d"):
                case ("-debug"):
                    deb = true;
                    System.out.println("During operation, the program will display additional data");
                    break;
                //if user use input incorrect value in console
                default:
                    System.out.println("Enter one of the numbers from 1 to 5 for the correct operation of the program");
            }
        }

    }

}