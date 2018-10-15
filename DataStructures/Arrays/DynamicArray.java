package DataStructures.Arrays;


import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.ArrayList;

import java.util.StringTokenizer;



public class DynamicArray {

    public static void main(String[] args)throws IOException

    {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1=new StringTokenizer(br.readLine());

        int N=0,Q=0;

        int lastAns=0;

        while(st1.hasMoreTokens()) {

            N=Integer.parseInt(st1.nextToken());

            Q=Integer.parseInt(st1.nextToken());

        }

        ArrayList<ArrayList<Integer>> arOfSequences=new ArrayList<>();

        for(int i=0;i<N;i++){

            //creating sequences

            arOfSequences.add(new ArrayList<>());

        }



        while(--Q>=0){

            StringTokenizer st2=new StringTokenizer(br.readLine());

            int type=Integer.parseInt(st2.nextToken());

            int x=Integer.parseInt(st2.nextToken());

            int y=Integer.parseInt(st2.nextToken());



            if(type==1){

                ArrayList<Integer> sequence=arOfSequences.get((x^lastAns)%N);

                sequence.add(y);



            }else {

                ArrayList<Integer> sequence= arOfSequences.get((x^lastAns)%N);

                lastAns=sequence.get(y%sequence.size());

                System.out.println(lastAns);



            }



        }

    }

}
