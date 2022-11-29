package kaydashyaroslav06;

import java.io.*;
import java.util.*;

public class Utill {

    //my collection
    //the class which is used by 2 others
    public static class MyCollection implements Serializable {

        // array to work with
        String[] arr;
        // array length
        int count = 0;

        //constructor for empty values
        MyCollection() {
            //max length 2^32
            arr = new String[2^32];
        }

        //constructor for array of strings
        MyCollection(String[] s) {
            arr = s;
            count = s.length;
        }

        //returns the length of the array
        public int size() {
            return count;
        }


        //checks for the presence of an object in the arr variable
        public boolean contains(String o) {
            //make a copy of arr variable
            String[] copyArr = arr.clone();
            System.arraycopy(copyArr, 0, arr, 0, size());
            //we go through each element of the array and check for presence
            for (String s : copyArr) {
                if (s != null && s.equals(o)) {
                    return true;
                }
            }
            return false;
        }

        //return a string from the elements of the arr variable
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size(); i++) {
                sb.append(arr[i] + " ");
            }
            return sb.toString();
        }


        public Iterator iterator() {
            return new Iterator() {

                //variable for iteration
                int iterCount = 0;

                //we check for the presence of the following object
                @Override
                public boolean hasNext() {
                    return iterCount < size() && arr[iterCount] != null;
                }

                //we meet the next object
                @Override
                public Object next() {
                    return arr[iterCount++];
                }

                //delete the current object
                @Override
                public void remove() {
                    int i = iterCount - 1;
                    String[] copyArr = new String[arr.length - 1];
                    System.arraycopy(arr, 0, copyArr, 0, i);
                    System.arraycopy(arr, i + 1, copyArr, i, copyArr.length - i - 1);
                    arr = copyArr;
                    count--;
                }
            };
        }

        //returns a copy of the variable arr
        public Object[] toArray() {
            String[] copyArr = new String[size()];
            System.arraycopy(arr, 0, copyArr, 0, copyArr.length);
            return copyArr;
        }

        //add object to arr variable
        public boolean add(Object o) {
            arr[count++] = o.toString();
            int maximum = 2^32;
            return size() < maximum;
        }

        //remove the object from the arr variable
        public boolean remove(Object o) {
            for (int i = 0; i < size(); i++) {
                // check for the presence of a variable about
                if (Objects.equals(arr[i], o.toString())) {
                    String[] copyArr = new String[size()];
                    System.arraycopy(arr, 0, copyArr, 0, i);
                    System.arraycopy(arr, i + 1, copyArr, i, copyArr.length - i - 1);
                    //assign new value to variable arr
                    arr = copyArr;
                    count--;
                    return true;
                }
            }
            return false;
        }

        //empty arr variable
        public void clear() {
            arr = new String[2^32];
            count = 0;
        }

        //check for the presence of all elements from the array of strings in the arr variable
        public boolean containsAll(String[] c) {
            int sameCount = 0;
            for (int i = 0; i < size(); i++) {
                for (int j = 0; j < c.length; j++) {
                    if (Objects.equals(arr[i], c[j])) {
                        sameCount++;
                        break;
                    }
                }
            }
            return sameCount == c.length;
        }

        // bubble sort
        public void sort() {
            String temp;
            for (int j = 0; j < size(); j++) {
                for (int i = j + 1; i < size(); i++) {
                    if (arr[i].compareTo(arr[j]) < 0) {
                        temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
        }

        //0 if the string is equal to the other string.
        //< 0 if the string is lexicographically less than the other string
        //> 0 if the string is lexicographically greater than the other string (more characters)
        //666 if one of index is not legit
        public String compareElem(int firIndex, int secIndex) {
            if (firIndex > size() || secIndex > size() || firIndex < 0 || secIndex < 0) {
                return "Enter correct index!!!";
            }
            String firstEl = arr[firIndex];
            String secondEl = arr[secIndex];
            int res = firstEl.compareTo(secondEl);
            return String.format("Comparing elements: %s and %s; \nresult: %d", firstEl, secondEl, res);
        }

    }

    private static Scanner scanner;
    private static String textTwo;
    private static String textOne;

    /**
     * Start function
     *
     * @param textOne, textTwo
     */
    static void sortText(String textOne, String textTwo) {
        System.out.print("\n");
        sortAplphabet(textOne, textTwo);
        count(textOne, textTwo);
    }

    /**
     * Sorting text
     *
     * @param textOne, textTwo
     * @return Sort text which alphabet
     */
    static void sortAplphabet(String textOne, String textTwo) {
        String letterOne = Character.toString(textOne.charAt(0));
        String letterTwo = Character.toString(textTwo.charAt(0));

        ArrayList<String> abc = new ArrayList<String>();
        abc.add(letterOne);
        abc.add(letterTwo);

        Collections.sort(abc);
        Iterator<String> itr = abc.iterator();

        if (itr.next() == letterOne) {
            System.out.println("First letter is: " + letterOne);
            System.out.println(textOne);
            System.out.println("Second letter is: " + letterTwo);
            System.out.println(textTwo);
        } else {
            System.out.println("Second letter is: " + letterTwo);
            System.out.println(textTwo);
            System.out.println("First letter is: " + letterOne);
            System.out.println(textOne);
        }
        System.out.println("\n");

    }

    /**
     * Count letter in sting
     *
     * @param textOne, textTwo
     * @return biggest text
     */
    static void count(String textOne, String textTwo) {

        int lengthOne = textOne.length();
        int lengthTwo = textTwo.length();

        if (lengthOne > lengthTwo) {
            System.out.println("Count length text One: " + textOne.length());
            System.out.println(textOne);
            System.out.println("Count length text Two: " + textTwo.length());
            System.out.println(textTwo);

        } else {
            System.out.println("Count length text Two: " + textTwo.length());
            System.out.println(textTwo);
            System.out.println("Count length text One: " + textOne.length());
            System.out.println(textOne);
        }

    }

    //returns the last collection
    static MyCollection readObject() {
        try {
            FileInputStream fos = new FileInputStream("safe.bin");
            ObjectInputStream outputStream = new ObjectInputStream(fos);
            MyCollection fileCollection = (MyCollection) outputStream.readObject();
            return fileCollection;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new MyCollection();
    }

    //write to file
    static void writeObject(MyCollection writeCollection) {
        try {
            FileOutputStream fos = new FileOutputStream("safe.bin");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(writeCollection);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Console application entry point
     *
     * @param args command line parameters
     */
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        System.out.println("1 line: ");
        textOne = scanner.nextLine();
        System.out.println("2 line: ");
        textTwo = scanner.nextLine();

        sortText(textOne, textTwo);

        // create a collection
        MyCollection myCollection = new MyCollection();

        System.out.println("Enter a number to select an action");

        //text menus:
        System.out.println("1 - create collection and do logic(lab3)");
        System.out.println("2 - do compare of elements");
        System.out.println("3 - sort collection");
        System.out.println("4 - find element(findingElem)");
        System.out.println("5 - write and read file");
        System.out.println("6 - end");


        //first input
        Scanner myInput1 = new Scanner(System.in);

        // end flag
        boolean isEnd = true;

        while (isEnd) {
            String numberImp = myInput1.nextLine();
            //switch use first input as a parameter
            //use first input parameter
            switch (numberImp) {
                case("1"):
                    //populate the collection
                    myCollection = new MyCollection(new String[]{textOne, textTwo});
                    break;
                case("2"):
                    //do element comparison
                    System.out.println(myCollection.compareElem(0, 1));
                    break;
                case("3"):
                    System.out.println("Collection before sorting: " + myCollection);
                    // sort the collection
                    myCollection.sort();
                    System.out.println("After sorting: " + myCollection);
                    break;
                case("4"):
                    //search element
                    String findingElem = "findingElem";
                    //string to be output
                    String format = String.format("We search : %s \nDoes we find anything: %b ", findingElem, myCollection.contains(findingElem));
                    System.out.println(format);
                    break;
                case("5"):
                    //write the collection to a file
                    writeObject(myCollection);
                    MyCollection myCollection1 = readObject();
                    System.out.println(myCollection1);
                    break;
                case("6"):
                    //end the loop
                    isEnd=false;
                    System.out.println("end");
                    break;
                //if user use input incorrect value in console
                default:
                    System.out.println("Enter one of the numbers from 1 to 5 for the correct robot");
            }
        }
    }

}