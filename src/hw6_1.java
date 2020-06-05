/*
 * HackerRank link:https://www.hackerrank.com/contests/cst370-su20-hw6/challenges/max-heap-1-2/submissions/code/1324073891
 * Title: hw6_1.java
 * Abstract: Reads in numbers by user and creates max heap if not already there.  Commands for displayMax, insert,
*            deleteMax, delete, update and display allow the user to modify and view the heap in an array fashion.
 * Author: Adam Houser
 * ID: 1144
 * Date: 6/4/2020
 */

import java.util.Scanner;

public class hw6_1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        // reads the first line of user input, number of numbers to put in heap
        int num = in.nextInt();

        // create an array for heap at size n+1
        int[] heap = new int[num+1];

        // insert the n numbers into array at location n+1 since 0 is left empty for heaps
        for (int i = 0; i < num; i++) {
            heap[i+1] = in.nextInt();
        }

        // check if the heap is a max heap and display yes or no.  If no, construct max heap with the numbers
        boolean isHeap = true;

        // parents of the heap are in the first n/2 nodes.  Check them to see if they are great than children
        for (int i = 0; i< num/2; i++) {
            int parent = i+1;
            int leftChild = 2 * (i+1);
            int rightChild = leftChild + 1;

            // make sure the left child node exists
            if (leftChild <= num && heap[leftChild] > heap[parent]) {
                // if child > parent then it isn't a heap
                isHeap = false;

                // if it is false, no need to continue
                break;
            }

            // make sure the right child node exists
            if (rightChild <= num && heap[rightChild] > heap[parent]) {
                // if child > parent then it isn't a heap
                isHeap = false;

                // if it is false, no need to continue
                break;
            }
        }

        if (isHeap) {
            System.out.println("This is a heap.");
        }
        else {
            System.out.println("This is NOT a heap.");

            // construct max heap with these numbers
            for (int i = (num/2); i > 0; i--) {
                heapify(heap, num+1, i);
            }
        }

        // read in next number for amount of commands
        int commands = in.nextInt();

        while (commands > 0) {
            //read in the next thing
            String todo = in.next();

            if (todo.equals("displayMax")) {
                // prints out the max which is first node of the heap
                System.out.println(heap[1]);
            }
            else if (todo.equals("insert")) {
                // reads in next number as well to add to heap
                int insertNum = in.nextInt();

                // create new temp array sized +1
                int[] tempArray = new int[heap.length+1];

                // populate the values from heap to tempArray
                for (int i = 0; i < heap.length; i++) {
                    tempArray[i] = heap[i];
                }

                // add the new value to tempArray
                tempArray[heap.length] = insertNum;

                // set heap array to temp
                heap = tempArray;

                // then we need to heapify to make sure it is still a max heap
                for (int i = (heap.length/2); i > 0; i--) {
                    heapify(heap, heap.length, i);
                }
            }
            else if (todo.equals("deleteMax")) {
                // deletes the max value at heap[1] by replacing heap[1] with the last heap value
                // create new temp array sized -1
                int[] tempArray = new int[heap.length-1];

                // populate the values from heap to tempArray skipping heap[1] where the max is
                tempArray[0] = heap[0];
                tempArray[1] = heap[heap.length-1];
                for (int i = 2; i < heap.length-1; i++) {

                    tempArray[i] = heap[i];
                }

                // set heap array to temp
                heap = tempArray;

                // then we need to heapify to make sure it is still a max heap
                for (int i = (heap.length/2); i > 0; i--) {
                    heapify(heap, heap.length, i);
                }
            }
            else if (todo.equals("delete")) {
                // deletes a particular value in the heap by replacing it with the last heap value
                int deleteNum = in.nextInt();

                // create new temp array sized -1
                int[] tempArray = new int[heap.length-1];

                // find the index in heap of the number we want to delete
                int deleteIndex = 0;
                for (int i = 0; i < heap.length; i++) {
                    if (heap[i] == deleteNum) {
                        deleteIndex = i;
                    }
                }

                // populate the values from heap to tempArray manually doing the deleteIndex with the last value
                for (int i = 0; i < deleteIndex; i++) {

                    tempArray[i] = heap[i];
                }
                tempArray[deleteIndex] = heap[heap.length-1];
                for (int i = deleteIndex+1; i < heap.length-1; i++) {

                    tempArray[i] = heap[i];
                }

                // set heap array to temp
                heap = tempArray;

                // then we need to heapify to make sure it is still a max heap
                for (int i = (heap.length/2); i > 0; i--) {
                    heapify(heap, heap.length, i);
                }
            }
            else if (todo.equals("update")) {
                // updates a particular value in the heap by replacing it with the entered value
                int updateIndex = in.nextInt();
                int updateNum = in.nextInt();

                // update the value at index updateIndex with updateNum
                heap[updateIndex] = updateNum;

                // then we need to heapify to make sure it is still a max heap
                for (int i = (heap.length/2); i > 0; i--) {
                    heapify(heap, heap.length, i);
                }
            }
            else if (todo.equals("display")) {
                // prints out the heap in array format
                for (int i = 1; i < heap.length; i++) {
                    System.out.print(heap[i] + " ");
                }
                System.out.print("\n");
            }
            else {
                System.out.println("Bad command");
                break;
            }

            // decrement commands
            commands--;
        }

        in.close();
    }

    static void heapify(int heap[], int size, int initial)
    {
        int pNode = initial;
        int left = 2 * pNode;
        int right = 2 * pNode + 1;
        int max = pNode;

        // if the left child is larger than root then set the max index to the left child index
        if (left < size && heap[left] > heap[max]) {
            max = left;
        }

        // if right child is larger than root or left if left was bigger set max index to right child index
        if (right < size && heap[right] > heap[max]) {
            max = right;
        }

        // if the parent isn't largest, swap with the largest child node
        if (max != initial) {
            int temp = heap[initial];
            heap[initial] = heap[max];
            heap[max] = temp;

            // flow down with recursive call
            heapify(heap, size, max);
        }
    }
}
