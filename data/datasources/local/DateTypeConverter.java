package rs.raf.vezbe11.data.datasources.local;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTypeConverter {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @TypeConverter
    public static Date fromString(String value) {
        if (value == null) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @TypeConverter
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return sdf.format(date);
    }
}

