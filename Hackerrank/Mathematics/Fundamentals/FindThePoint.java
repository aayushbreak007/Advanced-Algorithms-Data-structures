package Hackerrank.Mathematics.Fundamentals;

import java.util.Scanner;

public class FindThePoint {

    /* find point of inversion r. It is an inversion of point P rotated at 180 from point q.
    * This means that p,q,r ARE CO-linear and hence q becomes the mid-point of the line segment*/
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        while(--n>=0){

            int px=in.nextInt();
            int py=in.nextInt();
            int qx=in.nextInt();
            int qy=in.nextInt();

            //point of inversion at 180 degress to q
            //using mid-point of the line segment
            int rx=2*qx-px;
            int ry=2*qy-py;


            System.out.print(rx+" "+ry);

        }
    }
}
