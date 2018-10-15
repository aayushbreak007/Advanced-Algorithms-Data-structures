package DataStructures.Stacks;



import java.util.Scanner;

import java.util.Stack;



public class MaximumElementInStack {

    public static void main(String[] args){

        Scanner in=new Scanner(System.in);

        int q=in.nextInt();

        Stack<Integer> stack=new Stack<>();

        Stack<Integer> aux=new Stack<>();

        while(--q>=0){



            int t=in.nextInt();

            if(t==1){

                int num=in.nextInt();

                if(aux.isEmpty()){

                    aux.push(num);}

                else if(num>aux.peek()){

                    aux.push(num);

                }else{

                    aux.push(aux.peek());

                }

                stack.push(num);



            }else if(t==2){

                stack.pop();

                aux.pop();



            }else {

                System.out.println(aux.peek());





            }

        }



    }

}
