package DataStructures.Arrays;


import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.StringTokenizer;



public class HourGlass {

    public static void main(String[] args)throws IOException

    {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int ar[][]=new int[6][6];

        for(int i=0;i<6;i++)

        {

            StringTokenizer st=new StringTokenizer(br.readLine());

            int u=0;

            while(st.hasMoreTokens())

            {

                ar[i][u]=Integer.parseInt(st.nextToken());

                u++;

            }

        }



        findMaxHourGlass(ar);

    }



    private static void findMaxHourGlass(int[][] ar) {

        int max=Integer.MIN_VALUE;

        int sum;

        for(int i=0;i<=ar.length-3;i++)

        {

            for(int j=0;j<=ar.length-3;j++)

            {

                sum=getHourGlassSum(ar,i,j);

                if(sum>max)

                {

                    max=sum;

                }

            }

        }

        System.out.println(max);

    }



    private static int getHourGlassSum(int[][] ar, int i, int j) {

        int sum=ar[i][j]+ar[i][j+1]+ar[i][j+2]+ar[i+1][j+1]+ar[i+2][j]+ar[i+2][j+1]+ar[i+2][j+2];

        return sum;

    }

}
