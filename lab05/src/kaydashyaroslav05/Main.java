package kaydashyaroslav05;

import java.util.*;

public class Main {

    private static String str = "sad , ,sda , tt,22,!,23a4 3 4 6 ^ a b c a,b,c";

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
            // add space
            if (Character.isAlphabetic(chars[i + 1]) && chars[i] == ',') {
                stringBuilder. append(" ");
            }
        }
        str = stringBuilder.toString();
    }

    //the method assigns the value s to the static variable str
    static void fillString(String s) {
        str = s;
    }

    //performs logic 3 labi
    static void doLogic() {
        //remove symbols and numbers
        removeSigns();
        //remove duplicate characters and spaces
        removeDuplicate();
        //make spaces between commas and letters
        depthBeetwen();
        //shows the result
        System.out.println(str);
    }


    //my collection
    static class MyCollection {

        // array to work with
        String[] arr;
        // array length
        int count = 0;

        //constructor for empty values
        private MyCollection() {
            //max length 2^32
            arr = new String[2^32];
        }

        //constructor for array of strings
        private MyCollection(String[] s) {
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

                //iteration variable
                int iterCount = 0;

                //checking for the presence of the next object
                @Override
                public boolean hasNext() {
                    return iterCount < size() && arr[iterCount] != null;
                }

                //return next object
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
    }

    public static void main(String[] args) {
        // create a collection
        MyCollection myCollection = new MyCollection();
        //make a cycle of 10 actions
        for (int i = 0; i < 10; i++) {
            //create an object of the Scanner class in order to read data from the console
            Scanner myInput = new Scanner(System.in);
            //create a variable that will receive data from the console
            String myStr = myInput.nextLine();
            //assign value to external variable
            fillString(myStr);
            //add values ​​from console to my collection
            myCollection.add(str);
            //execute logic 3 labi
            doLogic();
            //add 2 elements to further demonstrate methods
            if (i == 2) {
                myCollection.add("removed");
                myCollection.add("iterationRemove");
            }
        }

        // print my collection
        System.out.println(myCollection.toString());

        //create an iterator
        Iterator iterator = myCollection.iterator();

        // do iteration
        while (iterator.hasNext()) {
            //implement iteration
            Object next = iterator.next();
            if (next.toString().equals("iterationRemove")) {
                System.out.println("iterationRemove was removed");
                //demonstrate the iterator.remove() method
                iterator.remove();
            }
        }

        // print my collection
        System.out.println(myCollection.toString());

        //demonstrate the remove method
        if (myCollection.contains("removed")) {
            System.out.println("MyCollection contains removed: " + myCollection.remove("removed"));
        }

        // print my collection
        System.out.println(myCollection.toString());

        //create an array from my collection
        String[] myCollArray = (String[]) myCollection.toArray();
        //check the array and our collection for identity
        System.out.println("Checking containsAll method: " + myCollection.containsAll(myCollArray));

        //print the array i.e. check the implementation of the toArray() method
        for (int i = 0; i < myCollection.size(); i++) {
            System.out.println(myCollArray[i]);
        }

        // clean my collection
        myCollection.clear();

        // print my collection
        System.out.println(myCollection.toString());
    }

}