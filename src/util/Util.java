/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ding
 */
public final class Util {

    public static String arrayToString(ArrayList<String> array) {
        int i = 0;
        String str = "a:" + array.size() + ":{";
        for (String s : array) {
            str += "i:" + i + ";s:" + s.length() + ':' + '"' + s + '"' + ";";
            i++;
        }
        str += "}";
        return str;
    }

    public static ArrayList<String> stringToArray(String str) {
        ArrayList<String> array = new ArrayList<>();
        int n = Integer.parseInt(str.split(":")[1]);
        String[] sp = str.split('"' + "");
        for (int i = 1; i < sp.length; i += 2) {
            array.add(sp[i]);
        }
        return array;
    }

    public static boolean validateEmail(String emailAddress) {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }

    //Hashing Password
    public static String hashpw(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt(13));
    }

    //checking Password
    public static Boolean checkpw(String plainpw, String hashed) {
        return BCrypt.checkpw(plainpw, hashed);
    }
}
