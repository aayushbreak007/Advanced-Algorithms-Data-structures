package StringOperations;

import java.util.ArrayList;
import java.util.List;

public class StringSuffixes {

    //PREFER USING "String" with o(1) substring operation

    public List<String> getSuffixes(String text){
        int lengthOfText=text.length();
        List<String> suffixesList=new ArrayList<>();
        for(int i=0;i<lengthOfText;i++){
            suffixesList.add(text.substring(i,lengthOfText));
        }
        return suffixesList;
    }

    public static void main(String[] args){
        StringSuffixes obj=new StringSuffixes();
        List<String> suffixes=obj.getSuffixes("Hello");

        for(String s:suffixes){
            System.out.println(s);
        }
    }
}
