package com.example.medical.EmtityClass;

import android.content.Context;

import com.google.mlkit.nl.translate.Translator;

public class EnglishToBangla {
    com.google.mlkit.nl.translate.Translator englishToBangla;
    Context context;

    public EnglishToBangla() {
    }

    public EnglishToBangla(Translator englishToBangla, Context context) {
        this.englishToBangla = englishToBangla;
        this.context = context;
    }


}
