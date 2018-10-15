package StringOperations;

public class SubstringSearchBruteForce {

    public static int search(String text,String pattern){
        int lengthOfText=text.length();
        int lengthOfPattern=pattern.length();

        for (int i=0;i<=lengthOfText-lengthOfPattern;i++){
            int j;
            for(j=0;j<lengthOfPattern;j++){
                if(text.charAt(i+j)!=pattern.charAt(j)){
                    //mismatch
                    break;
                }
            }
            if(j==lengthOfPattern){
                //all characters are matchin in the subnstring pattern
                return i;
            }
        }
        return lengthOfText;
    }


    public static void main(String[] args){
        String text="test is";

        String pattern="is";
        System.out.println(search(text,pattern));

    }
}
