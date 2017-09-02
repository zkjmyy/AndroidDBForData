package com.gxsn.androiddb;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxsn.androiddb.Model.TableModel;
import com.gxsn.androiddb.db.TableSqlManager;
import com.gxsn.androiddb.dbhelper.MyDBHelper;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase writableDatabase;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mLayout = ((LinearLayout) findViewById(R.id.ll_layout));

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //打开并操作数据
        userdatabase();
    }


    /**
     * 打开并操作数据库
     */
    private void userdatabase() {

        MyDBHelper myDBHelper = new MyDBHelper(this);

        try {
            myDBHelper.createDataBase();

        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        writableDatabase = myDBHelper.openDataBase();



//        TableModel tableModel1 = new TableModel();
//        tableModel1.setLabel("hahhahahahah");
//        tableModel1.setCategoryID(0);
//        TableSqlManager.insert(tableModel1);


        List<TableModel> tableModels = TableSqlManager.queryByCategoryID(0);


        for (TableModel tableModel : tableModels) {
            TextView textView = new TextView(getBaseContext());
            textView.setText(tableModel.getLabel());
            mLayout.addView(textView);
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
