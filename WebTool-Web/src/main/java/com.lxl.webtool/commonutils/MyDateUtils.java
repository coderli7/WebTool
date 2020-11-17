package com.lxl.webtool.commonutils;

import com.lxl.webtool.log.LoggerEnum;
import com.lxl.webtool.log.MyLoggerFactory;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils {


    public final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDateTimeNow(String format) {
        if (format == null || "".equals(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(format)
                .format(Calendar.getInstance().getTime());
    }

    /**
     * 获取两个时间差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getMinutes(String startDate, String endDate) {
        Long minute = 0L;
        try {

            if (startDate != null && startDate != "" && endDate != null
                    && endDate != "") {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Date startDt = simpleDateFormat.parse(startDate);
                Date endDt = simpleDateFormat.parse(endDate);

                long newTime1 = startDt.getTime();
                long newTime2 = endDt.getTime();
                Long result = newTime2 - newTime1; // 获取两时间相差的毫秒数
                minute = result;

            }
        } catch (Exception e) {
            MyLoggerFactory.log(LoggerEnum.Error,
                    String.format("执行函数getMinutes发生异常:%s", e.getMessage()));
        }
        return minute;
    }

    /**
     * 获取日期差额
     *
     * @param time
     * @return
     */
    public static String getTimeStr(Long time) {
        String timeStr = "";
        try {
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long nm = 1000 * 60;
            long hour = time % nd / nh; // 获取相差的小时数
            long min = time % nd % nh / nm; // 获取相差的分钟数
            long day = time / nd;
            timeStr = String.format("%s天 %s小时%s分 ", day, hour, min);
        } catch (Exception e) {
            MyLoggerFactory.log(LoggerEnum.Error,
                    String.format("执行函数getTimeStr发生异常:%s", e.getMessage()));
        }
        return timeStr;
    }

    /**
     * 转化日期格式
     *
     * @param dateStr
     * @param fromFormat
     * @param toFormat
     * @return
     */
    public static String getNewDateStr(String dateStr, String fromFormat,
                                       String toFormat) {
        String newDateStr = dateStr;
        Date fromeDate;
        try {
            if (StringUtils.isNotBlank(dateStr)
                    && StringUtils.isNotBlank(fromFormat)
                    && StringUtils.isNotBlank(toFormat)) {
                fromeDate = new SimpleDateFormat(fromFormat).parse(dateStr);
                newDateStr = new SimpleDateFormat(toFormat).format(fromeDate);
            }
        } catch (ParseException e) {
            MyLoggerFactory.log(LoggerEnum.Error,
                    String.format("日期转换异常(%s):%s", dateStr, e.getStackTrace()));
        }
        return newDateStr;

    }

    /**
     * 获取当天起始时间戳
     *
     * @return
     */
    public static Long getCurDayBeginTime() {
        Long curDayBegin = 0L;
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        String orgDt = df1.format(System.currentTimeMillis());
        try {
            curDayBegin = df1.parse(orgDt).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDayBegin / 1000;
    }

    /**
     * 获取当天最后一刻时间戳
     *
     * @return
     */
    public static Long getCurDayEndTime() {
        Long curDayEnd = 0L;
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String orgDt = df1.format(
                calendar.getTime()
        );
        try {
            curDayEnd = df1.parse(orgDt).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (curDayEnd / 1000 - 1);
    }

    /**
     * 将指定时间转化为时间戳
     *
     * @param dtVal
     * @param dfType 格式转换标记,可为空,默认为default
     * @return
     */
    public static Long convertDateStr(String dtVal, String dfType) {
        Long time = 0L;
        if (StringUtils.isBlank(dtVal)) {
            return time;
        }
        String dfPatern = StringUtils.isNotBlank(dfType) ? dfType : MyDateUtils.DATE_FORMAT_DEFAULT;
        SimpleDateFormat df = new SimpleDateFormat(dfPatern);
        try {
            time = df.parse(dtVal).getTime() / 1000;
        } catch (ParseException e) {
        }
        return time;
    }

}
