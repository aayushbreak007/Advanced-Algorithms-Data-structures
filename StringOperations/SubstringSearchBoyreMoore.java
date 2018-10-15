package StringOperations;

import java.util.HashMap;

public class SubstringSearchBoyreMoore {
    private String text;
    private String pattern;

    private HashMap<Character,Integer> badMatchTable;

    public SubstringSearchBoyreMoore(String text,String pattern){
        this.text=text;
        this.pattern=pattern;
        this.badMatchTable=new HashMap<>();
    }

    public void preComputeShifts(){
        //populate the badMatch table with each character in the PATTERN
        int lengthOfPattern=this.pattern.length();

        //iterate through each character in the PATTERN STRING
        for(int index=0;index<lengthOfPattern;index++){
            char actualCharacter=pattern.charAt(index);
            //formula to compute max shifts
            int maxShift=Math.max(1,(lengthOfPattern-index)-1);
            badMatchTable.put(actualCharacter,maxShift);
        }

    }

    public int search(){
        int lengthOfPattern=this.pattern.length();
        int lengthOfText=this.text.length();
        int numOfSkips;

        for(int i=0;i<=lengthOfText-lengthOfPattern;i+=numOfSkips){

            numOfSkips=0;
            //iterate through the pattern in "REVERSE ORDER "
            for(int j=lengthOfPattern-1;j>=0;j--){
                if(pattern.charAt(j)!=text.charAt(i+j)){
                    //there's a mismatch
                    //now look if the character from text is present in BMT
                    if(this.badMatchTable.get(text.charAt(i+j))!=null){
                        //the text character is present in the BMT table
                        numOfSkips=this.badMatchTable.get(text.charAt(i+j));
                        break;
                    }else{
                        numOfSkips=lengthOfPattern;
                        break;
                    }
                }
            }
            if(numOfSkips==0){
                //found a match
                return i;
            }
        }
        return lengthOfText;//given pattern is not present in the text
    }

    public static void main(String[] args){
        String text="My Name is joe!";
        String pattern="Name";
    }
}
