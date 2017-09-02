package com.gxsn.androiddb.dbhelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gxsn.androiddb.CopyDBApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zkj on 2017/08/30
 * CollectBase
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public static final int VSESION = 3;

    /**
     * @param context
     */
    public MyDBHelper(Context context) {
        super(context, CopyDBApplication.DBNAME, null, VSESION);
        this.myContext = context;
    }

    /**
     * 创建一个空的数据库，
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //该数据库已经存在了
        } else {

            //调用这个方法可以创建空数据库，我们自己的数据可可以将其覆盖,并设置版本号
            this.getWritableDatabase().setVersion(VSESION);

            try {
                //覆盖
                String outFileName = CopyDBApplication.DBPATH + CopyDBApplication.DBNAME;
                InputStream myInput = myContext.getAssets().open(CopyDBApplication.DBNAME);
                IOHelper.copyBigDataToSD(myInput, outFileName);

            } catch (IOException e) {
                throw new Error("io error");
            }
        }

    }

    /**
     * 检测数据库是否存在并且能否打开
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String myPath = CopyDBApplication.DBPATH + CopyDBApplication.DBNAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }


    public SQLiteDatabase openDataBase() throws SQLException {

        //打开数据库
        String myPath = CopyDBApplication.DBPATH + CopyDBApplication.DBNAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        return myDataBase;
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库升级
        switch (oldVersion) {
            case 1:
//                TableModel tableModel = new TableModel();
//
//                tableModel.setCategoryID(0);
//                tableModel.setLabel("哈哈哈哈哈哈哈哈哈哈");
//
//                String sql = "INSERT INTO " + "tab_table" + " (Code, Label";
//
//                sql += ", Type";
//                String temp = tableModel.getHint();
//                if (!TextUtils.isEmpty(temp))
//                    sql += ", Hint";
//                temp = tableModel.getDefaultValue();
//                if (!TextUtils.isEmpty(temp))
//                    sql += ", DefaultValue";
//
//                sql += ", IsVisible, IsEditable, Sort, DataType, " +
//                        "IsUp, UpType, IsComparable, IsNessary, CategoryID,UserType)" +
//                        "values ('" + tableModel.getCode() + "'" +
//                        ", '" + tableModel.getLabel() + "'";
//
//                sql += ", '" + tableModel.getType() + "'";
//                temp = tableModel.getHint();
//                if (!TextUtils.isEmpty(temp))
//                    sql += ", '" + tableModel.getHint() + "'";
//                temp = tableModel.getDefaultValue();
//                if (!TextUtils.isEmpty(temp))
//                    sql += ", '" + tableModel.getDefaultValue() + "'";
//
//                sql += ", '" + (tableModel.isVisible() ? 1 : 0) + "'" +
//                        ", '" + (tableModel.isEditable() ? 1 : 0) + "'" +
//                        ", '" + tableModel.getSort() + "'" +
//                        ", '" + tableModel.getDataType() + "'" +
//                        ", '" + (tableModel.isUp() ? 1 : 0) + "'" +
//                        ", '" + tableModel.getUpType() + "'" +
//                        ", '" + (tableModel.isComparable() ? 1 : 0) + "'" +
//                        ", '" + (tableModel.isNessary() ? 1 : 0) + "'" +
//                        ", '" + tableModel.getCategoryID() + "'"+
//                        ", '" + tableModel.getUserType() + "')";
//
//                db.execSQL(sql);
                break;
            case 2:

                break;
        }

    }
}
