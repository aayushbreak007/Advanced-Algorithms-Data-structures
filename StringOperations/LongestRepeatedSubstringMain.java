package StringOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestRepeatedSubstringMain {


    //USEFUL IN DATA ANALYSIS AND DNA ANALYSIS
    public List<String> getSuffixes(String text){
        int lengthOfText=text.length();
        List<String> suffixesList=new ArrayList<>();
        for(int i=0;i<lengthOfText;i++){
            suffixesList.add(text.substring(i,lengthOfText));
        }
        return suffixesList;
    }
    public String getLongestCommonPrefix(String text1,String text2){
        int commonLength=Math.min(text1.length(),text2.length());
        for(int i=0;i<commonLength;i++){
            if(text1.charAt(i)!=text2.charAt(i)){
                return text1.substring(0,i);//return any text
            }
        }
        return text1.substring(0,commonLength);
    }




    public String getLongestRepeatingSubstring(String text){

        int lengthOfText=text.length();
        //First get the suffixes
        List<String> suffixes=getSuffixes(text);

        //sort the suffixes
        Collections.sort(suffixes);
        //JAVA USES MERGE SORT FOR REFERENCE TYPE AND QUICK SORT FOR PRIMITIVE TYPES
        String longestSubstring="";

        for(int i=0;i<lengthOfText-1;i++){
            String tempString=getLongestCommonPrefix(suffixes.get(i),suffixes.get(i+1));
            if(tempString.length()>longestSubstring.length()){
                longestSubstring=tempString;
            }
        }
        return longestSubstring;
    }


    public static void main(String[] args){
        LongestRepeatedSubstringMain obj=new LongestRepeatedSubstringMain();
        System.out.println(obj.getLongestRepeatingSubstring("hellohehehelloi"));
    }
}
