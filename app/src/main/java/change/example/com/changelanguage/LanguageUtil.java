package change.example.com.changelanguage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

/**
 * 切换语言工具类
 */
public class LanguageUtil {

    /**
     * 切换语言
     */
    public static Context changeLanguage(Context context, Locale locale) {
        if (locale == null) {
            locale = readSystemLanguage();
        }
        Log.i("LanguageUtil", "locale:=" + locale.getLanguage());
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context = context.createConfigurationContext(config);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
        }
        res.updateConfiguration(config, dm);
        return context;
    }

    /**
     * 读取APP设置的语言
     *
     * @return
     */
    public static Locale readAppLanguage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = LocaleList.getEmptyLocaleList();
            for (int i = 0; i < localeList.size(); i++) {
                Log.i("LanguageUtil", "AppLanguage=" + localeList.get(i).getLanguage());
            }
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            Log.i("LanguageUtil", "AppLanguage=" + Locale.getDefault());
            Log.i("LanguageUtil", "AppLanguage=" + Locale.getDefault().getLanguage());
            return Locale.getDefault();
        }
    }


    /**
     * 读取系统默认语言
     */
    public static Locale readSystemLanguage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = Resources.getSystem().getConfiguration().getLocales();
            for (int i = 0; i < localeList.size(); i++) {
                Log.i("LanguageUtil", "SystemLanguage=" + localeList.get(i).getLanguage());
            }
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            Log.i("LanguageUtil", "SystemLanguage=" + Locale.getDefault());
            Log.i("LanguageUtil", "SystemLanguage=" + Locale.getDefault().getLanguage());
            return Locale.getDefault();
        }
    }

}
