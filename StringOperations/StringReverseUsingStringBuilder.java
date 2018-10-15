package StringOperations;

public class StringReverseUsingStringBuilder {

    public String reverseString(String text){
        //USE STRING BUILDER O(N).-------STRING USES O(N^2)
        int lengthOfText=text.length();
        StringBuilder reversedString=new StringBuilder();

        for(int i=lengthOfText-1;i>=0;i--){
            //linear running time
            reversedString.append(text.charAt(i));
        }

        return reversedString.toString();
    }

    public static void main(String[] args){
        StringReverseUsingStringBuilder obj=new StringReverseUsingStringBuilder();
        System.out.println(obj.reverseString("Hello world"));
    }
}
