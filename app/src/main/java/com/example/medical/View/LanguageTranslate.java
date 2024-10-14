package com.example.medical.View;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.translation.Translator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.Model.LanguageModel;
import com.example.medical.R;
import com.example.medical.Room.DataBases.TranslatroTable;
import com.example.medical.Room.RoomHelper.TranslateHelpar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageTranslate extends AppCompatActivity {

    RecyclerView recyclerVeiw;
    CardView v_hindi,v_bangla;
    protected static final int RESULT_SPEECH = 100;

    public Translator translationjarman;
    com.google.mlkit.nl.translate.Translator englishToBangla;
    com.google.mlkit.nl.translate.Translator banglaToEnglish;

    String lan_item= "Bangla";
    String sound = "hello World";

    TranslatroTable translateTable;
    TranslateHelpar translateHelpar;
    TextToSpeech textToSpeech_english,textToSpeech_bangla;

    ArrayList<LanguageModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_languagetranslate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerVeiw = findViewById(R.id.recyclerVeiw);
        v_hindi = findViewById(R.id.v_hindi);
        v_bangla = findViewById(R.id.v_bangla);


        translateTable = TranslatroTable.getTranslateTable(this);
        translateHelpar = translateTable.translateHelpar();


        textToSpeech_english = new TextToSpeech(LanguageTranslate.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if ( status == TextToSpeech.SUCCESS){
                    //      Toast.makeText(MainActivity.this, "Text to speech bangla", Toast.LENGTH_SHORT).show();

                    int result = textToSpeech_english.setLanguage(new Locale("en_US"));
                    if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                        //           Toast.makeText(MainActivity.this, "Missing data", Toast.LENGTH_SHORT).show();

                    }else {
                        //           Toast.makeText(MainActivity.this, "all success", Toast.LENGTH_SHORT).show();

                    }


                }else {
                    //        Toast.makeText(MainActivity.this, "Can not suppoty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textToSpeech_bangla = new TextToSpeech(LanguageTranslate.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if ( status == TextToSpeech.SUCCESS){
                    //      Toast.makeText(MainActivity.this, "Text to speech bangla", Toast.LENGTH_SHORT).show();

                    int result = textToSpeech_bangla.setLanguage(new Locale("bn_BD"));
                    if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                        //           Toast.makeText(MainActivity.this, "Missing data", Toast.LENGTH_SHORT).show();

                    }else {
                        //           Toast.makeText(MainActivity.this, "all success", Toast.LENGTH_SHORT).show();

                    }


                }else {
                    //        Toast.makeText(MainActivity.this, "Can not suppoty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Table Make

       CallAdapter();

        v_bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lan_item= "Bangla";

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, "bn_BD");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"bn_BD");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"bn_BD");
                intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES,"bn_BD");
                intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE,"bn_BD");
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"bn_BD");
                intent.putExtra(RecognizerIntent.EXTRA_RESULTS,"bn_BD");


                try {

                    startActivityForResult(intent, RESULT_SPEECH );
                }catch (ActivityNotFoundException e ){
                    e.printStackTrace();
                    Toast.makeText(LanguageTranslate.this, "Can not support speech to text", Toast.LENGTH_SHORT).show();
                }

            }
        });

        v_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                lan_item= "English";

                intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, "en_US");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en_US");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en_US");
                intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES,"en_US");
                intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE,"en_US");
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"en_US");
                intent.putExtra(RecognizerIntent.EXTRA_RESULTS,"en_US");


                try {

                    startActivityForResult(intent, RESULT_SPEECH );
                }catch (ActivityNotFoundException e ){
                    e.printStackTrace();
                    Toast.makeText(LanguageTranslate.this, "Can not support speech to text", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //===================

        TranslatorOptions options_englishToBangla =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.BENGALI)
                        .build();

        TranslatorOptions options_banglaToEnglish =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.BENGALI)
                        .setTargetLanguage(TranslateLanguage.ENGLISH)
                        .build();



        englishToBangla = Translation.getClient(options_englishToBangla);

        banglaToEnglish = Translation.getClient(options_banglaToEnglish);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        englishToBangla.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(LanguageTranslate.this, "download Failed", Toast.LENGTH_SHORT).show();


                    }

                });




        DownloadConditions conditions_bangla = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        banglaToEnglish.downloadModelIfNeeded(conditions_bangla)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LanguageTranslate.this, "Bangla Success", Toast.LENGTH_SHORT).show();

                    }
                });






    }

    public class MyAdapter extends RecyclerView.Adapter {


        int ENGLISH = 0;
        int BANGLA = 1 ;

        public class BanglaViewHolder extends RecyclerView.ViewHolder{

            TextView textView;

            public BanglaViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }

        public class EnglishViewHolder extends RecyclerView.ViewHolder{
            TextView textViewEnglish;
            public EnglishViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (viewType==ENGLISH){
                View view = layoutInflater.inflate(R.layout.english_layout,parent,false);
                return new EnglishViewHolder(view);
            }else {
                View view = layoutInflater.inflate(R.layout.banglatext,parent,false);
                return new BanglaViewHolder(view);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            LanguageModel languageModel = arrayList.get(position);




            if (getItemViewType(position)==ENGLISH){

                EnglishViewHolder eHolder = (EnglishViewHolder) holder;
                eHolder.textViewEnglish.setText(languageModel.getTranslate());


            }else {

                BanglaViewHolder bHolder = (BanglaViewHolder) holder;
                bHolder.textView.setText(languageModel.getTranslate());

            }

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        @Override
        public int getItemViewType(int position) {
            LanguageModel languageModel = arrayList.get(position);
            String item = languageModel.getItem();
            if (item.contains("English")){
                return ENGLISH;
            }else {
                return BANGLA;
            }

        }
    }



/*    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {



        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.banglatext,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

            LanguageModel languageModel = arrayList.get(position);

            holder.textview.setText(languageModel.getItem());

            Toast.makeText(LanguageTranslate.this, ""+languageModel.getItem(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView textview;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textview = itemView.findViewById(R.id.textview);
            }
        }
    }*/

    //================================================================================================

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH:
                if (requestCode == RESULT_SPEECH && data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    sound= text.get(0);

                    if(lan_item.contains("English")){
                        engtobang(text.get(0));



                    }else {
                        bangToEng(text.get(0));


                    }

                }
        }
    }

    public void engtobang(String text){
        englishToBangla.translate(text)
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            translateHelpar.insertDoctor(new LanguageModel(""+sound,""+s,lan_item));
                            textToSpeech_english.speak(s, TextToSpeech.QUEUE_ADD, null,null);

                            CallAdapter();
                        }catch (Exception e){
                            Toast.makeText(LanguageTranslate.this, ""+e, Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });
    }

    public void bangToEng(String text){
        banglaToEnglish.translate(text)
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {

                        try {
                            translateHelpar.insertDoctor(new LanguageModel(""+sound,""+s,lan_item));
                            textToSpeech_bangla.speak(s,TextToSpeech.QUEUE_FLUSH,null,null);

                            CallAdapter();
                        }catch (Exception e){
                            Toast.makeText(LanguageTranslate.this, ""+e, Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(LanguageTranslate.this, ""+s, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });
    }

    public void CallAdapter(){
        arrayList = new ArrayList<>();
        arrayList = (ArrayList<LanguageModel>) translateHelpar.getsentance();
        arrayList.addAll(arrayList);


        MyAdapter myAdapter = new MyAdapter();
        recyclerVeiw.setAdapter(myAdapter);

        recyclerVeiw.scrollToPosition(myAdapter.getItemCount() - 1);


    }

}