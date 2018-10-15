package Recursion;

public class TowerOfHanoi {
    public static void solveHanoi(int plates,char rodFrom,char rodMiddle,char rodTo){
        if(plates==1){
            System.out.println("Plate 1 from "+rodFrom+" to "+rodTo);
            return;
        }
        //move n-1 plates from rodfrom to rod aux
        solveHanoi(plates-1,rodFrom,rodTo,rodMiddle);
        System.out.println("Plate "+plates+" from "+rodFrom+" to "+rodTo);
        //move n-1 rods from aux to rod to
        solveHanoi(plates-1,rodMiddle,rodFrom,rodTo);
    }

    public static void main(String[] args){
        solveHanoi(3,'A','B','C');
    }
}
