package com.example.medical.View;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medical.R;

import java.util.ArrayList;

public class LanguageTranslate extends AppCompatActivity {

    RecyclerView recyclerVeiw;
    CardView v_hindi,v_bangla;
    protected static final int RESULT_SPEECH = 100;

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

        v_bangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


                intent.putExtra( RecognizerIntent.EXTRA_LANGUAGE, "hi_IN");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"hi_IN");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"hi_IN");
                intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES,"hi_IN");
                intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE,"hi_IN");
                intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"hi_IN");
                intent.putExtra(RecognizerIntent.EXTRA_RESULTS,"hi_IN");


                try {

                    startActivityForResult(intent, RESULT_SPEECH );
                }catch (ActivityNotFoundException e ){
                    e.printStackTrace();
                    Toast.makeText(LanguageTranslate.this, "Can not support speech to text", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter{

        public class banglaHolder extends RecyclerView.ViewHolder{

            public banglaHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
        public class Hindi extends RecyclerView.ViewHolder{

            public Hindi(@NonNull View itemView) {
                super(itemView);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH:
                if (requestCode == RESULT_SPEECH && data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);



                    Toast.makeText(this, ""+text.get(0), Toast.LENGTH_SHORT).show();

                }
        }
    }


}