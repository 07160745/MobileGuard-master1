package cn.edu.gdmec.android.mobileguard.m8trafficmonitor.db.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import cn.edu.gdmec.android.mobileguard.m8trafficmonitor.db.TrafficOpenHelper;

public class TrafficDao {
    private TrafficOpenHelper helper;
    public TrafficDao(Context context) {
        helper = new TrafficOpenHelper(context);
    }
    public long getMobileGPRS(String dataString) {
        SQLiteDatabase db = helper.getReadableDatabase();
        long gprs = 0;
        Cursor cursor = db.rawQuery("select gprs from traffic", new String[]{
                "datatime" + dataString + ")"});
        if (cursor.moveToNext()) {
            String gprsStr = cursor.getString(0);
            if (!TextUtils.isEmpty(gprsStr))
                gprs = Long.parseLong(gprsStr);
        } else {
            gprs = -1;
        }
        return gprs;
    }
    public void insertTodayGPRS(long gprs){
        SQLiteDatabase db=helper.getReadableDatabase();
        Date dNow=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(dNow);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        String dataString=sdf.format(dNow);
        ContentValues values=new ContentValues();
        values.put("grap",String.valueOf(gprs));
        values.put("grap",String.valueOf(gprs));
        db.insert("traffic",null,values);
    }
             public void UpdateTodayGPRS(long gprs){
                 SQLiteDatabase db=helper.getWritableDatabase();
                 Date date=new Date();
                 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
                 String dataString=sdf.format(date);
                 ContentValues values=new ContentValues();
                 values.put("grap",String.valueOf(gprs));
                 values.put("grap",String.valueOf(gprs));
                 db.update("traffic",values,"date",new String[]{
                   "datatime"+dataString+")"
                 });
             }
}
