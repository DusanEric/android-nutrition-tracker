package rs.raf.vezbe11.data.datasources.local;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class ListTypeConverter {
    @TypeConverter
    public static List<String> fromString(String value) {
        return value == null ? null : Arrays.asList(value.split(","));
    }

    @TypeConverter
    public static String listToString(List<String> list) {
        return list == null ? null : String.join(",", list);
    }
}
