package kaydashyaroslav09;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public class MyCollection implements Iterable, Serializable {

    // array to work with
    private String[] arr;
    // array length
    private int count = 0;

    //constructor for empty values
    public MyCollection() {
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

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}