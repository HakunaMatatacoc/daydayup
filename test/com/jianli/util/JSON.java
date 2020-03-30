package com.jianli.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Slf4j
public class JSON {
    public static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public static DateFormat defaultDf = new StdDateFormat();
    public static Date NULL_DATE = new Date(0);
    public static String NULL_DATE_STR = "0000-00-00";
    public static String NULL_DATETIME_STR = "0000-00-00 00:00:00";
    public static String NULL_TIME_STR = "00:00:00";

    public static boolean isNull(Date date) {
        return date.getTime() == NULL_DATE.getTime();
    }

    public static ObjectMapper getMapper(boolean snake, boolean sortByAlphabetically) {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new DateFormat() {
            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
                if (date instanceof java.sql.Date) {
                    if (isNull(date)) {
                        toAppendTo.append(NULL_DATE_STR);
                    } else {
                        toAppendTo.append(dateFormat.format(date));
                    }
                } else {
                    if (isNull(date)) {
                        toAppendTo.append(NULL_DATETIME_STR);
                    } else {
                        toAppendTo.append(datetimeFormat.format(date));
                    }
                }
                return toAppendTo;
            }

            @Override
            public Date parse(String source, ParsePosition pos) {
                if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", source)) {
                    if (NULL_DATETIME_STR.equals(source)) {
                        return new Date(NULL_DATE.getTime());
                    } else {
                        return datetimeFormat.parse(source, pos);
                    }
                } else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", source)) {
                    if (NULL_DATE_STR.equals(source)) {
                        return new Date(NULL_DATE.getTime());
                    } else {
                        return dateFormat.parse(source, pos);
                    }
                } else {
                    return defaultDf.parse(source, pos);
                }
            }
        };
        df.setCalendar(datetimeFormat.getCalendar());
        df.setNumberFormat(datetimeFormat.getNumberFormat());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        if (snake) {
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (sortByAlphabetically) {
            mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        }
        return mapper;
    }

    public static String stringify(Object object) {
        return stringify(object, true, false);
    }

    public static String stringify(Object object, boolean snake, boolean sortByAlphabetically) {
        try {
            if (object == null) {
                return null;
            }
            return getMapper(snake, sortByAlphabetically).writeValueAsString(object);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T parse(String json, Class<T> returnType) {
        return parse(json, returnType, true);
    }

    public static <T> T parse(String json, Class<T> returnType, boolean snake) {
        try {
            return getMapper(snake, false).readValue(json, returnType);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static <T> T parse(String json, TypeReference<T> typeRef) {
        return parse(json, typeRef, true);
    }

    public static <T> T parse(String json, TypeReference<T> typeRef, boolean snake) {
        try {
            return getMapper(snake, false).readValue(json, typeRef);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
