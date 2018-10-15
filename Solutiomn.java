import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solutiomn {
    public static void main(String[] args)throws IOException {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (--t >= 0) {

            int n=in.nextInt();
            int q= in.nextInt();
            String str="";
          //  for(int i=0;i<n;i++){
                str+=in.next();
          //  }

            char[] array = str.toCharArray();
            while (--q >= 0) {

                int r=in.nextInt();
                if(r==1){

                    int l = in.nextInt();
                    int h = in.nextInt();
                    int k = in.nextInt();
                    int count = 0;

                    for (int i = l-1; i < h; i++) {
                        for (int j = i + 1; j < h; j++) {
                            int x = (int) str.charAt(i);
                            int y = (int) str.charAt(j);
                            if (Math.abs(x - y) == k) {
                                count++;
                            }
                        }
                    }
                    System.out.println(count);

                }else {
                     int pos = in.nextInt();
                     array[pos-1] = in.next().charAt(0);
                     str = String.valueOf(array);
                }
            }


        }
    }
}
