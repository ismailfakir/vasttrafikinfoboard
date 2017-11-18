package net.cloudcentrik.vasttrafik;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VasttrafikUtils {

    public static Date getCurrentTime(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //System.out.println( sdf.format(cal.getTime()) );


        /*LocalTime start =LocalTime.now();
        //System.out.println(start.format(DateTimeFormatter.ofPattern("HH:mm")));

        LocalTime end=LocalTime.of(20,20);
        if(end.isAfter(start)){
            System.out.println(end.toString());
        }*/

        try{
            java.lang.String str="20:20";
            //DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date inputDate = sdf.parse(str);

            System.out.println(sdf.format(inputDate.getTime()));

        }catch (Exception e){

        }



        return cal.getTime();
    }

    public static boolean isAfter(String time,String date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

        try {

            Calendar cal = Calendar.getInstance();

            Date currentTime=Calendar.getInstance().getTime();
            Date inputTime=dateFormat.parse(date.concat(" ").concat(time));

            //System.out.println("Input Time : "+inputTime);
            //System.out.println("Current Time : "+currentTime);

            if(inputTime.after(currentTime)){
                return true;
            }

        }catch (Exception e){
            System.out.println("Date formatting exception");

        }
        return false;
    }

    /*public static void main( String[] args ) throws Exception
    {
        VasttrafikUtils vt=new VasttrafikUtils();
        System.out.println(isAfter("20:40","17-11-12"));
    }*/
}
