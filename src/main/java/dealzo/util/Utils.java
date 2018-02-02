package dealzo.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Utils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private static final SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

//    public static String sha256Hex(String str){
//        return DigestUtils.sha256Hex(str);
//    }

    public static String randomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static Date addNDaysToDate(Date date, int nDays){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, nDays);
        return cal.getTime();
    }

    public static Integer getYear(Timestamp timestamp){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.YEAR);
    }

    public static long getDaysDifference(Timestamp first, Timestamp second) {
        if(first != null && second != null){
            long diff = second.getTime() - first.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;
    }


    public static boolean isFuture(Timestamp timestamp) {
        return timestamp.after(new Timestamp(System.currentTimeMillis()));
    }

    public static Date getFormattedDate(String dateString , String timeString) {
        try {
            if (StringUtils.isEmpty(dateString)) {
                return null;
            }
            if (StringUtils.isEmpty(timeString)) {
                timeString = "00:00";
            }
            String dateTimeString = dateString + "T" + timeString;
            return sdf.parse(dateTimeString);
        } catch (Exception e) {
            logger.debug("invalid date and time format fromDateString:" + dateString + "fromTime :" + timeString);
        }
        return null;
    }

    public static String getFormattedDate(Date date, Long longDate) {
        String formattedDate = "";
        if (longDate != null && date == null) {
            date = new Date(longDate * 1000);
        }
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mma, dd-MMM-yy");
        formattedDate = df2.format(date);

        return formattedDate;
    }

    public static Timestamp getCurrentTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static Long getCurrentTimeInSeconds() {
        return System.currentTimeMillis()/1000;
    }

}
