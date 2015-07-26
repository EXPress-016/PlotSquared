package com.intellectualcrafters.plot.util;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class StringMan {
    public static String replaceFromMap(String string, Map<String, String> replacements) {
        StringBuilder sb = new StringBuilder(string);
        int size = string.length();
        for (Entry<String, String> entry : replacements.entrySet()) {
            if (size == 0) {
                break;
            }
            String key = entry.getKey();
            String value = entry.getValue();
            int start = sb.indexOf(key, 0);
            while (start > -1) {
                int end = start + key.length();
                int nextSearchStart = start + value.length();
                sb.replace(start, end, value);
                size -= end - start;
                start = sb.indexOf(key, nextSearchStart);
            }
        }
        return sb.toString();
    }
    
    public static String replaceAll(String string, Object... pairs) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < pairs.length; i+=2) {
            String key = pairs[i] + "";
            String value = pairs[i + 1] + "";
            int start = sb.indexOf(key, 0);
            while (start > -1) {
                int end = start + key.length();
                int nextSearchStart = start + value.length();
                sb.replace(start, end, value);
                start = sb.indexOf(key, nextSearchStart);
            }
        }
        return sb.toString();
    }
    
    public static boolean isAlphanumeric(String str) {
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isAlpha(String str) {
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if ((c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
                return false;
            }
        }
        return true;
    }
    
    public static String join(Collection<?> collection, String delimiter) {
        return join(collection.toArray(), delimiter);
    }
    
    public static String join(Collection<?> collection, char delimiter) {
        return join(collection.toArray(), delimiter + "");
    }
    
    public static boolean isAsciiPrintable(char c) {
        return (c >= ' ') && (c < '');
    }
    
    public static boolean isAsciiPrintable(String s) {
        for (char c: s.toCharArray()) {
            if (!isAsciiPrintable(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static int getLevenshteinDistance(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }
        if (n > m) {
            String tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }
        int p[] = new int[n+1];
        int d[] = new int[n+1];
        int _d[];
        int i;
        int j;
        char t_j;
        int cost;
        for (i = 0; i<=n; i++) {
            p[i] = i;
        }
        for (j = 1; j<=m; j++) {
            t_j = t.charAt(j-1);
            d[0] = j;

            for (i=1; i<=n; i++) {
                cost = s.charAt(i-1)==t_j ? 0 : 1;
                d[i] = Math.min(Math.min(d[i-1]+1, p[i]+1),  p[i-1]+cost);
            }
            _d = p;
            p = d;
            d = _d;
        }
        return p[n];
    }
    
    public static String join(Object[] array, String delimiter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = array.length; i < j; i++) {
            if (i > 0) {
                result.append(delimiter);
            }
            result.append(array[i]);
        }
        return result.toString();
    }
    
    public static boolean isEqual(String a, String b ) {
        return (a == b || (a.length() == b.length() && a.hashCode() == b.hashCode() && a.equals(b)));
    }

    public static String repeat(String s, int n) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
