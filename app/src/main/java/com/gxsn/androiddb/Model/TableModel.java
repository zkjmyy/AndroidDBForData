package com.gxsn.androiddb.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zkj on 2016/12/01
 * GreatWall
 */

public class TableModel implements Parcelable {


    public static final String CONDITION_INFO = "CONDITION_INFO";
    public static final String POSITION_INFO = "POSITION_INFO";
    public static final String ILLEGAL_TYPE = "ILLEGAL_TYPE";

    public static final int IS_UP = 1;

    private String code;
    private String type;
    private String label;   //别名
    private String defaultValue;
    private String hint;
    private boolean isVisible;
    private  boolean editable;
    private boolean isComparable;   //用于跟上次数据进行比较
    private boolean isNessary;
    private boolean isUp;
    private String dataType;
    private short upType;
    private int sort;
    private int categoryID;
    private int userType;

    public TableModel() {}


    protected TableModel(Parcel in) {
        code = in.readString();
        type = in.readString();
        label = in.readString();
        defaultValue = in.readString();
        hint = in.readString();
        isVisible = in.readByte() != 0;
        editable = in.readByte() != 0;
        isComparable = in.readByte() != 0;
        isNessary = in.readByte() != 0;
        isUp = in.readByte() != 0;
        dataType = in.readString();
        sort = in.readInt();
        categoryID = in.readInt();
        userType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(type);
        dest.writeString(label);
        dest.writeString(defaultValue);
        dest.writeString(hint);
        dest.writeByte((byte) (isVisible ? 1 : 0));
        dest.writeByte((byte) (editable ? 1 : 0));
        dest.writeByte((byte) (isComparable ? 1 : 0));
        dest.writeByte((byte) (isNessary ? 1 : 0));
        dest.writeByte((byte) (isUp ? 1 : 0));
        dest.writeString(dataType);
        dest.writeInt(sort);
        dest.writeInt(categoryID);
        dest.writeInt(userType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TableModel> CREATOR = new Creator<TableModel>() {
        @Override
        public TableModel createFromParcel(Parcel in) {
            return new TableModel(in);
        }

        @Override
        public TableModel[] newArray(int size) {
            return new TableModel[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isComparable() {
        return isComparable;
    }

    public void setComparable(boolean comparable) {
        isComparable = comparable;
    }

    public boolean isNessary() {
        return isNessary;
    }

    public void setNessary(boolean nessary) {
        isNessary = nessary;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public short getUpType() {
        return upType;
    }

    public void setUpType(short upType) {
        this.upType = upType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public static Creator<TableModel> getCREATOR() {
        return CREATOR;
    }
}
