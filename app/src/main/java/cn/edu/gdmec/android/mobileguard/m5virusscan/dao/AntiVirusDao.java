package cn.edu.gdmec.android.mobileguard.m5virusscan.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by 天地科技 on 2017/11/15.
 */

public class AntiVirusDao {
    private static String dbname;
    private static Context context;
    public AntiVirusDao(Context context){
        this.context=context;
        dbname="/data/data/"+context.getPackageName()+"/files/antivirus.db";
    }
    public String checkVirus(String md5){
        String desc=null;
        SQLiteDatabase db=SQLiteDatabase.openDatabase(
                dbname,null,
        SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=db.rawQuery("select desc from datable where md5=?",
                new String[] {md5});
        if (cursor.moveToNext()){
            desc = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return desc;
    }
}