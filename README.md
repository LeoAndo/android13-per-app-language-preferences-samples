# overview
Per-app language preferences (Android 13+)<br>
https://developer.android.com/about/versions/13/features#app-languages<br>
https://developer.android.com/about/versions/13/features/app-languages<br>

# API
https://developer.android.com/reference/android/app/LocaleManager#getApplicationLocales()<br>
https://developer.android.com/reference/android/app/LocaleManager#setApplicationLocales(android.os.LocaleList)<br>

# dev memo
端末の言語設定に依存させたい場合は、`LocaleList.getEmptyLocaleList()`を使ってリセットする<br>
setApplicationLocalesでアプリ内の言語設定を行えば、端末の言語設定に影響受けない<br>


# capture OS 13

## Captureしてませんが、OS13未満のデバイスでも動作します！

<img src="https://github.com/LeoAndo/android-per-app-language-preferences-samples/blob/main/capture.gif" width=320 />
