package ac.nita.advaitam4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class UserMode extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static boolean FLAG = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        httpcallDownloadCounter();

        ImageView bgHeader = (ImageView)findViewById(R.id.bgheader);
        Glide.with(UserMode.this).load(R.drawable.advaitam_4_logo).into(bgHeader);

        sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Context context = this;

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setTitle("User");


        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context,R.color.colorPrimary));

        //CardView choiceOrganisr = (CardView)findViewById(R.id.organiser_cv);
        //CardView choiceParticipant = (CardView)findViewById(R.id.participant_cv);



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.participant){
                    editor.putBoolean("FLAG",false).apply();
                    editor.putString("user_mode","PARTICIPANT").commit();
                } else if(v.getId() == R.id.organiser){
                    editor.putBoolean("FLAG",true).apply();
                    editor.putString("user_mode","ORGANISER").commit();
                }

                Intent intent = new Intent(UserMode.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };

        Button participantButton = (Button)findViewById(R.id.participant);
        Button organiserButton = (Button)findViewById(R.id.organiser);

        participantButton.setOnClickListener(onClickListener);
        organiserButton.setOnClickListener(onClickListener);

        //choiceOrganisr.setOnClickListener(onClickListener);
        //choiceParticipant.setOnClickListener(onClickListener);

    }



    void httpcallDownloadCounter(){
       // try {
            SharedPreferences sharedPreferences = getSharedPreferences("FLAGS", MODE_PRIVATE);
            String isFirstTime = sharedPreferences.getString("is_first_time", "YES");
            if (isFirstTime.equals("YES")) {
                String url = "http://eduhub.springpebbles.in/advaitam/apis/counter/index.php";
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("is_first_time", "NO");
                editor.commit();
                new GetUrlContentTask().execute(url);
            }
       // } catch (IOException e){

        //}
    }
    private class GetUrlContentTask extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader rd = null;
            try {
                rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String content = "", line;
            try {
                while ((line = rd.readLine()) != null) {
                    content += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(String result) {
            // this is executed on the main thread after the process is over
            // update your UI here
        }
    }



}
