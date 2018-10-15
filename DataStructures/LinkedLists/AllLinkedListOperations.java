package DataStructures.LinkedLists;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AllLinkedListOperations {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }


    }
    /*****************************HACKERANK OPERATIONS*************************************/
    //1.)
    static void printSinglyLinkedList(SinglyLinkedListNode head) {

        if(head==null){
            return ;
        }
        SinglyLinkedListNode currentNode=head;
        while(currentNode!=null){
            System.out.println(currentNode.data);
            currentNode=currentNode.next;
        }
    }

    //2.)
    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
        SinglyLinkedListNode newNode=new SinglyLinkedListNode(data);
        if(head==null){
            head=newNode;
            return head;
        }
        SinglyLinkedListNode currentNode=head;
        while(currentNode.next!=null){
            currentNode=currentNode.next;
        }
        currentNode.next=newNode;
        return head;

    }

    //3.)
    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode head, int data) {

        SinglyLinkedListNode newNode=new SinglyLinkedListNode(data);
        if(head==null){
            head=newNode;
            return head;
        }
        newNode.next=head;
        head=newNode;
        return head;
    }

    //4.)
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {

        SinglyLinkedListNode newNode=new SinglyLinkedListNode(data);
        if(head==null){
            head=newNode;
            return head;
        }
        if(position==0){
            newNode.next=head;
            head=newNode;

        }else{

            int currPos=-1;
            SinglyLinkedListNode currentNode=head;
            while(currentNode.next!=null){
                currPos++;
                if(currPos==position-1){
                    newNode.next=currentNode.next;
                    currentNode.next=newNode;
                    return head;
                }
                currentNode=currentNode.next;

            }
            //if it is the last node
            currentNode.next=newNode;
        }
        return head;
    }

    //5.)
    static SinglyLinkedListNode deleteNode(SinglyLinkedListNode head, int position) {

        SinglyLinkedListNode currentNode=head;
        if(head==null){
            return head;
        }
        if(position==0){
            //removal at the beg
            head=currentNode.next;
        }else{

            int currPos=-1;
            while(currentNode.next!=null){
                currPos++;
                if(currPos==position-1){
                    SinglyLinkedListNode removingNode=currentNode.next;
                    currentNode.next=removingNode.next;
                    return head;
                }
                currentNode=currentNode.next;
            }
        }
        return head;
    }

    //6.)
    static void reversePrint(SinglyLinkedListNode head) {
        //This is a simple trick that can be solved using Recursion Stack
        if (head == null) {
            return;
        }
        if(head.next!=null){
            reversePrint(head.next);}
        System.out.println(head.data);


    }

    //7.)
    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        if(head1==null && head2==null){
            return true;
        }
        while(head1.next!=null){
            if(head1.data!=head2.data){//data should be matching
                return false;
            }
            if(head2.next==null){//length should be equal
                return false;
            }
            head1=head1.next;
            head2=head2.next;


        }
        return true;
    }

    //8.)
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        SinglyLinkedListNode result = null;
        if (head1.data <= head2.data) {
            result = head1;
            result.next = mergeLists(head1.next, head2);
        } else {
            result = head2;
            result.next = mergeLists(head1, head2.next);
        }
        return result;
    }


    //9.)
    static int getNodeFromTail(SinglyLinkedListNode head,int positionFromTail) {

        if(head==null){
            return head.data;
        }
        int size=0;
        int counter=0;
        SinglyLinkedListNode currentNode=head;
        while(currentNode.next!=null)
        {
            size++;
            currentNode=currentNode.next;
        }
        currentNode=head;

        while(currentNode!=null)
        {
            counter++;
            if(counter==(size-(positionFromTail-1))){
                return currentNode.data;
            }
            currentNode=currentNode.next;
        }

        return head.data;

    }

    //10.) Cycle detection
    static boolean hasCycle(SinglyLinkedListNode head) {

        SinglyLinkedListNode slow=head;
        SinglyLinkedListNode fast=head;

        while(fast!=null && slow!=null && fast.next!=null ){

            slow=slow.next;
            fast=fast.next.next;

            if(slow==fast){
                return true;
            }

        }
        return false;

    }


    //11.) Find the node that connects 2 linked lists
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        while(head1!=null && head2!=null){
            if(head1.next==head2.next)
            {
                return head1.next.data;
            }
            head1=head1.next;
            head2=head2.next;
        }
        return 0;
    }






    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        int tests = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int testsItr = 0; testsItr < tests; testsItr++) {
            SinglyLinkedList llist1 = new SinglyLinkedList();

            int llist1Count = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llist1Count; i++) {
                int llist1Item = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist1.insertNode(llist1Item);
            }

            SinglyLinkedList llist2 = new SinglyLinkedList();

            int llist2Count = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llist2Count; i++) {
                int llist2Item = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist2.insertNode(llist2Item);
            }

            SinglyLinkedListNode llist3 = mergeLists(llist1.head, llist2.head);

            printSinglyLinkedList(llist3);

        }


        scanner.close();
    }
}