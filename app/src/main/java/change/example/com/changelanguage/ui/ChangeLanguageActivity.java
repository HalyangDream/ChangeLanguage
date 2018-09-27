package change.example.com.changelanguage.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import change.example.com.changelanguage.LanguageUtil;
import change.example.com.changelanguage.R;

/**
 * @author HalyangDream
 */
public class ChangeLanguageActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private AppCompatTextView CHINA, ENGLISH, JAPAN;

    private int CHAGE_SUCCESS = 0x001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        initView();
        setListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        CHINA = findViewById(R.id.china);
        ENGLISH = findViewById(R.id.english);
        JAPAN = findViewById(R.id.japan);
        setSupportActionBar(toolbar);
        //显示NavigationIcon,这个方法是ActionBar的方法.Toolbar没有这个方法.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置监听.必须在setSupportActionBar()之后调用
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });
        toolbar.setTitle(R.string.content_ui);
        //设置icon
//    toolbar.setNavigationIcon(drawable);
    }


    private void setListener() {
        CHINA.setOnClickListener(this);
        ENGLISH.setOnClickListener(this);
        JAPAN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.china:
                changeLanguage("zh");
                break;
            case R.id.english:
                changeLanguage("en");
                break;
            case R.id.japan:
                changeLanguage("ja");
                break;
            default:
                break;
        }
    }

    private void changeLanguage(String language) {
        Toast.makeText(this, "点击了" + language, Toast.LENGTH_SHORT).show();
        Locale locale = new Locale(language);
        Context context = LanguageUtil.changeLanguage(this, locale);
        Message message = new Message();
        message.what = CHAGE_SUCCESS;
        EventBus.getDefault().post(message);
        recreate();
    }



}
