package kaydashyaroslav02;
import java.util.Random;
public class tolab02 {

	


    //create a Random class variable to generate random values
    static Random random = new Random();

   
    private static void printBinary(int val) {
    	
     
      {if (val < 8){
            System.out.print(val);
            return;
        }
        int digit = val % 8;
        
       
      
        printBinary(val / 8);
        System.out.print(digit);
      }
    }

   
    public static void main(String[] args) {

        
        printBinary(1); System.out.println(" ERROR");   
        printBinary(2); System.out.println(" ERROR");  
        printBinary(7); System.out.println(" ERROR");   
        printBinary(254); System.out.println(" ERROR"); 
        printBinary(255); System.out.println(" ERROR"); 
        printBinary(2550); System.out.println(" ERROR"); 
        printBinary(4550); System.out.println(" ERROR"); 
        
    }
}//void main calls the iteration method, it calls System.out.println("num1: num2: gcd:"); and then executes the maxDivisor method in a loop