package StringOperations;

import java.util.ArrayList;
import java.util.List;

public class StringPrefixes {
    //Prefer using "StringBuilder"
    public List<String> getPrefixes(String text){
        int lengthOfText=text.length();
        List<String> prefixList=new ArrayList<>();
        for(int i=1;i<lengthOfText+1;i++){
            prefixList.add(text.substring(0,i));
        }
        return prefixList;
    }

    public static void main(String[] args){
        StringPrefixes obj=new StringPrefixes();
        List<String> prefixes=obj.getPrefixes("Hello");
        for(String s:prefixes){
            System.out.println(s);
        }
    }
}
