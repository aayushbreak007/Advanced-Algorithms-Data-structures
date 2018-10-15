package StringOperations;

public class LongestCommonPrefix {

    public String getLongestCommonPrefix(String text1,String text2){
        int commonLength=Math.min(text1.length(),text2.length());
        for(int i=0;i<commonLength;i++){
            if(text1.charAt(i)!=text2.charAt(i)){
                return text1.substring(0,i);//return any text
            }
        }
        return text1.substring(0,commonLength);
    }

    public static void main(String[] args){
        LongestCommonPrefix obj=new LongestCommonPrefix();
        System.out.println(obj.getLongestCommonPrefix("Helo","HelloOK"));//return Hel
    }
}
