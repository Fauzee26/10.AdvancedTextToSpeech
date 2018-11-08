package fauzi.hilmy.advancedtexttospeech.language;

import java.util.ArrayList;

public class LanguageData {
    public static String[][] languageData = new String[][]{
            {"ca-es", "Catalan"},
            {"zh-cn", "Chinese (China)"},
            {"zh-hk", "Chinese (Hong Kong)"},
            {"zh-tw", "Chinese (Taiwan)"},
            {"da-dk", "Danish"},
            {"nl-nl", "Dutch"},
            {"en-au", "English (Australia)"},
            {"en-ca", "English (Canada)"},
            {"en-gb", "English (Great Britain)"},
            {"en-in", "English (India)"},
            {"en-us", "English (United States)"},
            {"fi-fi", "Finnish"},
            {"fr-ca", "French (Canada)"},
            {"fr-fr", "French (France)"},
            {"de-de", "German"},
            {"it-it", "Italian"},
            {"ja-jp", "Japanese"},
            {"ko-kr", "Korean"},
            {"nb-no", "Norwegian"},
            {"pl-pl", "Polish"},
            {"pt-br", "Portuguese (Brazil)"},
            {"pt-pt", "Portuguese (Portugal)"},
            {"ru-ru", "Russian"},
            {"es-mx", "Spanish (Mexico)"},
            {"es-es", "Spanish (Spain)"},
            {"sv-se", "Swedish (Sweden)"}
    };

    public static ArrayList<Language> getLanguagee(){
        Language language = null;
        ArrayList<Language> languageArrayList = new ArrayList<>();
        for (int i = 0; i < languageData.length; i++){
            language = new Language();
            language.setLanguageCode(languageData[i][0]);
            language.setLanguageName(languageData[i][1]);
            languageArrayList.add(language);
        }
        return languageArrayList;
    }
}
