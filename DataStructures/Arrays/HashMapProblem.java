package DataStructures.Arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HashMapProblem {
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        HashMap<String,Integer> wordMap=new HashMap<>();
        for(int i=0;i<n;i++){
            String str=br.readLine();
            if(wordMap.containsKey(str)){
                wordMap.put(str,wordMap.get(str)+1);
            }else{
            wordMap.put(str,1);}
        }
        int q=Integer.parseInt(br.readLine());
        while(--q>=0){
            String qrStr=br.readLine();
            if(wordMap.containsKey(qrStr)){
                System.out.println(wordMap.get(qrStr));
            }else{
                System.out.println(0);
            }
        }
    }
}
