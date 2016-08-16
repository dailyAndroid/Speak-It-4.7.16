package com.example.hwhong.speakit;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    int var;
    EditText rawtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rawtext = (EditText) findViewById(R.id.editText);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (textToSpeech.SUCCESS == i) {
                    var = textToSpeech.setLanguage(Locale.FRENCH);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Feature not Supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startOperation(View view) {
        switch (view.getId()) {
            case R.id.speak:
                if (var == TextToSpeech.LANG_NOT_SUPPORTED || var == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(getApplicationContext(),
                            "Feature not Supported", Toast.LENGTH_SHORT).show();
                } else {
                    String text = rawtext.getText().toString();
                    textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            case R.id.end:
                if (textToSpeech != null) {
                    textToSpeech.stop();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
