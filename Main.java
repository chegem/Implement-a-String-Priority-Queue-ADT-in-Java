/******************************************************************************
Student: 
Date: 27-03-2022
Topic: Implement a String Priority Queue ADT in Java

*******************************************************************************/

package adt;

public interface StringPQ {
public void insert(String s, int priority);
public String getMin();
public void setPriority(String value, int newPriority); // sets the priority for *all* matching entries to the new priority
public int size();
}

//PQEntry class

package adt;


public class PQEntry {
    private int priority;
    private String value;

    public PQEntry(String value, int priority) {
        this.priority = priority;
        this.value = value;
    }

    public PQEntry() {
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

  
}

/*
* LinkedListStringPQ class
*/
package adt;

import java.util.LinkedList;

public class LinkedListStringPQ implements StringPQ{

    LinkedList<PQEntry> list;

    public LinkedListStringPQ() {
        list = new LinkedList<>();
    }
  
    @Override
    public void insert(String s, int priority) {
        list.add(new PQEntry(s, priority));      
    }

    @Override
    public String getMin() {
        if(list.isEmpty())
            return null;
        PQEntry min = list.getFirst();
        for(PQEntry entry: list){
            if(entry.getPriority()<min.getPriority())
                min = entry;
        }
        list.remove(min);
        return min.getValue();
    }

    @Override
    public void setPriority(String value, int newPriority) {
        for(PQEntry entry: list){
            if(entry.getValue().equals(value))
                entry.setPriority(newPriority);
        }
    }

    @Override
    public int size() {
        return list.size();
    }
  
}


//ArrayStringPQ class
package adt;

public class ArrayStringPQ implements StringPQ{

    PQEntry[] list;
    int size;

    public ArrayStringPQ() {
        list = new PQEntry[2];
        size = 0;
    }
  
    @Override
    public void insert(String s, int priority) {
        if(isFull())
            expand();
        PQEntry newEntry = new PQEntry(s, priority);
        list[size++] = newEntry;
    }

    @Override
    public String getMin() {
        if(size==0)
            return null;
        PQEntry min = list[0];
        int minIndex = 0;
        for(int i=0; i<size; i++){
            PQEntry entry = list[i];
            if(entry.getPriority()<min.getPriority()){
                min = entry;
                minIndex = i;
            }
        }
        for(int i=minIndex; i<size-1; i++){
            list[i]=list[i+1];
        }
        list[size-1] = null;
        size--;
        return min.getValue();
    }

    @Override
    public void setPriority(String value, int newPriority) {
        for(int i=0; i<size; i++){
            PQEntry entry = list[i];
            if(entry.getValue().equals(value))
                entry.setPriority(newPriority);
        }
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isFull() {
        return size==list.length;
    }
  
    private void expand(){
        PQEntry[] newList = new PQEntry[list.length*2];
        for(int i=0; i<size; i++){
            newList[i] = list[i];
        }
        list = newList;
    }
  
}

// PQTester class
package adt;
public class PQTester {

    public static void main(String[] args) {
        PQTester t = new PQTester();
        System.out.println("Test with ArrayStringPQ: ");
        t.test(new ArrayStringPQ());
        System.out.println();
        System.out.println("Test with LinkedListStringPQ: ");
        t.test(new LinkedListStringPQ());

    }

    public void test(StringPQ pq) {
        pq.insert("Eat", 3);
        pq.insert("Sleep", 2);
        pq.insert("Rake the lawn", 5);
        pq.insert("Study", 1);
        pq.insert("Maintain social relationships", 4);

        pq.setPriority("Eat", 4);
        pq.setPriority("Maintain social relationships", 3);

        while (pq.size() > 0) {
            System.out.println(pq.getMin());
        }

    }
}