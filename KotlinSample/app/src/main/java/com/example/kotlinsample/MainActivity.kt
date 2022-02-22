package com.example.kotlinsample

import android.app.LocaleManager
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inside an activity, in-app language picker gets an input locale "xx-YY"
        // 2. App calls the API to set its locale
        val localeManager: LocaleManager = getSystemService(LocaleManager::class.java)

        val locales = arrayOf(
            Locale.forLanguageTag("ja"), // Priority Max
            Locale.forLanguageTag("ak"), // (not support locale)
            Locale.forLanguageTag("zh"),
            Locale.forLanguageTag("ko"),
        )

        findViewById<Button>(R.id.buttonShuffle).setOnClickListener {
            Log.d(TAG, "before locales: ${locales.map { it.displayName }}")
            locales.shuffle()
            Log.d(TAG, "after shuffled locales: ${locales.map { it.displayName }}")
        }

        findViewById<Button>(R.id.buttonSet).setOnClickListener {
            localeManager.applicationLocales = LocaleList(*locales)
            // 3. The system updates the locale and restarts the app, including any configuration updates
            // 4. The app is now displayed in "xx-YY" language
        }

        findViewById<Button>(R.id.buttonReset).setOnClickListener {
            // Pass a LocaleList#getEmptyLocaleList() to reset to the system locale.
            localeManager.applicationLocales = LocaleList.getEmptyLocaleList()
        }

        findViewById<Button>(R.id.buttonGet).setOnClickListener {
            // 1. App calls the API to get the preferred locale
            val currentAppLocales: LocaleList = localeManager.applicationLocales
            Log.d(
                TAG,
                "onCreate: currentAppLocales toLanguageTags: " + currentAppLocales.toLanguageTags()
            )
            // 2. App uses the returned LocaleList to display languages to the user
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}