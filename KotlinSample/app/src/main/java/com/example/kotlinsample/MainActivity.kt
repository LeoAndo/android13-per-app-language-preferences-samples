package com.example.kotlinsample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1. Inside an activity, in-app language picker gets an input locale "xx-YY"
        // 2. App calls the API to set its locale
        val locales = arrayOf(
            Locale.forLanguageTag("ja"),  // Priority Max
            Locale.forLanguageTag("ak"),  // (not support locale)
            Locale.forLanguageTag("zh"),
            Locale.forLanguageTag("ko")
        )
        findViewById<View>(R.id.buttonShuffle).setOnClickListener {
            Log.d(
                TAG,
                "before locales: " + locales[0].displayName
            )
            locales.shuffle()
            Log.d(
                TAG,
                "after shuffled locales:" + locales[0].displayName
            )
        }
        findViewById<View>(R.id.buttonSet).setOnClickListener {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(locales[0].language)
            )
        }
        findViewById<View>(R.id.buttonReset).setOnClickListener {
            // Pass a LocaleList#getEmptyLocaleList() to reset to the system locale.
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList())
        }
        findViewById<View>(R.id.buttonGet).setOnClickListener {
            // 1. App calls the API to get the preferred locale
            val currentAppLocales = AppCompatDelegate.getApplicationLocales()
            Log.d(
                TAG,
                "onCreate: currentAppLocales toLanguageTags: " + currentAppLocales.toLanguageTags()
            )
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}