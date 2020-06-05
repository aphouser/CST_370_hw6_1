/*
 * HackerRank link:https://www.hackerrank.com/contests/cst370-su20-hw5/challenges/connect-components/submissions/code/1323830193
 * Title: hw6_1.java
 * Abstract: Reads in graph by user input.  Checks for connectivity and adds edges if they are needed to connect
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
                System.out.println(heap[1]);
            }
            else if (todo.equals("insert")) {
                System.out.println("insert command");
                int insertNum = in.nextInt();
                System.out.println("number to be inserted is " + insertNum);
            }
            else if (todo.equals("deleteMax")) {
                System.out.println("deleteMax command");
                System.out.println("number to be deleted is " + heap[1]);
            }
            else if (todo.equals("delete")) {
                System.out.println("delete command");
                int deleteNum = in.nextInt();
                System.out.println("number to be deleted is " + deleteNum);

            }
            else if (todo.equals("update")) {
                System.out.println("update command");
                int updateIndex = in.nextInt();
                int updateNum = in.nextInt();
                System.out.println("number to be updated is " + updateNum + " at " + updateIndex);

                // then we heapify
            }
            else if (todo.equals("display")) {
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
