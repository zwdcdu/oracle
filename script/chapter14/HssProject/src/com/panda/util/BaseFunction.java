package com.panda.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseFunction {
    public static Date StringToDate(String str) throws Exception{
        if (str.contains("/")) {
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(str);
            return date;
        }
        Date date =  new SimpleDateFormat("yyyy-MM-dd").parse(str);
        return date;
    }
}
