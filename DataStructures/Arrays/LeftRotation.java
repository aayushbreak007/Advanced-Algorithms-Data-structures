package DataStructures.Arrays;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.StringTokenizer;



public class LeftRotation {

    public static void main(String[] args)throws IOException{



        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());

        int d=Integer.parseInt(st.nextToken());

        int[] ar=new int[n];

        StringTokenizer st2=new StringTokenizer(br.readLine());

        while(st2.hasMoreTokens()){

            for(int i=0;i<ar.length;i++){

                ar[i]=Integer.parseInt(st2.nextToken());

            }

        }

        doLeftRotation(ar,d,n);

    }



    private static void doLeftRotation(int[] ar,int d,int n) {

        //reverse elements till d in ar[]



        d%=n;//this is needed when number of rotation are greater than n

        reverse(ar,0,d-1);

        reverse(ar,d,n-1);

        reverse(ar,0,n-1);



        for(int i=0;i<ar.length;i++){

            System.out.print(ar[i]+" ");}

    }



    private static void reverse(int[] ar,int start,int end) {

        int temp;

        while(start<end)

        {

            temp=ar[start];

            ar[start]=ar[end];

            ar[end]=temp;

            start++;

            end--;

        }





    }





}
