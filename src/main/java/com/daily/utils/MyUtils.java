package com.daily.utils;

import java.util.Random;

/**
 * @author a1002
 */
public class MyUtils {
    //num为随机数字字符串的长度
    public static StringBuilder getNumber(int num) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            sb.append(random.nextInt(10));
        }
        return sb;
    }

    //判断字符串是否为数字
    public static boolean isNumber(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
