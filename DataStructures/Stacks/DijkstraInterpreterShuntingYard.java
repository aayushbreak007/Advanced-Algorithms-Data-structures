package DataStructures.Stacks;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.Stack;

import java.util.StringTokenizer;



public class DijkstraInterpreterShuntingYard {

    public static void main(String[] args)throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine());





        Stack<String> operationStack=new Stack<>();

        Stack<Double> valueStack=new Stack<>();



        while(st.hasMoreTokens())

        {

            String x=st.nextToken();

            if(x.equals("("))

            {

                //do nothing

            }

            else if(x.equals("+"))

            {

                operationStack.push("+");



            }

            else if(x.equals("*"))

            {

                operationStack.push("*");

            }

            else if(x.equals(")"))

            {

                String operation =operationStack.pop();

                if(operation.equals("+"))

                {

                    //add the two values

                    valueStack.push(valueStack.pop()+valueStack.pop());

                }

                else if(operation.equals("*"))

                {

                    //multiply the two values

                    valueStack.push(valueStack.pop()*valueStack.pop());

                }

            }

            else

            {

                //otherwise it is a value and push it into the value stack

                valueStack.push(Double.parseDouble(x));

            }

        }



        System.out.println(valueStack.pop());

    }

}
