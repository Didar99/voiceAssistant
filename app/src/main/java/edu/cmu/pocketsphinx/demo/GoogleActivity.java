package edu.cmu.pocketsphinx.demo;

import static edu.cmu.pocketsphinx.demo.commands.air_power_off;
import static edu.cmu.pocketsphinx.demo.commands.air_power_on;
import static edu.cmu.pocketsphinx.demo.commands.curtain_down;
import static edu.cmu.pocketsphinx.demo.commands.curtain_stop;
import static edu.cmu.pocketsphinx.demo.commands.curtain_up;
import static edu.cmu.pocketsphinx.demo.commands.fingerPrintAfterLock;
import static edu.cmu.pocketsphinx.demo.commands.light_off;
import static edu.cmu.pocketsphinx.demo.commands.light_on;
import static edu.cmu.pocketsphinx.demo.commands.tv_power;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class GoogleActivity extends AppCompatActivity implements
        RecognitionListener {

    private static final int PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private TextView returnedText;
    private TextView returnedError;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "GoogleActivity";
    private boolean mainKeyWord, keyWord = false;

    private Button button;
    private void resetSpeechRecognizer() {

        if(speech != null)
            speech.destroy();
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        Log.i(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this));
        if(SpeechRecognizer.isRecognitionAvailable(this))
            speech.setRecognitionListener(this);
        else
            finish();
    }

    private void setRecogniserIntent() {

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);

        // UI initialisation
        returnedText = findViewById(R.id.textView1);
        returnedError = findViewById(R.id.errorView1);
        progressBar =  findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // start speech recogniser
        resetSpeechRecognizer();

        // start progress bar
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);

        // check for permission
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RECORD_AUDIO);
            return;
        }

        setRecogniserIntent();

        speech.startListening(recognizerIntent);
    }


    @Override
    public void onResume() {
        Log.i(LOG_TAG, "resume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(LOG_TAG, "pause");
        super.onPause();
        if(speech != null) {
            speech.destroy();
        }
    }


    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        speech = null;
        finish();
        startActivity(new Intent(getApplicationContext(), PocketSphinxActivity.class));
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";

//        returnedText.setText(text);
        ///////////////////////////////////
        Log.e("COMMAND", "MATCHES => " + matches);


        String[] namesArr = matches.toArray(new String[matches.size()]);

        for (int i = 0; i <= namesArr.length-1; i++) {
            if ("аха".equals(namesArr[i]) || "ахал".equals(namesArr[i]) || "ха-ха".equals(namesArr[i]) || "ха-ха-ха".equals(namesArr[i]) || "а ха".equals(namesArr[i]) || "охо".equals(namesArr[i]) || "ага".equals(namesArr[i]) || "ах".equals(namesArr[i])) {
                Toast.makeText(getApplicationContext(), "Ahal", Toast.LENGTH_SHORT).show();
                break;
            } else if ("включи свет".equals(namesArr[i]) || "ранее up".equals(namesArr[i]) || "джанни".equals(namesArr[i]) || "вчера мир".equals(namesArr[i]) || "тирания".equals(namesArr[i]) || "вчера нияк".equals(namesArr[i]) || "Terraria".equals(namesArr[i]) || "шрам як".equals(namesArr[i]) || "sram як".equals(namesArr[i]) || "сраный ясно".equals(namesArr[i]) || "вчера не я".equals(namesArr[i]) || "вчера он и я".equals(namesArr[i]) || "черный яр".equals(namesArr[i]) || "срань ок".equals(namesArr[i]) || "хранят".equals(namesArr[i]) || "сравнять".equals(namesArr[i]) || "санёк".equals(namesArr[i]) || "сырая".equals(namesArr[i]) || "санек".equals(namesArr[i]) || "черный об".equals(namesArr[i]) || "черный up".equals(namesArr[i]) || "странник".equals(namesArr[i]) || "черный апк".equals(namesArr[i]) || "вчера на пк".equals(namesArr[i]) || "сраный от".equals(namesArr[i]) || " сраный арт".equals(namesArr[i]) || "страна 1".equals(namesArr[i]) || "страна ад".equals(namesArr[i]) || "страна от".equals(namesArr[i]) || "страны ад".equals(namesArr[i]) || "страны от".equals(namesArr[i]) || "страны атс".equals(namesArr[i]) || "страны оз".equals(namesArr[i]) || "страна йорк".equals(namesArr[i]) || "страны йорк".equals(namesArr[i]) || "вчера она".equals(namesArr[i]) || "страна Ош".equals(namesArr[i]) || "Страна Оз".equals(namesArr[i]) || "Сравни Яг".equals(namesArr[i]) || "странный АГ".equals(namesArr[i]) || "сравнить ок".equals(namesArr[i]) || "сранный явка".equals(namesArr[i]) || "Сравни ярка".equals(namesArr[i]) || "сранный йог".equals(namesArr[i]) || "сранный Йорк".equals(namesArr[i]) || "шрамы Йорк".equals(namesArr[i]) || "страны ок".equals(namesArr[i]) || "вчера не як".equals(namesArr[i]) || "терраны яркость".equals(namesArr[i]) || "шрамы явка".equals(namesArr[i]) || "шрамы Яг".equals(namesArr[i]) || "тираны як".equals(namesArr[i]) || "вчера на як".equals(namesArr[i]) || "сраный я".equals(namesArr[i]) || "сранный ок".equals(namesArr[i]) || "сраный ак".equals(namesArr[i]) ||"сраный ок".equals(namesArr[i]) ||"сранный як".equals(namesArr[i]) || "сразу як".equals(namesArr[i]) || "срань як".equals(namesArr[i]) || "сраная".equals(namesArr[i]) || "сранный Яг".equals(namesArr[i]) || "сранный я".equals(namesArr[i])) {
                Log.e("COMMAND", "LIGHT ON");
                light_on(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Çyra ýandy", Toast.LENGTH_SHORT).show();
                keyWord = false;
                break;

            } else if ("выключи свет".equals(namesArr[i]) || "что ночи".equals(namesArr[i]) || "вчера а вечером".equals(namesArr[i]) || "вчера вечером".equals(namesArr[i]) || "сауны вечера".equals(namesArr[i]) || "сраный вечера".equals(namesArr[i]) || "вчера на что".equals(namesArr[i]) || "радар".equals(namesArr[i]) || "дурачок".equals(namesArr[i]) || "снова ночь".equals(namesArr[i]) || "вчера ночи".equals(namesArr[i]) || "вчера не хочу".equals(namesArr[i]) || "страницы".equals(namesArr[i]) || "страны цифр".equals(namesArr[i]) || "страница".equals(namesArr[i]) || "зря ночи".equals(namesArr[i]) || "сара ночи".equals(namesArr[i]) || "саранча".equals(namesArr[i]) || "сырая ночи".equals(namesArr[i]) || "серая ночи".equals(namesArr[i]) || "вчера новичок".equals(namesArr[i]) || "сраный вечер".equals(namesArr[i]) ||  "сраный актер".equals(namesArr[i]) ||  "среда вечер".equals(namesArr[i]) || "вчера не ищешь".equals(namesArr[i]) || "сура ан вечер".equals(namesArr[i]) || "сраный концерт".equals(namesArr[i]) || "ceramica".equals(namesArr[i]) || "дз runner".equals(namesArr[i]) || "трансфер".equals(namesArr[i]) || "сразу surf".equals(namesArr[i]) || "сразу сер".equals(namesArr[i]) || "страны surf".equals(namesArr[i]) || "сраная surf".equals(namesArr[i]) || "страна зачет".equals(namesArr[i]) || "сразу счет".equals(namesArr[i]) || "страны еще".equals(namesArr[i]) || "страна еще".equals(namesArr[i]) || "вчера нет".equals(namesArr[i]) || "sravni.ru".equals(namesArr[i]) || "сраное вчера".equals(namesArr[i]) || "сраных вечер".equals(namesArr[i]) || "сранный крючок".equals(namesArr[i]) || "вчерашний вечер".equals(namesArr[i]) || "сраный зачет".equals(namesArr[i]) || "сраный дача".equals(namesArr[i]) || "страны кучер".equals(namesArr[i]) || "сраный десерт".equals(namesArr[i]) || "срань вечер".equals(namesArr[i]) || "сразу черный".equals(namesArr[i]) || "страны учет".equals(namesArr[i]) || "сраный picture".equals(namesArr[i]) || "странный вечер".equals(namesArr[i]) || "сранный вечер".equals(namesArr[i]) || "вчера на вечер".equals(namesArr[i]) || "сранный couture".equals(namesArr[i]) || "сранный ветер".equals(namesArr[i]) || "сравнить вечер".equals(namesArr[i]) || "вчера не вечер".equals(namesArr[i]) || "шрамы вечер".equals(namesArr[i]) || "страны вечер".equals(namesArr[i]) || "вчера вечер".equals(namesArr[i]) || "сраный surf".equals(namesArr[i]) || "сравнить".equals(namesArr[i])) {
                Log.e("COMMAND", "LIGHT OFF");
                light_off(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Çyra öçürildi", Toast.LENGTH_SHORT).show();
                break;

            } else if ("включить кондиционер".equals(namesArr[i]) || "отцом конце мир".equals(namesArr[i]) || "add кондиционер".equals(namesArr[i]) || "отца кондиционер".equals(namesArr[i]) || "асу канцлер".equals(namesArr[i]) || "а что кондиционер".equals(namesArr[i]) || "ка смерти".equals(namesArr[i]) || "канцелярия адрес".equals(namesArr[i]) || "кондиционер Оазис".equals(namesArr[i]) || "казань 1".equals(namesArr[i]) || "кондиционер акцент".equals(namesArr[i]) || "канцлер отцом".equals(namesArr[i]) || "качканар отца".equals(namesArr[i]) || "казань орать".equals(namesArr[i]) || "карта мира".equals(namesArr[i]) || "красный яр".equals(namesArr[i]) || "канцелярия".equals(namesArr[i]) || "конце мэрия".equals(namesArr[i]) || "контейнер от".equals(namesArr[i]) || "канцлер от".equals(namesArr[i]) || "концами 1".equals(namesArr[i]) || "кондиционер асель".equals(namesArr[i]) || "кондиционер азс".equals(namesArr[i]) || "кондиционер acer".equals(namesArr[i]) || "кондиционеры азс".equals(namesArr[i]) || "кондиционер asus".equals(namesArr[i]) || "кондиционеры орск".equals(namesArr[i]) || "кондиционеры арск".equals(namesArr[i]) || "конца не ария".equals(namesArr[i]) || "конца нарик".equals(namesArr[i]) || "канцлер отчёт".equals(namesArr[i]) || "контролер отчет".equals(namesArr[i]) || "канцлер отчет".equals(namesArr[i]) || "канцлер акцент".equals(namesArr[i]) || "конца не акцент".equals(namesArr[i]) || "консоль эта цель".equals(namesArr[i]) || "концерн эта цель".equals(namesArr[i]) || "контейнер йорк".equals(namesArr[i]) || "контейнер як".equals(namesArr[i]) || "контейнеры ок".equals(namesArr[i]) || "как цены ария".equals(namesArr[i]) || "канцелярия кг".equals(namesArr[i]) || "конца не раз".equals(namesArr[i]) || "канцелярия ок".equals(namesArr[i]) || "канцелярия как".equals(namesArr[i]) || "конца не рак".equals(namesArr[i]) || "антонио рак".equals(namesArr[i]) || "конце не отчет".equals(namesArr[i]) || "конце на 1".equals(namesArr[i]) || "концерн я рад".equals(namesArr[i]) || "конца не отца".equals(namesArr[i]) || "станционный 1".equals(namesArr[i]) || "кондиционер отчет".equals(namesArr[i]) || "кондиционер актер".equals(namesArr[i]) || "конце наряд".equals(namesArr[i]) || "костанай 1".equals(namesArr[i]) || "станционная 1".equals(namesArr[i]) || "конца наряд".equals(namesArr[i]) || "конца не рада".equals(namesArr[i]) || "конца не рода".equals(namesArr[i]) || "танцы на 1".equals(namesArr[i]) || "цена 1".equals(namesArr[i]) || "компания 1".equals(namesArr[i]) || "концы не раз".equals(namesArr[i]) || "контейнер h".equals(namesArr[i]) || "концы не rush".equals(namesArr[i]) || "как цене rush".equals(namesArr[i]) || "как цены 1".equals(namesArr[i]) || "как соня rush".equals(namesArr[i]) || "как заняться".equals(namesArr[i]) || "канцелярия 1".equals(namesArr[i]) || "конце мираж".equals(namesArr[i]) || "конца не рад".equals(namesArr[i]) || "концами рад".equals(namesArr[i]) || "конце не рад".equals(namesArr[i]) || "кондиционера цена".equals(namesArr[i]) || "кондиционер ABS".equals(namesArr[i]) || "кондиционер АЦЦ".equals(namesArr[i]) || "кондиционер АС".equals(namesArr[i]) || "кондиционер акция".equals(namesArr[i]) || "кондиционер Аксу".equals(namesArr[i]) || "кондиционеры AUX".equals(namesArr[i]) || "кондиционеры от".equals(namesArr[i]) || "контейнер АЦЦ".equals(namesArr[i]) || "контейнеры H".equals(namesArr[i]) || "кондиционеры H".equals(namesArr[i]) || "кондиционер adg".equals(namesArr[i]) || "канцелярия Age".equals(namesArr[i]) || "кондиционеры аж".equals(namesArr[i]) || "кондиционеры Ош".equals(namesArr[i]) || "кондиционер очень".equals(namesArr[i]) || "канцлер ашан".equals(namesArr[i]) || "консоли 1".equals(namesArr[i]) || "кондиционер 1".equals(namesArr[i]) || "кондиционер як".equals(namesArr[i]) || "кондиционер йорк".equals(namesArr[i]) || "канцлер от цель".equals(namesArr[i]) || "концерт цель".equals(namesArr[i]) || "кондиционер от цен".equals(namesArr[i]) || "канцлер отель".equals(namesArr[i]) || "контейнер отцу".equals(namesArr[i]) || "канцлер отце".equals(namesArr[i]) || "контейнер отца".equals(namesArr[i]) || "кондиционера цены".equals(namesArr[i]) || "кондиционер а центр".equals(namesArr[i]) || "кондиционера центр".equals(namesArr[i]) || "кондиционеров Сургут".equals(namesArr[i]) || "кондиционер Абсолют".equals(namesArr[i]) || "кондиционер отцов".equals(namesArr[i]) || "кондиционер отца".equals(namesArr[i]) || "кондиционер овца".equals(namesArr[i]) || "кондиционер отцу".equals(namesArr[i]) || "кондиционер отцом".equals(namesArr[i]) || "кондиционер Ассоль".equals(namesArr[i]) || "кондиционер Opel".equals(namesArr[i]) || "кондиционер artel".equals(namesArr[i]) || "кондиционер отшил".equals(namesArr[i]) || "кондиционер отель".equals(namesArr[i]) || "кондиционер оцелот".equals(namesArr[i]) || "кондиционер отшила".equals(namesArr[i]) || "кондиционер отзывы".equals(namesArr[i]) || "кондиционер отсюда".equals(namesArr[i])) {
                Log.e("COMMAND", "CONDITIONER ON");
                air_power_on(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Kondisioner işledildi", Toast.LENGTH_SHORT).show();

            } else if ("я поля кондиционер".equals(namesArr[i]) || "я тоже кондиционер".equals(namesArr[i]) || "я пол кондиционер".equals(namesArr[i]) || "выключи кондиционер".equals(namesArr[i]) || "конца не врачу".equals(namesArr[i]) || "станционная речи".equals(namesArr[i]) || "станционная врачу".equals(namesArr[i]) || "антонио вечер".equals(namesArr[i]) || "конца наречие".equals(namesArr[i]) || "конца не речи".equals(namesArr[i]) || "сказали вечер".equals(namesArr[i]) || "как дилеры surf".equals(namesArr[i]) || "sony речи".equals(namesArr[i]) || "казань арчи".equals(namesArr[i]) || "консоли врачу".equals(namesArr[i]) || "конца не ори вечер".equals(namesArr[i]) || "консоли вечер".equals(namesArr[i]) || "казань арена черт".equals(namesArr[i]) || "канцелярия чё".equals(namesArr[i]) || "каждая речи".equals(namesArr[i]) || "казань ярче".equals(namesArr[i]) || "концерт цирк".equals(namesArr[i]) || "казни вечер".equals(namesArr[i]) || "казни роджер".equals(namesArr[i]) || "концы мир acer".equals(namesArr[i]) || "канцлер учу".equals(namesArr[i]) || "концентрат surf".equals(namesArr[i]) || "контейнер очерк".equals(namesArr[i]) || "контейнер учи".equals(namesArr[i]) || "консоли речи".equals(namesArr[i]) || "контейнер актер".equals(namesArr[i]) || "казань оратор".equals(namesArr[i]) || "казань врачу".equals(namesArr[i]) || "концами роджер".equals(namesArr[i]) || "концами ярче".equals(namesArr[i]) || "конца меры вечер".equals(namesArr[i]) || "концами рейсер".equals(namesArr[i]) || "конце не рейсер".equals(namesArr[i]) || "конца не ричард".equals(namesArr[i]) || "кондиционеры лица".equals(namesArr[i]) || "конца не орех вечер".equals(namesArr[i]) || "конца не ори дача".equals(namesArr[i]) || "кондиционер records".equals(namesArr[i]) || "компания репортер".equals(namesArr[i]) || "компания ревизор".equals(namesArr[i]) || "конца мире surf".equals(namesArr[i]) || "костанай ручьи".equals(namesArr[i]) || "касание surf".equals(namesArr[i]) || "станционная рейчел".equals(namesArr[i]) || "конца на рычаг".equals(namesArr[i]) || "сценарий surf".equals(namesArr[i]) || "конца на режиссер".equals(namesArr[i]) || "канцелярия surf".equals(namesArr[i]) || "канцелярия счет".equals(namesArr[i]) || "кондиционеры счет".equals(namesArr[i]) || "как сценарий surf".equals(namesArr[i]) || "кондиционеры surf".equals(namesArr[i]) || "кондиционер режиссер".equals(namesArr[i]) || "танцы на руси".equals(namesArr[i]) || "конце на рейсер".equals(namesArr[i]) || "конца нарик вечер".equals(namesArr[i]) || "канцелярия черт".equals(namesArr[i]) || "оксана режиссер".equals(namesArr[i]) || "станционная режиссер".equals(namesArr[i]) || "как сценарий еще".equals(namesArr[i]) || "конца не расчет".equals(namesArr[i]) || "конца не ори еще".equals(namesArr[i]) || "канцлер surf".equals(namesArr[i]) || "канцлер ещё".equals(namesArr[i]) || "канцлер еще".equals(namesArr[i]) || "кондиционера фонд".equals(namesArr[i]) || "конца не раб".equals(namesArr[i]) || "конца нервов".equals(namesArr[i]) || "станционная под".equals(namesArr[i]) || "кондиционер вечер".equals(namesArr[i]) || "кондиционеры вечер".equals(namesArr[i]) || "концами rss".equals(namesArr[i]) || "антонио речь".equals(namesArr[i]) || "танцами резерв".equals(namesArr[i]) || "конца не ручьи".equals(namesArr[i]) || "конца не рыцарь".equals(namesArr[i]) || "конца не вечер".equals(namesArr[i]) || "конца не rutor".equals(namesArr[i]) || "касание вечер".equals(namesArr[i]) || "касание речи".equals(namesArr[i]) || "компания речи".equals(namesArr[i]) || "казань вечер".equals(namesArr[i]) || "акция речи".equals(namesArr[i]) || "ксения речи".equals(namesArr[i]) || "как центр".equals(namesArr[i]) || "конце мир".equals(namesArr[i]) || "канцелярии вечер".equals(namesArr[i]) || "канцлер вечер".equals(namesArr[i]) || "контейнер вечер".equals(namesArr[i]) || "контейнеры вечер".equals(namesArr[i]) || "казань а речи".equals(namesArr[i]) || "кондиционер Йорк".equals(namesArr[i]) || "контейнеры apple".equals(namesArr[i]) || "контейнер я пол".equals(namesArr[i]) || "канцелярия pool".equals(namesArr[i]) || "канцелярия пол".equals(namesArr[i]) || "кондиционер я понял".equals(namesArr[i]) || "кондиционеры афон".equals(namesArr[i]) || "канцлер я фон".equals(namesArr[i]) || "контролер я пол".equals(namesArr[i]) || "концерты apple".equals(namesArr[i]) || "канцлер я пол".equals(namesArr[i]) || "концерт я пол".equals(namesArr[i]) || "кондиционер я был".equals(namesArr[i]) || "кондиционер был".equals(namesArr[i]) || "кондиционер Apple".equals(namesArr[i]) ||"кондиционер я полу".equals(namesArr[i]) || "кондиционер я пол".equals(namesArr[i]) || "кондиционер apple".equals(namesArr[i]) || "кондиционеры apple".equals(namesArr[i]) || "кондиционер я поля".equals(namesArr[i]) || "кондиционер опа".equals(namesArr[i]) || "кондиционеры опа".equals(namesArr[i]) || "кондиционер афон".equals(namesArr[i])) {
                Log.e("COMMAND", "CONDITIONER OFF");
                air_power_off(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Kondisioner öçürildi", Toast.LENGTH_SHORT).show();
                break;

            } else if ("отопление".equals(namesArr[i]) || "какими от".equals(namesArr[i]) || "а ты отель".equals(namesArr[i]) || "а ты о чём".equals(namesArr[i]) || "а ты один".equals(namesArr[i]) || "оплачен".equals(namesArr[i]) || "а ты наш".equals(namesArr[i]) || "11".equals(namesArr[i]) || "копай настя".equals(namesArr[i]) || "компания".equals(namesArr[i]) || "копай не оч".equals(namesArr[i]) || "кафе наш".equals(namesArr[i]) || "копай наш".equals(namesArr[i]) || "копы наш".equals(namesArr[i]) || "apple нет".equals(namesArr[i]) || "комплимент".equals(namesArr[i]) || "папины от".equals(namesArr[i]) || "а пацан".equals(namesArr[i]) || "принцесса".equals(namesArr[i]) || "афина".equals(namesArr[i]) || "а принц".equals(namesArr[i]) || "а признался".equals(namesArr[i]) || "операции".equals(namesArr[i]) || "папин сын".equals(namesArr[i]) || "а прицел".equals(namesArr[i]) || "а пицца".equals(namesArr[i]) || "общаться".equals(namesArr[i]) || "оп и миасс".equals(namesArr[i]) || "а поехать".equals(namesArr[i]) || "папина".equals(namesArr[i]) || "а 13".equals(namesArr[i]) || "кабина".equals(namesArr[i]) || "апина".equals(namesArr[i]) || "опарина".equals(namesArr[i]) || "капризная".equals(namesArr[i]) || "софрино 1".equals(namesArr[i]) || "опарина 1".equals(namesArr[i]) || "копы миасс".equals(namesArr[i]) || "кафе нас".equals(namesArr[i]) || "кафе на отель".equals(namesArr[i]) || "копы нет".equals(namesArr[i]) || "кофейня от".equals(namesArr[i]) || "кофейня".equals(namesArr[i]) || "запарина".equals(namesArr[i]) || "кофейная".equals(namesArr[i]) || "да paint".equals(namesArr[i]) || "капец".equals(namesArr[i]) || "да поймет".equals(namesArr[i]) || "запарина от".equals(namesArr[i]) || "копия цен".equals(namesArr[i]) || "да поттер".equals(namesArr[i]) || "кататься".equals(namesArr[i]) || "операция".equals(namesArr[i]) || "копицентр".equals(namesArr[i]) || "кафе Ассоль".equals(namesArr[i]) || "кафе WhatsApp".equals(namesArr[i]) || "кафе Асыл".equals(namesArr[i]) || "а пацаны".equals(namesArr[i]) || "каприз отцу".equals(namesArr[i]) || "apple отцом".equals(namesArr[i]) || "opel центр".equals(namesArr[i]) || "кафе отце".equals(namesArr[i]) || "кафе от цен".equals(namesArr[i]) || "копы отце".equals(namesArr[i]) || "копы отца".equals(namesArr[i]) || "копы от цен".equals(namesArr[i]) || "гатыа сан".equals(namesArr[i]) || "кафе ацил".equals(namesArr[i]) || "кафе акцент".equals(namesArr[i]) || "кафе Accent".equals(namesArr[i]) || "кафе оценка".equals(namesArr[i]) || "кафе акцентр".equals(namesArr[i]) || "папа сын".equals(namesArr[i]) || "по пацан".equals(namesArr[i]) || "копаться".equals(namesArr[i]) || "папе отшил".equals(namesArr[i]) || "папе ацил".equals(namesArr[i]) || "кафе Асель".equals(namesArr[i]) || "кафе отцом".equals(namesArr[i]) || "папе отель".equals(namesArr[i]) || "кафе отель".equals(namesArr[i]) || "opel".equals(namesArr[i])) {
                Log.e("COMMAND", "DOOR OPEN");
                fingerPrintAfterLock(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Gapy açyldy", Toast.LENGTH_SHORT).show();
                break;

            } else if ("ты не дурачок".equals(namesArr[i]) || "телевизоры ок".equals(namesArr[i]) || "цилиндре йорк".equals(namesArr[i]) || "телевизоре ок".equals(namesArr[i]) || "ты ленивый ок".equals(namesArr[i]) || "телевизор ок".equals(namesArr[i]) || "телевизор як".equals(namesArr[i]) || "телевизор йорк".equals(namesArr[i]) || "телевизор а от".equals(namesArr[i]) || "телевизора от".equals(namesArr[i]) || "телевизоры h".equals(namesArr[i]) || "телевизоры ош".equals(namesArr[i]) || "телевизоры атс".equals(namesArr[i]) || "телевизоре атс".equals(namesArr[i]) || "селе играть".equals(namesArr[i]) || "телевизор особа".equals(namesArr[i]) || "телевизор особо".equals(namesArr[i]) || "телевизоры ад".equals(namesArr[i]) || "телевизоры at".equals(namesArr[i]) || "телевизоры отца".equals(namesArr[i]) || "телевизоры отце".equals(namesArr[i]) || "телевизоры отцы".equals(namesArr[i]) || "телевизоры от".equals(namesArr[i]) || "телевизоры азс".equals(namesArr[i]) || "телевизор ios".equals(namesArr[i]) || "телевизоре аш".equals(namesArr[i]) || "телевизоре h".equals(namesArr[i]) || "телевизор ош".equals(namesArr[i]) || "телевизоре ош".equals(namesArr[i]) || "через 1".equals(namesArr[i]) || "сервис 1".equals(namesArr[i]) || "телевизор ad".equals(namesArr[i]) || "телевизор at".equals(namesArr[i]) || "телевизор рад".equals(namesArr[i]) || "телевизор ад".equals(namesArr[i]) || "телевизор от".equals(namesArr[i]) || "телевизор отсылка".equals(namesArr[i]) || "телевизор асан".equals(namesArr[i]) || "телевизор отчет".equals(namesArr[i]) || "телевизор 1".equals(namesArr[i]) || "телевизор а".equals(namesArr[i]) || "телевизор очень".equals(namesArr[i]) || "телевизора центр".equals(namesArr[i]) || "телевизор accent".equals(namesArr[i]) || "телевизор Samsung".equals(namesArr[i]) || "телевизор Thomson".equals(namesArr[i]) || "телевизор Томсон".equals(namesArr[i]) || "телевизор отель".equals(namesArr[i]) || "телевизор ацилок".equals(namesArr[i]) || "телевизор Accent".equals(namesArr[i]) || "телевизор artel".equals(namesArr[i]) ||"телевизор отцу".equals(namesArr[i]) || "телевизор отцом".equals(namesArr[i]) || "телевизор Ассоль".equals(namesArr[i]) || "телевизор акцент".equals(namesArr[i]) || "телевизор осел".equals(namesArr[i])) {
                Log.e("COMMAND", "TV ON");
                tv_power(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Telewizor açyldy", Toast.LENGTH_SHORT).show();
                break;

            } else if ( "телевизор Acer".equals(namesArr[i]) ||  "ты видратор".equals(namesArr[i]) ||  "телевизор учу".equals(namesArr[i]) || "телевизору".equals(namesArr[i]) || "телевизоры".equals(namesArr[i]) || "телевизоры acer".equals(namesArr[i]) || "телевизор acer".equals(namesArr[i]) || "телевизор питер".equals(namesArr[i]) || "телевизор вечер".equals(namesArr[i]) || "телевизоры вечер".equals(namesArr[i]) || "телевизор surf".equals(namesArr[i]) || "телевизоры surf".equals(namesArr[i]) || "телевизор ача".equals(namesArr[i]) || "телевизор а чё".equals(namesArr[i]) || "телевизоры чё".equals(namesArr[i]) || "телевизор а что".equals(namesArr[i]) || "телевизор оче".equals(namesArr[i]) || "телевизоры up".equals(namesArr[i]) || "телевизоры об".equals(namesArr[i]) || "телевизор up".equals(namesArr[i]) || "телевизоре up".equals(namesArr[i]) || "телевизоры я".equals(namesArr[i]) || "телевизор я".equals(namesArr[i]) || "телевизор Apple".equals(namesArr[i]) || "телевизор я понял".equals(namesArr[i]) || "телевизор я пон".equals(namesArr[i]) || "телевизор я пол".equals(namesArr[i]) || "телевизоры оптом".equals(namesArr[i]) || "телевизор Йорк".equals(namesArr[i]) || "телевизоры Apple".equals(namesArr[i]) || "телевизоры apple".equals(namesArr[i]) || "телевизор apple".equals(namesArr[i]) || "телевизор я поля".equals(namesArr[i]) || "телевизоры опа".equals(namesArr[i]) || "телевизор опа".equals(namesArr[i]) || "телевизоры афон".equals(namesArr[i])) {
                Log.e("COMMAND", "TV OFF");
                tv_power(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Telewizor öçürildi", Toast.LENGTH_SHORT).show();
                break;

            } else if ("жанна от".equals(namesArr[i]) || "открой жаль".equals(namesArr[i]) || "жаль вас".equals(namesArr[i]) || "залезать".equals(namesArr[i]) || "жалюзи оценок".equals(namesArr[i]) || "жалюзи цена".equals(namesArr[i]) || "жалюз отель".equals(namesArr[i]) || "жаль зад".equals(namesArr[i]) || "жаль dodge".equals(namesArr[i]) || "жаль удач".equals(namesArr[i]) ||"жаль удачи".equals(namesArr[i]) || "жаль ужас".equals(namesArr[i]) || "за лиза от".equals(namesArr[i]) || "записаться".equals(namesArr[i]) || "салют акцент".equals(namesArr[i]) || "жаль у сосед".equals(namesArr[i]) || "жаль у саши".equals(namesArr[i]) || "жаль у сад цен".equals(namesArr[i]) || "жаль у сад".equals(namesArr[i]) || "жарю сад".equals(namesArr[i]) || "жарю зачем".equals(namesArr[i]) || "заниматься".equals(namesArr[i]) || "залез отчет".equals(namesArr[i]) || "занят цен".equals(namesArr[i]) || "салют от цен".equals(namesArr[i]) || "зайди загс".equals(namesArr[i]) || "зови загс".equals(namesArr[i]) || "завел сапсан".equals(namesArr[i]) || "жадина цен".equals(namesArr[i]) || "жаль отца".equals(namesArr[i]) || "жадина".equals(namesArr[i]) || "жаль я".equals(namesArr[i]) || "жаль зачем".equals(namesArr[i]) || "за одеваться".equals(namesArr[i]) || "жаль отцом".equals(namesArr[i]) || "жаль играть".equals(namesArr[i]) || "жаль видать".equals(namesArr[i]) || "жаль этаж".equals(namesArr[i]) || "жаль от".equals(namesArr[i]) || "взяли за от".equals(namesArr[i]) || "джалиля".equals(namesArr[i]) || "жаль его от".equals(namesArr[i]) || "жаль 1".equals(namesArr[i]) || "за adidas".equals(namesArr[i]) || "жаль уаза".equals(namesArr[i]) || "джанни от".equals(namesArr[i]) || "железо от".equals(namesArr[i]) || "жаль эта цель".equals(namesArr[i]) || "затока цен".equals(namesArr[i]) || "завязать".equals(namesArr[i]) || "займи заяц".equals(namesArr[i]) || "жаль назад".equals(namesArr[i]) || "жаль заяц".equals(namesArr[i]) || "жаль зайца".equals(namesArr[i]) || "жаль узо".equals(namesArr[i]) || "за лиза".equals(namesArr[i]) || "за назад".equals(namesArr[i]) || "залез отцом".equals(namesArr[i]) || "жаль асад".equals(namesArr[i]) || "жалюзи азс".equals(namesArr[i]) || "жалюзи отцу".equals(namesArr[i]) || "за лиза 1".equals(namesArr[i]) || "жалюзи отцы".equals(namesArr[i]) || "жанна заяц".equals(namesArr[i]) || "жалюзи отца".equals(namesArr[i]) || "жалюзи овца".equals(namesArr[i]) || "жаль сад".equals(namesArr[i]) || "жаль из-за".equals(namesArr[i]) || "жаль узнать".equals(namesArr[i]) || "жаль с отцом".equals(namesArr[i]) || "жаль из отцов".equals(namesArr[i]) || "жаль узы add".equals(namesArr[i]) || "жалюзи add".equals(namesArr[i]) || "жаль я от".equals(namesArr[i]) || "жалюзи оч".equals(namesArr[i]) || "жалюзи ad".equals(namesArr[i]) ||"жалюзи at".equals(namesArr[i]) ||"жалюзи ад".equals(namesArr[i]) || "жалюзи от".equals(namesArr[i]) || "жалюз Осин".equals(namesArr[i]) || "жаль Уотсон".equals(namesArr[i]) || "Жаль очень".equals(namesArr[i]) || "шале-отель".equals(namesArr[i]) || "жалюзи открыл".equals(namesArr[i]) || "жаль у вас".equals(namesArr[i]) || "залез отце".equals(namesArr[i]) || "жаль у сапсан".equals(namesArr[i]) || "жаль у отца".equals(namesArr[i]) || "жаль усачев".equals(namesArr[i]) || "жаль уотсон".equals(namesArr[i]) || "зовет отцом".equals(namesArr[i]) || "занят отцом".equals(namesArr[i]) || "жалюзи ацил".equals(namesArr[i]) || "жалюз ацил".equals(namesArr[i]) || "жалюзи отшил".equals(namesArr[i]) || "жалюзи отель".equals(namesArr[i]) || "жалюзи Аксон".equals(namesArr[i]) || "жалюз Accent".equals(namesArr[i]) || "жалюзи отцом".equals(namesArr[i]) || "жалюз отшил".equals(namesArr[i])) {
                Log.e("COMMAND", "CURTAIN OPEN");

                Toast.makeText(getApplicationContext(), "Žalýuz açyldy", Toast.LENGTH_SHORT).show();
                curtain_up(getApplicationContext());
                try {
                    Thread.sleep(5000);
                    curtain_stop(getApplicationContext());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            } else if ("жалюзи яблок".equals(namesArr[i]) || "жанна заявку".equals(namesArr[i]) || "жанна заявка".equals(namesArr[i]) || "жалюзи опор".equals(namesArr[i]) || "жалюзи а под".equals(namesArr[i]) || "жалюзи яд".equals(namesArr[i]) || "жалюзи оп".equals(namesArr[i]) || "жалюзи up".equals(namesArr[i]) || "жалюзи йорк".equals(namesArr[i]) || "жалюзи як".equals(namesArr[i]) || "жалюзи я".equals(namesArr[i]) || "жаль я понял".equals(namesArr[i]) || "жалюзи пон".equals(namesArr[i]) || "жалюзи опан".equals(namesArr[i]) || "жаль а я пон".equals(namesArr[i]) || "жалюзи я пон".equals(namesArr[i]) || "жалюзи опал".equals(namesArr[i]) || "жалюзи apple".equals(namesArr[i]) || "жалюзи афон".equals(namesArr[i]) || "жалюзи а по".equals(namesArr[i]) || "салют я пол".equals(namesArr[i]) || "жалюзи а пол".equals(namesArr[i]) || "жалюзи Apple".equals(namesArr[i]) || "жалюзи пол".equals(namesArr[i]) || "жалюзи АПЛ".equals(namesArr[i]) || "жалюз Apple".equals(namesArr[i]) || "жалюзи Якутск".equals(namesArr[i])) {
                Log.e("COMMAND", "CURTAIN CLOSE");

                Toast.makeText(getApplicationContext(), "Žalýuz ýapyldy", Toast.LENGTH_SHORT).show();
                curtain_down(getApplicationContext());
                try {
                    Thread.sleep(5000);
                    curtain_stop(getApplicationContext());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }

            // akylly oy
            else if ("андрей".equals(namesArr[i]) || "а колей".equals(namesArr[i]) || "олень".equals(namesArr[i]) || "акула".equals(namesArr[i]) || "оконный".equals(namesArr[i]) || "a hyundai".equals(namesArr[i]) || "а когда".equals(namesArr[i]) || "оля".equals(namesArr[i]) || "акулы".equals(namesArr[i])) {
                Log.e("COMMAND", "MAIN KEY");
                Toast.makeText(getApplicationContext(), "Akylly öý", Toast.LENGTH_SHORT).show();
                break;
            }
        }

    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.i(LOG_TAG, "FAILED " + errorMessage);
//        returnedError.setText(errorMessage);

    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        //Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    public String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}