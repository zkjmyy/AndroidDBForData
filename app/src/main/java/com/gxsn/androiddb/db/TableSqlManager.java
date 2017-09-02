package com.gxsn.androiddb.db;

import android.database.Cursor;
import android.text.TextUtils;

import com.gxsn.androiddb.Model.TableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkj on 2016/12/01
 * GreatWall
 */

public class TableSqlManager extends SqlManager {


    private static TableSqlManager tableSqlManager;
    private static final String tableName = "tab_table";

    private TableSqlManager() {
    }

    public static TableSqlManager getInstance() {
        if (tableSqlManager == null) {
            synchronized (TableSqlManager.class) {
                if (tableSqlManager == null) {
                    tableSqlManager = new TableSqlManager();
                }
            }
        }
        return tableSqlManager;
    }

    // 按照sql语句查询
    public static List<TableModel> queryBySQL(String sql) {
        List<TableModel> models = new ArrayList<>();
        Cursor cursor = (Cursor) wirtedb.rawQuery(sql,null);
        if (cursor == null) return null;

        int code = cursor.getColumnIndex("Code");
        int type = cursor.getColumnIndex("Type");
        int label = cursor.getColumnIndex("Label");
        int defaultValue = cursor.getColumnIndex("DefaultValue");
        int hint = cursor.getColumnIndex("Hint");
        int isVisible = cursor.getColumnIndex("IsVisible");
        int isEditable = cursor.getColumnIndex("IsEditable");
        int isComparable = cursor.getColumnIndex("IsComparable");
        int isNessary = cursor.getColumnIndex("IsNessary");
        int isUp = cursor.getColumnIndex("IsUp");
        int dataType = cursor.getColumnIndex("DataType");
        int upType = cursor.getColumnIndex("UpType");
        int sort = cursor.getColumnIndex("Sort");
        int categoryID = cursor.getColumnIndex("CategoryID");
        int userType = cursor.getColumnIndex("UserType");

        while (cursor.moveToNext()) {
            TableModel m = new TableModel();
            m.setCode(cursor.getString(code));
            m.setType(cursor.getString(type));
            m.setLabel(cursor.getString(label));


            String temp = cursor.getString(defaultValue);
            if (!TextUtils.isEmpty(temp))
                m.setDefaultValue(temp);
            temp = cursor.getString(hint);
            if (!TextUtils.isEmpty(temp)) {
                m.setHint(temp);
            }

            m.setVisible(cursor.getShort(isVisible) != 0);

            m.setEditable(cursor.getShort(isEditable) != 0);
            m.setComparable(cursor.getShort(isComparable) != 0);
            m.setNessary(cursor.getShort(isNessary) != 0);

            m.setUp(cursor.getShort(isUp) != 0);

            m.setDataType(cursor.getString(dataType));

            short temp_short = cursor.getShort(upType);
            if (temp_short != 0) {
                m.setUpType(temp_short);
            }
            m.setSort(cursor.getInt(sort));

            m.setCategoryID(Integer.parseInt(cursor.getString(categoryID)));

            m.setUserType(cursor.getInt(userType));
            models.add(m);
        }
        cursor.close();
        return models;
    }

    /**
     * 按监测内容查询
     */
    public static List<TableModel> queryByCategoryID(int categoryID) {

        String sql = "select * from " + tableName + " where CategoryID ='" + categoryID + "" +
                "' order by Sort asc";

        return queryBySQL(sql);
    }

    /**
     * 按监测内容查询
     */
    public static List<TableModel> queryByCategoryIDAndUserType(int categoryID, int userType) {

        String sql = "select * from " + tableName + " where CategoryID ='" + categoryID + "' and UserType = '" + userType +
                "' order by Sort asc";

        return queryBySQL(sql);
    }


    /**
     * 按监测内容查询和提交的数据
     */
    public static List<TableModel> queryByCategoryIDAndUserTypeAndIsUp(int categoryID, int userType, int isup) {

        String sql = "select * from " + tableName + " where CategoryID ='" + categoryID + "' and UserType = '" + userType + "' and IsUp = '" + isup +
                "' order by Sort asc";

        return queryBySQL(sql);
    }

    /**
     * 按监测内容查询和提交的数据
     */
    public static List<TableModel> queryByCategoryIDAndUserTypeAndCompare(int categoryID, int userType, int compare) {

        String sql = "select * from " + tableName + " where CategoryID ='" + categoryID + "' and UserType = '" + userType + "' and IsComparable = '" + compare +
                "' order by Sort asc";

        return queryBySQL(sql);
    }


    public static TableModel queryByCategoryIDAndUserTypeAndCode(int categoryID, int userType, String code) {
        String sql = "select * from " + tableName + " where CategoryID ='" + categoryID + "' and UserType = '" + userType + "' and Code ='" + code + "'"
                + " order by Sort asc";
        List<TableModel> tableModels = queryBySQL(sql);
        TableModel tableModel = tableModels.get(0);
        return tableModel;
    }

    /**
     * 查询数据库，获取哪些控件是可对比的
     *
     * @param categoryID
     * @return
     */
    public static List<String> getCompareableKey(int categoryID, int userType) {
        List<String> lists = new ArrayList<>();
        String sql = "select Code from " + tableName + " where CategoryID = '" +
                categoryID + "' and UserType = '" + userType + "' and IsComparable = '1'";
        Cursor cursor = wirtedb.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("Code");
            String string = cursor.getString(index);
            lists.add(string);
        }
        cursor.close();
        return lists;
    }


    /**
     * 插入
     *
     * @param tableModel
     */
    public static void insert(TableModel tableModel) {
        String sql = "INSERT INTO " + tableName + " (Code, Label";

        sql += ", Type";
        String temp = tableModel.getHint();
        if (!TextUtils.isEmpty(temp))
            sql += ", Hint";
        temp = tableModel.getDefaultValue();
        if (!TextUtils.isEmpty(temp))
            sql += ", DefaultValue";

        sql += ", IsVisible, IsEditable, Sort, DataType, " +
                "IsUp, UpType, IsComparable, IsNessary, CategoryID,UserType)" +
                "values ('" + tableModel.getCode() + "'" +
                ", '" + tableModel.getLabel() + "'";

        sql += ", '" + tableModel.getType() + "'";
        temp = tableModel.getHint();
        if (!TextUtils.isEmpty(temp))
            sql += ", '" + tableModel.getHint() + "'";
        temp = tableModel.getDefaultValue();
        if (!TextUtils.isEmpty(temp))
            sql += ", '" + tableModel.getDefaultValue() + "'";

        sql += ", '" + (tableModel.isVisible() ? 1 : 0) + "'" +
                ", '" + (tableModel.isEditable() ? 1 : 0) + "'" +
                ", '" + tableModel.getSort() + "'" +
                ", '" + tableModel.getDataType() + "'" +
                ", '" + (tableModel.isUp() ? 1 : 0) + "'" +
                ", '" + tableModel.getUpType() + "'" +
                ", '" + (tableModel.isComparable() ? 1 : 0) + "'" +
                ", '" + (tableModel.isNessary() ? 1 : 0) + "'" +
                ", '" + tableModel.getCategoryID() + "'"+
                ", '" + tableModel.getUserType() + "')";

//        Log.e("======table=insert==", sql);
        wirtedb.execSQL(sql);
        // DatabaseSqlHelper.getBasicDBSQLHelperNum().execSql(sql);
    }

}
