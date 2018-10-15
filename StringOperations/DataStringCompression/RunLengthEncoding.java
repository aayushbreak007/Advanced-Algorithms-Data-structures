package StringOperations.DataStringCompression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {

    //AACCCEEEBBBBF----->2A3C3E4BF
    /* we use String builder since we have to manipulate a big string and append characters as well and not dealing with multi-threading env*/
    public static String Encode(String source){

        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<source.length();i++){
            //run length will start with 1---charcater standing alone should be present in the compressed data
            int runLength=1;
            while(i+1<source.length() && (source.charAt(i)==source.charAt(i+1))){
                runLength++;
                i++;
            }
            //AAAA--->4A
            stringBuilder.append(runLength);
            stringBuilder.append(source.charAt(i));
        }
        return stringBuilder.toString();

    }

    public static String Decode(String compressedText){

        StringBuilder stringBuilder=new StringBuilder();
        Pattern pattern=Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher=pattern.matcher(compressedText);

        while(matcher.find()){
            int runLength=Integer.parseInt(matcher.group());
            matcher.find();//find the next group

            while (runLength--!=0){
                //4A ---->AAAA
                stringBuilder.append(matcher.group());

            }
        }
        return stringBuilder.toString();

    }
    public static void main(String[] args){
        System.out.println(Encode("AAABBA"));
        System.out.println(Decode("3A4C1E"));


    }
}