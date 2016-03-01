package com.example.notimportant.kincheck.Activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.notimportant.kincheck.R;
import com.example.notimportant.kincheck.ShowTime;
import com.example.notimportant.kincheck.Theater;
import com.example.notimportant.kincheck.XmlParser.GoogleAfishaParser;

import java.util.ArrayList;


public class MainActivity extends Activity{

    public static ListView lv;
    public static ArrayList<Theater> tl;
    public static Context context;
    public static GoogleAfishaParser gap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        context = getApplicationContext();

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        for (int i = 0; i < info.length; i++)
            if (info[i].getState() == NetworkInfo.State.CONNECTED)
            {
                lv = (ListView) findViewById(R.id.listView);
                gap = new GoogleAfishaParser();
                break;
            }
    }

    public static void render(){
        tl = gap.getInfo();
        ArrayList<String> data = new ArrayList<String>();
        for(Theater t : tl){
            String tv = t.getName() + "\n";
            for(ShowTime st : t.getShowTimes()){
                if(!st.getTimes().isEmpty()) {
                    tv+="\t\t\t\t" + st.getName() + "\n";
                    tv+="\t\t\t\t\t\t\t\t" + st.getTimes() + "\n";
                }
            }
            data.add(tv);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
    }

}
