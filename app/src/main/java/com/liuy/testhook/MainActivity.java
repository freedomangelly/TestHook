package com.liuy.testhook;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.liuy.testhook.hookProvider.ProviderHelper;

import de.robv.android.xposed.XposedBridge;

import static com.liuy.testhook.hookProvider.ProviderHelper.URI;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll=findViewById(R.id.ll_main_layout);
        Button query = getButtonQuary();
        Button insert = getProviderInsert();
        Button tv = ActivityHookBtn();
        ll.addView(tv);
        ll.addView(query);
        ll.addView(insert);
        TestHook testHook=new TestHook();
//        XposedBridge.hookAllConstructors(HookEntity.class,testHook);
        XposedBridge.hookAllMethods(HookEntity.class,"onPause",testHook);
        new HookEntity().onPause();
    }

    private Button getButtonQuary() {
        Button query = new Button(this);
        query.setText("query");
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = getContentResolver().query(URI,
                        null, null, null, null);
                assert cursor != null;
                while (cursor.moveToNext()) {
                    int count = cursor.getColumnCount();
                    StringBuilder sb = new StringBuilder("column: ");
                    for (int i = 0; i < count; i++) {
                        sb.append(cursor.getString(i) + ", ");
                    }
                    Log.d(TAG, sb.toString());
                }
            }
        });
        return query;
    }

    private Button getProviderInsert() {
        Button insert = new Button(this);
        insert.setText("insert");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("name", "name" + ProviderHelper.count++);
                getContentResolver().insert(ProviderHelper.URI, values);
            }
        });
        return insert;
    }

    private Button ActivityHookBtn() {
        Button tv = new Button(this);
        tv.setText("测试界面");
//        setContentView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("http://www.baidu.com"));
                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                getApplicationContext().startActivity(intent);
            }
        });
        return tv;
    }

    @Override
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            com.liuy.testhook.proxyhook.hookActivityAttach.HookHelper.attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        Log.i("TestHook","onPause");
        super.onPause();
    }
}
