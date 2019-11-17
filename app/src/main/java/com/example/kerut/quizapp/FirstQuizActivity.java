package com.example.kerut.quizapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.kerut.quizapp.QuizActivity.CONNECTION_TIMEOUT;
import static com.example.kerut.quizapp.QuizActivity.READ_TIMEOUT;

/**
 * Created by Vilius Kerutis on 24/10/2018.
 */

public class FirstQuizActivity extends AppCompatActivity {

    ArrayList<HashMap> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getQuizData getQuizData = new getQuizData("");
        getQuizData.execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_quiz);



        final RadioGroup nato = findViewById(R.id.Nato_RadioButton);
        final RadioButton natoTeisingas = findViewById(R.id.NATO_2004);
        natoTeisingas.setText(getText(R.string.on2004));

        final RadioGroup karalius = findViewById(R.id.Karalius_RadioButton);
        final RadioButton karaliusTeisingas = findViewById(R.id.Karalius_Mindaugas);
        karaliusTeisingas.setText(getText(R.string.Mindaugas));

        final RadioGroup butiarnebuti = findViewById(R.id.Hamletas_RadioButton);
        final RadioButton butiarnebutiTeisingas = findViewById(R.id.Hamletas);
        butiarnebutiTeisingas.setText(getText(R.string.Hamletas));

        final RadioGroup lygumos = findViewById(R.id.Lygumos_RadioButton);
        final RadioButton lygumosTeisingas = findViewById(R.id.o75);
        lygumosTeisingas.setText(getText(R.string.o75));

        final RadioGroup centras = findViewById(R.id.Centras_RadioButton);
        final RadioButton centrasTeisingas = findViewById(R.id.o1989);
        centrasTeisingas.setText(getText(R.string.o1989));

        final RadioGroup paper = findViewById(R.id.Paper_RadioButton);
        final RadioButton paperTeisingas = findViewById(R.id.newyorktimes);
        paperTeisingas.setText(getText(R.string.newYorkTimes));

        final RadioGroup olimp = findViewById(R.id.Olimp_RadioButton);
        final RadioButton olimpTeisingas = findViewById(R.id.o1924);
        olimpTeisingas.setText(getText(R.string.o1924));

        final RadioGroup internet = findViewById(R.id.Internet_RadioButton);
        final RadioButton internetTeisingas = findViewById(R.id.o45);
        internetTeisingas.setText(getText(R.string.o45));

        final RadioGroup taxes = findViewById(R.id.Taxes_RadioButton);
        final RadioButton taxesTeisingas = findViewById(R.id.x3);
        taxesTeisingas.setText(getText(R.string.x3));

        final RadioGroup duona = findViewById(R.id.Duona_RadioButton);
        final RadioButton duonaTeisingas = findViewById(R.id.o110kg);
        duonaTeisingas.setText(getText(R.string.o110kg));

        Button endQuiz = findViewById(R.id.firstquiz_endnow);
        endQuiz.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                List<String> data = new ArrayList<>();
                User user = new User(getApplicationContext());
                data.add(0, user.getUsernameForLogin());

                int kiekis = 0;

                final RadioButton[] natoPasirinktas = new RadioButton[1];
                natoPasirinktas[0] = findViewById(nato.getCheckedRadioButtonId());
                if (natoTeisingas.getText().toString().equals(natoPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] karaliusPasirinktas = new RadioButton[1];
                karaliusPasirinktas[0] = findViewById(karalius.getCheckedRadioButtonId());
                if (karaliusTeisingas.getText().toString().equals(karaliusPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] butiarnebutiPasirinktas = new RadioButton[1];
                butiarnebutiPasirinktas[0] = findViewById(butiarnebuti.getCheckedRadioButtonId());
                if (butiarnebutiTeisingas.getText().toString().equals(butiarnebutiPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] lygumosPasirinktas = new RadioButton[1];
                lygumosPasirinktas[0] = findViewById(lygumos.getCheckedRadioButtonId());
                if (lygumosTeisingas.getText().toString().equals(lygumosPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] centrasPasirinktas = new RadioButton[1];
                centrasPasirinktas[0] = findViewById(centras.getCheckedRadioButtonId());
                if (centrasTeisingas.getText().toString().equals(centrasPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] paperPasirinktas = new RadioButton[1];
                paperPasirinktas[0] = findViewById(paper.getCheckedRadioButtonId());
                if (paperTeisingas.getText().toString().equals(paperPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] olimpPasirinktas = new RadioButton[1];
                olimpPasirinktas[0] = findViewById(olimp.getCheckedRadioButtonId());
                if (olimpTeisingas.getText().toString().equals(olimpPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] internetPasirinktas = new RadioButton[1];
                internetPasirinktas[0] = findViewById(internet.getCheckedRadioButtonId());
                if (internetTeisingas.getText().toString().equals(internetPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] taxesPasirinktas = new RadioButton[1];
                taxesPasirinktas[0] = findViewById(taxes.getCheckedRadioButtonId());
                if (taxesTeisingas.getText().toString().equals(taxesPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                final RadioButton[] duonaPasirinktas = new RadioButton[1];
                duonaPasirinktas[0] = findViewById(duona.getCheckedRadioButtonId());
                if (duonaTeisingas.getText().toString().equals(duonaPasirinktas[0].getText().toString())) {
                    kiekis++;
                }

                String kiek = "" + kiekis + "/10";
                data.add(1, kiek);
                addResultToDB(data);
                setContentView(R.layout.test_ending);


                TextView textView = findViewById(R.id.endingsentence);
                if (kiekis < 5)
                    textView.setText("Deja.. Jūs surinkote vos: " + kiekis * 10 + "%");
                else if (kiekis >= 5 && kiekis < 9)
                    textView.setText("Neblogai! Jūs surinkote: " + kiekis * 10 + "%");
                else textView.setText("Puikiai! Jūs surinkote: " + kiekis * 10 + "%");

                Button button = findViewById(R.id.ending_checkanswers);
                button.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {

                        setContentView(R.layout.check_the_answers);

                        TextView correct = findViewById(R.id.correctsentence);
                        correct.setText(getString(R.string.Nato) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.on2004) +
                                "\n\n\n" + getString(R.string.Karalius) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.Mindaugas) +
                                "\n\n\n" + getString(R.string.Beornot) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.Hamletas) +
                                "\n\n\n" + getString(R.string.Lygumos) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.o75) +
                                "\n\n\n" + getString(R.string.Centras) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.o1989) +
                                "\n\n\n" + getString(R.string.Paper) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.newYorkTimes) +
                                "\n\n\n" + getString(R.string.Olimp) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.o1924) +
                                "\n\n\n" + getString(R.string.Internet) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.o45) +
                                "\n\n\n" + getString(R.string.Taxes) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.x3) +
                                "\n\n\n" + getString(R.string.Duona) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.o110kg));

                        Button getback = findViewById(R.id.ending_getback);
                        getback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent goToQuizActivity = new Intent(FirstQuizActivity.this, QuizActivity.class);
                                startActivity(goToQuizActivity);
                            }
                        });
                    }
                });

                Button getback = findViewById(R.id.ending_getback);
                getback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToQuizActivity = new Intent(FirstQuizActivity.this, QuizActivity.class);
                        startActivity(goToQuizActivity);
                    }
                });
            }
        });
    }

    private class getQuizData extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(FirstQuizActivity.this);
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;

        public getQuizData(String searchQuery) {
            this.searchQuery = searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(
                        getString(R.string.URL_LithuanianData));

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                conn.setRequestProperty("Cookie", "__test=3b12ae2fea971a678179f5f43b9020b2; expires=Friday, January 1, 2038 at 1:55:55 AM; path=/");

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // add parameter to our above url
                User user = new User(getApplicationContext());
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery).appendQueryParameter("username", user.getUsernameForLogin());
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                //writer.write(getPostDataString(data));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return ("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);
            //this method will be running on UI thread
            pdLoading.dismiss();

            try {
                JSONArray jArray = new JSONArray(result);


                //Extract data from json and store int ArrayList as class objects
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", json_data.getString("id"));
                    hashMap.put("question", json_data.getString("question"));
                    hashMap.put("var1", json_data.getString("var1"));
                    hashMap.put("var2", json_data.getString("var2"));
                    hashMap.put("var3", json_data.getString("var3"));
                    hashMap.put("var4", json_data.getString("var4"));

                    list.add(hashMap);
                }




            } catch (JSONException e)

            {
                // You to understand what actually error is and handle it appropriately
                Toast.makeText(FirstQuizActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(FirstQuizActivity.this, result, Toast.LENGTH_LONG).show();
            }

        }

    }



    private void addResultToDB(final List<String> duom) {

        class addResult extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FirstQuizActivity.this, getString(R.string.Login_please_wait), null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<>();
                data.put("vartotojas", params[0]);
                data.put("kiekis", params[1]);
                data.put("pavadinimas", getText(R.string.test_quiz).toString());

                String result = database.sendPostRequest(getString(R.string.URL_QuizLithuania), data);

                return result;
            }
        }
        addResult newResult = new addResult();
        newResult.execute(duom.get(0), duom.get(1));
    }
}
