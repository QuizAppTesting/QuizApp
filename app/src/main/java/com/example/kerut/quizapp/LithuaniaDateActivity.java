package com.example.kerut.quizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vilius Kerutis on 25/10/2018.
 */

public class LithuaniaDateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lithuania_date);


        setKlausimus();

        Button baigti = findViewById(R.id.Baigti);
        baigti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int atsakytiTeisingai = 0;
                int neatsakyti = 0;
                List<Integer> nuokrypis = new ArrayList<Integer>();
                int kiekis = 0;

                List<String> data = new ArrayList<String>();
                User user = new User(getApplicationContext());
                data.add(0, user.getUsernameForLogin());

                try {

                    EditText pirmasAtsakymas = findViewById(R.id.PirmasAtsakymas);
                    if (pirmasAtsakymas.getText().toString().equals(getString(R.string.pirmasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (pirmasAtsakymas.getText().toString().isEmpty()) {
                        neatsakyti++;
                    } else {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(pirmasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.pirmasAtsakymas))));
                    }

                    EditText antrasAtsakymas = findViewById(R.id.AntrasAtsakymas);
                    if (antrasAtsakymas.getText().toString().equals(getString(R.string.antrasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!antrasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(antrasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.antrasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText thirdAnswer = findViewById(R.id.thirdAnswer);
                    if (thirdAnswer.getText().toString().equals(getString(R.string.thirdAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!thirdAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(thirdAnswer.getText().toString()) - Integer.parseInt(getString(R.string.thirdAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText ketvirtasAtsakymas = findViewById(R.id.KetvirtasAtsakymas);
                    if (ketvirtasAtsakymas.getText().toString().equals(getString(R.string.ketvirtasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!ketvirtasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(ketvirtasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.ketvirtasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText penktasAtsakymas = findViewById(R.id.PenktasAtsakymas);
                    if (penktasAtsakymas.getText().toString().equals(getString(R.string.penktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!penktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(penktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.penktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText sixthAnswer = findViewById(R.id.sixthAnswer);
                    if (sixthAnswer.getText().toString().equals(getString(R.string.sixthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!sixthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(sixthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.sixthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText septintasAtsakymas = findViewById(R.id.SeptintasAtsakymas);
                    if (septintasAtsakymas.getText().toString().equals(getString(R.string.septintasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!septintasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(septintasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.septintasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText eigthAnswer = findViewById(R.id.eigthAnswer);
                    if (eigthAnswer.getText().toString().equals(getString(R.string.eigthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!eigthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(eigthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.eigthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText devintasAtsakymas = findViewById(R.id.DevintasAtsakymas);
                    if (devintasAtsakymas.getText().toString().equals(getString(R.string.devintasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!devintasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(devintasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.devintasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText tenthAnswer = findViewById(R.id.tenthAnswer);
                    if (tenthAnswer.getText().toString().equals(getString(R.string.tenthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!tenthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(tenthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.tenthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText vienuoliktasAtsakymas = findViewById(R.id.VienuoliktasAtsakymas);
                    if (vienuoliktasAtsakymas.getText().toString().equals(getString(R.string.vienuoliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!vienuoliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(vienuoliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.vienuoliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText dvyliktasAtsakymas = findViewById(R.id.DvyliktasAtsakymas);
                    if (dvyliktasAtsakymas.getText().toString().equals(getString(R.string.dvyliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!dvyliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(dvyliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.dvyliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText tryliktasAtsakymas = findViewById(R.id.TryliktasAtsakymas);
                    if (tryliktasAtsakymas.getText().toString().equals(getString(R.string.tryliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!tryliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(tryliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.tryliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText keturioliktasAtsakymas = findViewById(R.id.KeturioliktasAtsakymas);
                    if (keturioliktasAtsakymas.getText().toString().equals(getString(R.string.keturioliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!keturioliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(keturioliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.keturioliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText penkioliktasAtsakymas = findViewById(R.id.PenkioliktasAtsakymas);
                    if (penkioliktasAtsakymas.getText().toString().equals(getString(R.string.penkioliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!penkioliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(penkioliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.penkioliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText sixteenthAnswer = findViewById(R.id.sixteenthAnswer);
                    if (sixteenthAnswer.getText().toString().equals(getString(R.string.sixteenthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!sixteenthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(sixteenthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.sixteenthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText septynioliktasAtsakymas = findViewById(R.id.SeptynioliktasAtsakymas);
                    if (septynioliktasAtsakymas.getText().toString().equals(getString(R.string.septynioliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!septynioliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(septynioliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.septynioliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText eighteenthAnswer = findViewById(R.id.eighteenthAnswer);
                    if (eighteenthAnswer.getText().toString().equals(getString(R.string.eighteenthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!eighteenthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(eighteenthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.eighteenthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText devynioliktasAtsakymas = findViewById(R.id.DevynioliktasAtsakymas);
                    if (devynioliktasAtsakymas.getText().toString().equals(getString(R.string.devynioliktasAtsakymas))) {
                        atsakytiTeisingai++;
                    } else if (!devynioliktasAtsakymas.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(devynioliktasAtsakymas.getText().toString()) - Integer.parseInt(getString(R.string.devynioliktasAtsakymas))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentythAnswer = findViewById(R.id.twentythAnswer);
                    if (twentythAnswer.getText().toString().equals(getString(R.string.twentythAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentythAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentythAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentythAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentyfirstAnswer = findViewById(R.id.twentyfirstAnswer);
                    if (twentyfirstAnswer.getText().toString().equals(getString(R.string.twentyfirstAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentyfirstAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentyfirstAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentyfirstAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentySecondAnswer = findViewById(R.id.twentySecondAnswer);
                    if (twentySecondAnswer.getText().toString().equals(getString(R.string.twentySecondAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentySecondAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentySecondAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentySecondAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentyThirdAnswer = findViewById(R.id.twentyThirdAnswer);
                    if (twentyThirdAnswer.getText().toString().equals(getString(R.string.twentyThirdAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentyThirdAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentyThirdAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentyThirdAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentyfourthAnswer = findViewById(R.id.twentyfourthAnswer);
                    if (twentyfourthAnswer.getText().toString().equals(getString(R.string.twentyfourthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentyfourthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentyfourthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentyfourthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentyFivethAnswer = findViewById(R.id.twentyFivethAnswer);
                    if (twentyFivethAnswer.getText().toString().equals(getString(R.string.twentyFivethAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentyFivethAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentyFivethAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentyFivethAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentySixthAnswer = findViewById(R.id.twentySixthAnswer);
                    if (twentySixthAnswer.getText().toString().equals(getString(R.string.twentySixthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentySixthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentySixthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentySixthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentySeventhAnswer = findViewById(R.id.twentySeventhAnswer);
                    if (twentySeventhAnswer.getText().toString().equals(getString(R.string.twentySeventhAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentySeventhAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentySeventhAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentySeventhAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentyEighthAnswer = findViewById(R.id.twentyEighthAnswer);
                    if (twentyEighthAnswer.getText().toString().equals(getString(R.string.twentyEighthAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentyEighthAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentyEighthAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentyEighthAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText twentyNinethAnswer = findViewById(R.id.twentyNinethAnswer);
                    if (twentyNinethAnswer.getText().toString().equals(getString(R.string.twentyNinethAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!twentyNinethAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(twentyNinethAnswer.getText().toString()) - Integer.parseInt(getString(R.string.twentyNinethAnswer))));
                    } else {
                        neatsakyti++;
                    }

                    EditText thirdythAnswer = findViewById(R.id.thirdythAnswer);
                    if (thirdythAnswer.getText().toString().equals(getString(R.string.thirdythAnswer))) {
                        atsakytiTeisingai++;
                    } else if (!thirdythAnswer.getText().toString().isEmpty()) {
                        nuokrypis.add(kiekis++, Math.abs(Integer.parseInt(thirdythAnswer.getText().toString()) - Integer.parseInt(getString(R.string.thirdythAnswer))));
                    } else {
                        neatsakyti++;
                    }
                } catch (Exception e) {
                    Toast.makeText(LithuaniaDateActivity.this, "Bad format of number, please try again", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LithuaniaDateActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

                data.add(1, "" + atsakytiTeisingai + "/30");

                setContentView(R.layout.project_test_ending);

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (Integer i : nuokrypis) {
                    if (i < min) {
                        min = i;
                    }
                    if (i > max) {
                        max = i;
                    }
                }

                if (min != 2147483647)
                    data.add(2, "" + min + "");
                else data.add(2, "-");

                if (max != -2147483648)
                    data.add(3, "" + max + "");
                else data.add(3, "-");

                data.add(4, "" + neatsakyti + "");
                System.out.println(data.get(2) + "\n" + data.get(3) + "\n" + data.get(4));
                System.out.println(data);
                addResultToDB(data);

                TextView textView = findViewById(R.id.Ivertis);
                NumberFormat formatter = new DecimalFormat("#0.00");

                if (atsakytiTeisingai < 15)
                    textView.setText("Deja.. Jūs surinkote vos: " + formatter.format(atsakytiTeisingai / 30D * 100D) + "%");
                else if (atsakytiTeisingai >= 15 && atsakytiTeisingai < 24)
                    textView.setText("Neblogai! Jūs surinkote: " + formatter.format(atsakytiTeisingai / 30D * 100D) + "%");
                else
                    textView.setText("Puikiai! Jūs surinkote: " + formatter.format(atsakytiTeisingai / 30D * 100D) + "%");

                TextView teisingi = findViewById(R.id.Teisingi);
                teisingi.setText(R.string.teisingi);

                TextView kiekteisingi = findViewById(R.id.keikTeisingi);
                kiekteisingi.setText(atsakytiTeisingai + "/" + 30);

                TextView nepasirinkta = findViewById(R.id.Neatsakyti);
                nepasirinkta.setText(R.string.neatsakyta);

                TextView kieknepasirinkta = findViewById(R.id.keikNeatsakyti);
                kieknepasirinkta.setText(neatsakyti + "/" + 30);

                TextView minNuokrypis = findViewById(R.id.minNuokrypis);
                minNuokrypis.setText(R.string.minNuokrypis);
                TextView koksminNuokrypis = findViewById(R.id.koksmin);

                if (min != Integer.MAX_VALUE) {
                    koksminNuokrypis.setText("" + min + "");
                } else koksminNuokrypis.setText("-");

                TextView maxNuokrypis = findViewById(R.id.maxNuokrypis);
                maxNuokrypis.setText(R.string.maxNuokrypis);
                TextView koksmaxNuokrypis = findViewById(R.id.koksmax);

                if (max != Integer.MIN_VALUE) {
                    koksmaxNuokrypis.setText("" + max + "");
                } else koksmaxNuokrypis.setText("-");

                Button getback = findViewById(R.id.ending_getback);
                getback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LithuaniaDateActivity.this, QuizActivity.class);
                        startActivity(intent);
                    }
                });

                Button chechtheanswers = findViewById(R.id.ending_checkanswers);
                chechtheanswers.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.check_the_answers);

                        TextView correct = findViewById(R.id.correctsentence);
                        correct.setText(getString(R.string.pirmasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.pirmasAtsakymas) +
                                "\n\n\n" + getString(R.string.antrasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.antrasAtsakymas) +
                                "\n\n\n" + getString(R.string.thirdQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.thirdAnswer) +
                                "\n\n\n" + getString(R.string.ketvirtasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.ketvirtasAtsakymas) +
                                "\n\n\n" + getString(R.string.penktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.penktasAtsakymas) +
                                "\n\n\n" + getString(R.string.sixthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.sixthAnswer) +
                                "\n\n\n" + getString(R.string.septintasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.septintasAtsakymas) +
                                "\n\n\n" + getString(R.string.eighthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.eigthAnswer) +
                                "\n\n\n" + getString(R.string.devintasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.devintasAtsakymas) +
                                "\n\n\n" + getString(R.string.tenthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.tenthAnswer) +
                                "\n\n\n" + getString(R.string.vienuoliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.vienuoliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.dvyliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.dvyliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.tryliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.tryliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.keturioliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.keturioliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.penkioliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.penkioliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.sixteenthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.sixteenthAnswer) +
                                "\n\n\n" + getString(R.string.septynioliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.septynioliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.eighteenthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.eighteenthAnswer) +
                                "\n\n\n" + getString(R.string.devynioliktasKlausimas) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.devynioliktasAtsakymas) +
                                "\n\n\n" + getString(R.string.twentythQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentythAnswer) +
                                "\n\n\n" + getString(R.string.twentyFirstQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentyfirstAnswer) +
                                "\n\n\n" + getString(R.string.twentySecondthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentySecondAnswer) +
                                "\n\n\n" + getString(R.string.twentythirdQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentyThirdAnswer) +
                                "\n\n\n" + getString(R.string.twentyFourthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentyfourthAnswer) +
                                "\n\n\n" + getString(R.string.twentyFivethQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentyFivethAnswer) +
                                "\n\n\n" + getString(R.string.twentySixthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentySixthAnswer) +
                                "\n\n\n" + getString(R.string.twentySeventhQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentySeventhAnswer) +
                                "\n\n\n" + getString(R.string.twentyEighthQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentyEighthAnswer) +
                                "\n\n\n" + getString(R.string.twentyNNinethQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.twentyNinethAnswer) +
                                "\n\n\n" + getString(R.string.thirdythQ) + "\n" + getString(R.string.Correct) + "  " + getString(R.string.thirdythAnswer));

                        Button getback = findViewById(R.id.ending_getback);
                        getback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent goToQuizActivity = new Intent(LithuaniaDateActivity.this, QuizActivity.class);
                                startActivity(goToQuizActivity);
                            }
                        });
                    }
                });
            }
        });


    }

    private void addResultToDB(final List<String> duom) {

        class addResult extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            DB database = new DB();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LithuaniaDateActivity.this, getString(R.string.Login_please_wait), null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("vartotojas", params[0]);
                data.put("kiekis", params[1]);
                data.put("pavadinimas", getText(R.string.project_quiz).toString());
                data.put("minnuokrypis", params[2]);
                data.put("maxnuokrypis", params[3]);
                data.put("neatsakyta", params[4]);

                String result = database.sendPostRequest(getString(R.string.URL_QuizDates), data);

                return result;
            }
        }
        addResult newResult = new addResult();
        newResult.execute(duom.get(0), duom.get(1), duom.get(2), duom.get(3), duom.get(4));
    }

    private void setKlausimus() {
        TextView pirmasklausimas = findViewById(R.id.PirmasKlausimas);
        pirmasklausimas.setText(R.string.pirmasKlausimas);

        TextView antrasklausimas = findViewById(R.id.AntrasKlausimas);
        antrasklausimas.setText(R.string.antrasKlausimas);

        TextView thirdQ = findViewById(R.id.thirdQ);
        thirdQ.setText(R.string.thirdQ);

        TextView ketvirtasklausimas = findViewById(R.id.KetvirtasKlausimas);
        ketvirtasklausimas.setText(R.string.ketvirtasKlausimas);

        TextView penktasklausimas = findViewById(R.id.PenktasKlausimas);
        penktasklausimas.setText(R.string.penktasKlausimas);

        TextView sixthQ = findViewById(R.id.sixthQ);
        sixthQ.setText(R.string.sixthQ);

        TextView septintasklausimas = findViewById(R.id.SeptintasKlausimas);
        septintasklausimas.setText(R.string.septintasKlausimas);

        TextView eigthQ = findViewById(R.id.eightQ);
        eigthQ.setText(R.string.eighthQ);

        TextView devintasklausimas = findViewById(R.id.DevintasKlausimas);
        devintasklausimas.setText(R.string.devintasKlausimas);

        TextView tenthQ = findViewById(R.id.tenthQ);
        tenthQ.setText(R.string.tenthQ);

        TextView vienuoliktasklausimas = findViewById(R.id.VienuoliktasKlausimas);
        vienuoliktasklausimas.setText(R.string.vienuoliktasKlausimas);

        TextView dvyliktasklausimas = findViewById(R.id.DvyliktasKlausimas);
        dvyliktasklausimas.setText(R.string.dvyliktasKlausimas);

        TextView tryliktasklausimas = findViewById(R.id.TryliktasKlausimas);
        tryliktasklausimas.setText(R.string.tryliktasKlausimas);

        TextView keturioliktasklausimas = findViewById(R.id.KeturioliktasKlausimas);
        keturioliktasklausimas.setText(R.string.keturioliktasKlausimas);

        TextView penkioliktasklausimas = findViewById(R.id.PenkioliktasKlausimas);
        penkioliktasklausimas.setText(R.string.penkioliktasKlausimas);

        TextView sixteenthQ = findViewById(R.id.sixteenthQ);
        sixteenthQ.setText(R.string.sixteenthQ);

        TextView septynioliktasklausimas = findViewById(R.id.SeptynioliktasKlausimas);
        septynioliktasklausimas.setText(R.string.septynioliktasKlausimas);

        TextView eighteenthQ = findViewById(R.id.eighteenthQ);
        eighteenthQ.setText(R.string.eighteenthQ);

        TextView devynioliktasklausimas = findViewById(R.id.DevynioliktasKlausimas);
        devynioliktasklausimas.setText(R.string.devynioliktasKlausimas);

        TextView dvidesimtasklausimas = findViewById(R.id.DvidesimtasKlausimas);
        dvidesimtasklausimas.setText(R.string.twentythQ);

        TextView dvidesimtpirmasklausimas = findViewById(R.id.DvidesimtpirmasKlausimas);
        dvidesimtpirmasklausimas.setText(R.string.twentyFirstQ);

        TextView dvidesimtantrasklausimas = findViewById(R.id.DvidesimtantrasKlausimas);
        dvidesimtantrasklausimas.setText(R.string.twentySecondthQ);

        TextView twentyThirdQ = findViewById(R.id.twentyThirdQ);
        twentyThirdQ.setText(R.string.twentythirdQ);

        TextView dvidesimtketvirtasklausimas = findViewById(R.id.DvidesimtketvirtasKlausimas);
        dvidesimtketvirtasklausimas.setText(R.string.twentyFourthQ);

        TextView dvidesimtpenktasklausimas = findViewById(R.id.DvidesimtpenktasKlausimas);
        dvidesimtpenktasklausimas.setText(R.string.twentyFivethQ);

        TextView dvidesimtsestasklausimas = findViewById(R.id.DvidesimtsestasKlausimas);
        dvidesimtsestasklausimas.setText(R.string.twentySixthQ);

        TextView dvidesimtseptintasklausimas = findViewById(R.id.DvidesimtseptintasKlausimas);
        dvidesimtseptintasklausimas.setText(R.string.twentySeventhQ);

        TextView dvidesimtastuntasklausimas = findViewById(R.id.DvidesimtastuntasKlausimas);
        dvidesimtastuntasklausimas.setText(R.string.twentyEighthQ);

        TextView dvidesimtdevintasklausimas = findViewById(R.id.DvidesimtdevintasKlausimas);
        dvidesimtdevintasklausimas.setText(R.string.twentyNNinethQ);

        TextView trisdesimtasklausimas = findViewById(R.id.TrisdesimtasKlausimas);
        trisdesimtasklausimas.setText(R.string.thirdythQ);
    }
}
