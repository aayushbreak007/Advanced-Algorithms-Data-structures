package Recursion;

public class AdditionOfNumbers {
    public static int additionRecursive(int n){
        if(n==1){
            return 1;
        }
        return n+additionRecursive(n-1);
    }
    public static long factorial(int n){
        if(n==0){
            return 1;
        }
        return n*(factorial(n-1));
    }
    public static void main(String[] args){

        System.out.println(additionRecursive(5));
        System.out.println("Factorial:"+factorial(15));
    }
}
