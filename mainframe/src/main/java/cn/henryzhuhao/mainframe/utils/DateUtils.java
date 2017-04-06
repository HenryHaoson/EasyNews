package cn.henryzhuhao.mainframe.utils;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HenryZhuhao on 2017/3/26.
 */

public class DateUtils {
    /**\
     *根据当前日期返回这周周一和周日的日期
     * 以周一为第一天
     * @return
     */
        public static String[] getMONandSUN(){
            String startTime,endTime;
            Calendar calendar= Calendar.getInstance();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
            calendar.setTime(date);
            //calendar.add(Calendar.DATE,1);测试用的，改变现在时间。
            int weekDay=calendar.get(Calendar.DAY_OF_WEEK);
            if(weekDay==1){
                endTime=simpleDateFormat.format(date);
                calendar.add(Calendar.DATE,-7);
                date=calendar.getTime();
                startTime=simpleDateFormat.format(date);
            }else {
                calendar.add(Calendar.DATE,2-weekDay);
                date=calendar.getTime();
                //String startTime=""+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DAY_OF_MONTH);
                startTime=simpleDateFormat.format(date);
                calendar.add(Calendar.DATE,6);
                date=calendar.getTime();
                //String endTime=""+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DAY_OF_MONTH);
                endTime=simpleDateFormat.format(date);
            }

            return new String[]{startTime,endTime};
        }
    public static int getWeekDay(@NonNull String str_date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        try {
            date=simpleDateFormat.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        int weekDay=calendar.get(Calendar.DAY_OF_WEEK);
        if(weekDay==1){
            weekDay=7;
        }else {
            weekDay=weekDay-1;
        }
        return weekDay;
    }
}
