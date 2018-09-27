package change.example.com.changelanguage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;


/**
 * @author HalyangDream
 */
public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change:
                Intent intent = new Intent(this, ChangeLanguageActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Message message) {
        Log.i("Main", "onEvent");
        recreate();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listview);
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
        //toolbar.setNavigationIcon(drawable);
    }

    private void initAdapter() {
        ContentAdapter contentAdapter = new ContentAdapter(this);
        listView.setAdapter(contentAdapter);
    }

}
