package com.example.notimportant.kincheck.XmlParser;


import android.os.AsyncTask;
import android.util.Log;

import com.example.notimportant.kincheck.Activity.MainActivity;
import com.example.notimportant.kincheck.ShowTime;
import com.example.notimportant.kincheck.Theater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleAfishaParser {

    private String PAGE_URL = "https://www.google.ru/movies?near=rostov-on-don,&date=0&start=0";
    private ArrayList<Theater> theatersList;

    private String TAG_THEATER = "theater";
    private String TAG_DESC = "desc";
    private String TAG_ID = "id";
    private String TAG_NAME = "name";
    private String TAG_INFO = "info";
    private String TAG_MOVIE = "movie";

    public GoogleAfishaParser() {
        theatersList = new ArrayList<>();
        new Parsing().execute();
    }

    public ArrayList<Theater> getInfo(){
        return theatersList;
    }

    private class Parsing extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            Elements theaters;
            try {
                doc = Jsoup.connect(PAGE_URL).get();
                theaters = doc.getElementsByClass(TAG_THEATER);
                theatersList.clear();
                for (Element theaterEl : theaters) {
                    Element desc =  theaterEl.getElementsByClass(TAG_DESC).get(0);

                    Pattern tp = Pattern.compile("id=(.*)$");
                    Matcher tm = tp.matcher(desc.getElementsByClass(TAG_NAME).get(0).getElementsByTag("a").get(0).attr("href"));
                    tm.find();
                    String id = tm.group(1);

                    String name = desc.getElementsByClass(TAG_NAME).get(0).text();
                    String info = desc.getElementsByClass(TAG_INFO).get(0).text();

                    //DEV
                    Log.d("TEST", "\tTheater: " + name + "; ID: " + id);


                    Theater theater = new Theater(id, name, info, "");

                    Elements ShowTimesEl = theaterEl.getElementsByClass(TAG_MOVIE);
                    for(Element showTimeEL : ShowTimesEl){
                        String STname = showTimeEL.getElementsByClass(TAG_NAME).get(0).text();
                        String temp = showTimeEL.getElementsByClass(TAG_NAME).get(0).getElementsByTag("a").get(0).attr("href");;

                        Pattern p = Pattern.compile("id=(.*)$");
                        Matcher m = p.matcher(temp);
                        m.find();
                        String STid = m.group(1);
                        //DEV
                        Log.d("TEST", "\t\tMovie: " + STname + "; ID: " + STid);

                        Elements timesEL = showTimeEL.getElementsByClass("times").get(0).getElementsByAttributeValue("style", "color:");
                        String STtimes = "";
                        for(Element timeEL : timesEL){
                            STtimes += timeEL.text() + "\t";
                        }

                        if(!STtimes.isEmpty()){
                            ShowTime showTime = new ShowTime(STid, STname, STtimes);
                            theater.addShowTime(showTime);
                        }
                    }

                    theatersList.add(theater);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            MainActivity.render();
        }
    }
}
