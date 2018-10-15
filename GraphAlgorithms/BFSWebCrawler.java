package GraphAlgorithms;



import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.URL;

import java.util.ArrayList;

import java.util.LinkedList;

import java.util.List;

import java.util.Queue;

import java.util.regex.Matcher;

import java.util.regex.Pattern;



public class BFSWebCrawler {

    private Queue<String> queue;

    private List<String> discoveredWebsiteList ;



    public BFSWebCrawler(){

        this.queue=new LinkedList<>();

        this.discoveredWebsiteList=new ArrayList<>();

    }



    public void discoverWeb(String root){

        this.queue.add(root);

        this.discoveredWebsiteList.add(root);



        while(!queue.isEmpty()){

            String v=this.queue.remove();

            String rawHtml=readURL(v);



            String regexp="http://(\\w+\\.)*(\\w+)";//we need to find only URLS in the raw source code

            Pattern pattern=Pattern.compile(regexp);

            Matcher matcher=pattern.matcher(rawHtml);



            while(matcher.find()){

                String actualUrl=matcher.group();//returns the url it has found in the raw data

                if(!discoveredWebsiteList.contains(actualUrl)){

                    discoveredWebsiteList.add(actualUrl);

                    System.out.println("Website has been found with url:"+actualUrl);

                    queue.add(actualUrl);

                }

            }



        }

    }



    public static void main(String[] args){

        BFSWebCrawler obj=new BFSWebCrawler();

        obj.discoverWeb("http://www.bbc.com");

    }



    private String readURL(String v) {



        //this will read the while source from the url

        String rawHtml="";

        try{

            URL url=new URL(v);

            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));



            String inputLine="";



            while((inputLine=br.readLine())!=null){

                rawHtml+=inputLine;

            }

            br.close();

        }catch (Exception e){

            e.printStackTrace();

        }

        return rawHtml;

    }

}
