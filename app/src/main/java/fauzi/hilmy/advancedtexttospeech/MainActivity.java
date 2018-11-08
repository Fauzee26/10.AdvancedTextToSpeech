package fauzi.hilmy.advancedtexttospeech;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fauzi.hilmy.advancedtexttospeech.language.Language;
import fauzi.hilmy.advancedtexttospeech.language.LanguageData;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spinLanguage)
    Spinner spinLanguage;
    @BindView(R.id.btnHear)
    Button btnHear;
    @BindView(R.id.edtWord)
    TextInputEditText edtWord;

    private MediaPlayer mp = null;
    private ArrayList<Language> arrayList;
    private ArraySpinner adapter;

    private String baseUrl = "http://api.voicerss.org/?key=3c97229df6be4e909a24059458ee2d86&hl=";
    private String nextUrl = "&src=";
    private String languageSelected, sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        arrayList = new ArrayList<>();
        arrayList.addAll(LanguageData.getLanguagee());

        adapter = new ArraySpinner(MainActivity.this, android.R.layout.simple_spinner_item, arrayList);
        spinLanguage.setAdapter(adapter);
        spinLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Language language = adapter.getItem(i);
                languageSelected = language.languageCode;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnHear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentence = edtWord.getText().toString();
                if (!sentence.isEmpty()) {
                    try
                    {
                        final MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener()
                        {
                            @Override
                            public void onPrepared(MediaPlayer mp)
                            {
                                mp.setVolume(1, 1);
                                mp.start();
                            }
                        };

                        final MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener()
                        {
                            @Override
                            public boolean onError(MediaPlayer mp, int what, int extra)
                            {
                                return false;
                            }
                        };

                        final MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener()
                        {
                            @Override
                            public void onCompletion(MediaPlayer mp)
                            {
                                mp.release();
                                mp = null;
                            }
                        };

                        String url = buildSpeechUrl(sentence, languageSelected);

                        AudioManager audioManager = (AudioManager)getSystemService(MainActivity.AUDIO_SERVICE);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

                        try
                        {
                            if (mp != null)
                                mp.release();

                            mp = new MediaPlayer();
                            mp.setDataSource(url);
                            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mp.setOnErrorListener(onErrorListener);
                            mp.setOnCompletionListener(onCompletionListener);
                            mp.setOnPreparedListener(onPreparedListener);
                            mp.prepareAsync();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Build speech URL.
     */
    private String buildSpeechUrl(String words, String language) {
        String url = "";

        url = baseUrl + language + nextUrl + words;

        return url;
    }
}
