package com.example.javasample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LocaleManager;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Inside an activity, in-app language picker gets an input locale "xx-YY"
        // 2. App calls the API to set its locale
        var localeManager = getSystemService(LocaleManager.class);

        var locales = new Locale[]{
                Locale.forLanguageTag("ja"), // Priority Max
                Locale.forLanguageTag("ak"), // (not support locale)
                Locale.forLanguageTag("zh"),
                Locale.forLanguageTag("ko"),
        };

        findViewById(R.id.buttonShuffle).setOnClickListener(view -> {
            Log.d(TAG, "before locales: " + locales[0].getDisplayName());
            Collections.shuffle(Arrays.asList(locales));
            Log.d(TAG, "after shuffled locales:" + locales[0].getDisplayName());
        });

        findViewById(R.id.buttonSet).setOnClickListener(view -> {
            localeManager.setApplicationLocales(new LocaleList(locales));
            // 3. The system updates the locale and restarts the app, including any configuration updates
            // 4. The app is now displayed in "xx-YY" language
        });

        findViewById(R.id.buttonReset).setOnClickListener(view -> {
            // Pass a LocaleList#getEmptyLocaleList() to reset to the system locale.
            localeManager.setApplicationLocales(LocaleList.getEmptyLocaleList());
        });

        findViewById(R.id.buttonGet).setOnClickListener(view -> {
            // 1. App calls the API to get the preferred locale
            var currentAppLocales = localeManager.getApplicationLocales();
            Log.d(TAG, "onCreate: currentAppLocales toLanguageTags: " + currentAppLocales.toLanguageTags());
            // 2. App uses the returned LocaleList to display languages to the user
        });
    }
}