package com.hayllieapps.digidiary.DBHandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void createTable(){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "CREATE TABLE IF NOT EXISTS DIARIES(Id INTEGER PRIMARY KEY AUTOINCREMENT, text VARCHAR, text2 VARCHAR, diarydate VARCHAR, lastupdated VARCHAR, image BLOB, deleted INTEGER, favourite INTEGER)";
        database.execSQL(sql);
    }

    public void insertData(String text1v, String text2v, String diarydate, String lastupdated, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO DIARIES(text, text2, diarydate, lastupdated, image, deleted, favourite) VALUES (?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        //statement.clearBindings();
        statement.bindString(1, text1v);
        statement.bindString(2, text2v);
        statement.bindString(3, diarydate);
        statement.bindString(4, lastupdated);
        statement.bindBlob(5, image);
        statement.bindDouble(6,0);
        statement.bindDouble(7,0);
        statement.executeInsert();
        database.close();
    }

    public void updateData(String text1, String text2, String diarydate, String lastupdated, byte[] image, int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE DIARIES SET text = ?, text2 = ?, diarydate = ?, lastupdated = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1,text1);
        statement.bindString(2,text2);
        statement.bindString(3,diarydate);
        statement.bindString(4,lastupdated);
        statement.bindBlob(5,image);
        statement.bindDouble(6,(double)id);
        statement.execute();
        database.close();
    }

    public void deleteDiaries(ArrayList<Integer> ids){
        SQLiteDatabase database = getWritableDatabase();
        //DELETE from diaries
        String sql = "DELETE FROM DIARIES WHERE id = ?";

        SQLiteStatement statement = database.compileStatement(sql);
        for(int id : ids){
            statement.bindDouble(1,id);
            statement.execute();
        }
        database.close();
    }

    public void  softDeleteDiary(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE DIARIES SET deleted = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindDouble(1,1);
        statement.bindDouble(2,(double)id);

        statement.execute();
        database.close();

    }

    public void restoreDiaries(ArrayList<Integer> ids){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE DIARIES SET deleted = 0 WHERE id = ? ";
        SQLiteStatement statement = database.compileStatement(sql);
        for(int id : ids){
            statement.bindDouble(1,id);
            statement.execute();
        }
        database.close();

    }

    public void addDiaryToFavourites(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE DIARIES SET favourite = 1 WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindDouble(1,(double)id);
        statement.execute();
        database.close();

    }

    public void removeDiaryFromFavourites(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE DIARIES SET favourite = 0 WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindDouble(1,(double)id);
        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
    }
}
