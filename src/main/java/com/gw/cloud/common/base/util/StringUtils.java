package com.gw.cloud.common.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author CGB
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 空字符
     */
    public static final String STR_EMPTY = "";
    /**
     * null字符
     */
    public static final String STR_NULL = "null";
    /**
     * 空格
     */
    public static final String STR_SPACE = " ";
    /**
     * 下划线
     */
    public static final String STR_UNDERLINE = "_";
    /**
     * 连字号
     */
    public static final String STR_HYPHEN = "-";
    /**
     * 竖线
     */
    public static final String STR_VERTICAL_LINE = "\\|";
    /**
     * 斜杠
     */
    public static final String STR_SLASH = "/";
    /**
     * 星号
     */
    public static final String STR_STAR = "*";
    /**
     * 井号
     */
    public static final String STR_SHARP = "#";
    /**
     * 顿号
     */
    public static final String STR_DAWN = "、";
    /**
     * 冒号
     */
    public static final String STR_COLON = ":";
    /**
     * 等号
     */
    public static final String STR_EQUAL = "=";
    /**
     * 百分号
     */
    public static final String STR_PERCENT = "%";
    /**
     * 与字符
     */
    public static final String STR_AMPERSAND = "&";
    /**
     * 英文逗号
     */
    public final static String STR_COMMA = ",";
    /**
     * 英文句号
     */
    public static final String STR_PERIOD = ".";
    /**
     * 英文分号
     */
    public static final String STR_SEMICOLON = ";";
    /**
     * 英文单引号
     */
    public static final String STR_SINGLE_QUOTE = "'";
    /**
     * 制表符
     */
    public static final String STR_TAB = "\\t";
    /**
     * 通用换行符
     */
    public static final String STR_LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    /**
     * 文件分隔符
     */
    public static final String STR_FILE_SEPARATOR = System.getProperty("file.separator", "\\");
    /**
     * 路径分隔符
     */
    public static final String STR_PATH_SEPARATOR = System.getProperty("path.separator", ";");
    /**
     * 正则表达式：匹配数字和英文字母
     */
    public static final String EXPRESSION_NUMBER_AND_LETTER = "[0-9a-zA-Z]+";

    /**
     * 一次性判断多个或单个对象为空。
     *
     * @param objects
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean isBlank(Object... objects) {
        Boolean result = false;
        for (Object object : objects) {
            if (null == object || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val.toLowerCase();
    }

    /**
     * 一次性判断多个或单个对象不为空。
     *
     * @param objects
     * @return 只要有一个元素不为Blank，则返回true
     */
    public static boolean isNotBlank(Object... objects) {
        return !isBlank(objects);
    }

    public static boolean isBlank(String... objects) {
        Object[] object = objects;
        return isBlank(object);
    }

    public static boolean isNotBlank(String... objects) {
        Object[] object = objects;
        return !isBlank(object);
    }

    public static boolean isBlank(String str) {
        Object object = str;
        return isBlank(object);
    }

    public static boolean isNotBlank(String str) {
        Object object = str;
        return !isBlank(object);
    }

    /**
     * 判断一个字符串在数组中存在几个
     *
     * @param baseStr
     * @param strings
     * @return
     */
    public static int indexOf(String baseStr, String[] strings) {

        if (null == baseStr || baseStr.length() == 0 || null == strings) {
            return 0;
        }

        int i = 0;
        for (String string : strings) {
            boolean result = baseStr.equals(string);
            i = result ? ++i : i;
        }
        return i;
    }

    public static String trimToEmpty(Object str) {
        return (isBlank(str) ? "" : str.toString().trim());
    }

    /**
     * 将 Strig  进行 BASE64 编码
     *
     * @param str [要编码的字符串]
     * @param bf  [true|false,true:去掉结尾补充的'=',false:不做处理]
     * @return
     */
    public static String getBASE64(String str, boolean... bf) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String base64 = new sun.misc.BASE64Encoder().encode(str.getBytes());
        //去掉 '='
        if (isBlank(bf) && bf[0]) {
            base64 = base64.replaceAll("=", "");
        }
        return base64;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     **/
    public static String getStrByBASE64(String s) {
        if (isBlank(s)) {
            return STR_EMPTY;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return STR_EMPTY;
        }
    }

    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     *
     * @param map
     * @return
     */
    public static String mapToGet(Map<? extends Object, ? extends Object> map) {
        String result = STR_EMPTY;
        if (map == null || map.size() == 0) {
            return result;
        }
        Set<? extends Object> keys = map.keySet();
        for (Object key : keys) {
            result += ((String) key + STR_EQUAL + (String) map.get(key) + "&");
        }

        return isBlank(result) ? result : result.substring(0, result.length() - 1);
    }

    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     *
     * @param args
     * @return
     */
    public static Map<String, ? extends Object> getToMap(String args) {
        if (isBlank(args)) {
            return null;
        }
        args = args.trim();
        //如果是?开头,把?去掉
        if (args.startsWith("?")) {
            args = args.substring(1, args.length());
        }
        String[] argsArray = args.split("&");

        Map<String, Object> result = new HashMap<String, Object>();
        for (String ag : argsArray) {
            if (!isBlank(ag) && ag.indexOf("=") > 0) {

                String[] keyValue = ag.split("=");
                //如果value或者key值里包含 "="号,以第一个"="号为主 ,如  name=0=3  转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.

                String key = keyValue[0];
                String value = "";
                for (int i = 1; i < keyValue.length; i++) {
                    value += keyValue[i] + "=";
                }
                value = value.length() > 0 ? value.substring(0, value.length() - 1) : value;
                result.put(key, value);

            }
        }

        return result;
    }

    /**
     * 转换成Unicode
     *
     * @param str
     * @return
     */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            int v = str.charAt(i);
            if (v >= 19968 && v <= 171941) {
                as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
                s1 = s1 + "\\u" + as[i];
            } else {
                s1 = s1 + str.charAt(i);
            }
        }
        return s1;
    }

    /**
     * 合并数据
     *
     * @param v
     * @return
     */
    public static String merge(Object... v) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
        }
        return sb.toString();
    }

    /**
     * 字符串转urlcode
     *
     * @param value
     * @return
     */
    public static String strToUrlcode(String value) {
        try {
            value = java.net.URLEncoder.encode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            logger.error("字符串转换为URLCode失败,value:" + value, e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * urlcode转字符串
     *
     * @param value
     * @return
     */
    public static String urlcodeToStr(String value) {
        try {
            value = java.net.URLDecoder.decode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            logger.error("URLCode转换为字符串失败;value:" + value, e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断字符串是否包含汉字
     *
     * @param txt
     * @return
     */
    public static Boolean containsCN(String txt) {
        if (isBlank(txt)) {
            return false;
        }
        for (int i = 0; i < txt.length(); i++) {

            String bb = txt.substring(i, i + 1);

            boolean cc = Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc) {
                return cc;
            }
        }
        return false;
    }

    /**
     * 去掉HTML代码
     *
     * @param news
     * @return
     */
    public static String removeHtml(String news) {
        String s = news.replaceAll("amp;", "").replaceAll("<", "<").replaceAll(">", ">");

        Pattern pattern = Pattern.compile("<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);
        String str = matcher.replaceAll("");

        Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
        Matcher matcher2 = pattern2.matcher(str);
        String strhttp = matcher2.replaceAll(" ");


        String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
                + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?"
                + "("
                + "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
                + "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*"
                + ")"
                + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
                + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
        Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
        Matcher matchhttp = p1.matcher(strhttp);
        String strnew = matchhttp.replaceAll("").replaceAll("(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");


        Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
        Matcher matchercomma = patterncomma.matcher(strnew);
        String strout = matchercomma.replaceAll(" ");
        String answer = strout.replaceAll("[\\pP‘’“”]", " ")
                .replaceAll("\r", " ").replaceAll("\n", " ")
                .replaceAll("\\s", " ").replaceAll("　", "");


        return answer;
    }

    /**
     * 把数组的空数据去掉
     *
     * @param array
     * @return
     */
    public static List<String> array2Empty(String[] array) {
        List<String> list = new ArrayList<String>();
        for (String string : array) {
            if (StringUtils.isNotBlank(string)) {
                list.add(string);
            }
        }
        return list;
    }

    /**
     * 把数组转换成set
     *
     * @param array
     * @return
     */
    public static <T> Set<T> array2Set(T[] array) {
        Set<T> set = new TreeSet<T>();
        for (T id : array) {
            if (null != id) {
                set.add(id);
            }
        }
        return set;
    }

    /**
     * serializable toString
     *
     * @param serializable
     * @return
     */
    public static String toString(Serializable serializable) {
        if (null == serializable) {
            return null;
        }
        try {
            return (String) serializable;
        } catch (Exception e) {
            return serializable.toString();
        }
    }

    /**
     * 判断字符串是否为null或空
     *
     * @param str 输入的字符串
     * @return 判断结果
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || STR_EMPTY.equals(str) || STR_NULL.equals(str);
    }

    /**
     * 判断字符串是否为null、空或仅由空白字符构成
     *
     * @param str 输入的字符串
     * @return 判断结果
     */
    public static boolean isNullOrWhiteSpace(String str) {
        return str == null || STR_EMPTY.equals(str.trim()) || STR_NULL.equals(str.trim());
    }

    /**
     * 判断数组是否包含为null、空或仅由空白字符构成的元素
     *
     * @param objs 输入的数组
     * @return 判断结果
     */
    public static boolean isContainNullOrWhiteSpace(Object... objs) {
        for (Object obj : objs) {
            if (isNullOrWhiteSpace(String.valueOf(obj))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 为指定字符串增加前缀和后缀
     *
     * @param str       输入的字符串
     * @param prefix    前缀
     * @param suffix    后缀
     * @param separator 分隔符
     * @return 转换结果
     */
    public static String addPrefixAndSuffix(String str, String prefix, String suffix, String separator) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix);
            if (separator != null) {
                sb.append(separator);
            }
        }
        sb.append(str);
        if (suffix != null) {
            if (separator != null) {
                sb.append(separator);
            }
            sb.append(suffix);
        }
        return sb.toString();
    }

    /**
     * 为指定字符串增加前缀
     *
     * @param str       输入的字符串
     * @param prefix    前缀
     * @param separator 分隔符
     * @return 转换结果
     */
    public static String addPrefix(String str, String prefix, String separator) {
        return addPrefixAndSuffix(str, prefix, null, separator);
    }

    /**
     * 为指定字符串增加前缀
     *
     * @param str    输入的字符串
     * @param prefix 前缀
     * @return 转换结果
     */
    public static String addPrefix(String str, String prefix) {
        return addPrefixAndSuffix(str, prefix, null, null);
    }

    /**
     * 为指定字符串增加后缀
     *
     * @param str       输入的字符串
     * @param suffix    后缀
     * @param separator 分隔符
     * @return 转换结果
     */
    public static String addSuffix(String str, String suffix, String separator) {
        return addPrefixAndSuffix(str, null, suffix, separator);
    }

    /**
     * 为指定字符串增加后缀
     *
     * @param str    输入的字符串
     * @param suffix 后缀
     * @return 转换结果
     */
    public static String addSuffix(String str, String suffix) {
        return addPrefixAndSuffix(str, null, suffix, null);
    }

    /**
     * 把数组转换为用逗号分隔的字符串
     *
     * @param array 输入数组
     * @return 用逗号分隔的字符串
     */
    public static String arrayToString(String[] array) {
        String str = STR_EMPTY;
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                str += array[i] + STR_COMMA;
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * 把List转换为用逗号分隔的字符串
     *
     * @param list 输入List
     * @return 用逗号分隔的字符串
     */
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i)).append(STR_COMMA);
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否全部由数字和字母组成
     *
     * @param str 输入的字符串
     * @return 判断结果
     */
    public static boolean isAllLetters(String str) {
        return str.matches(EXPRESSION_NUMBER_AND_LETTER);
    }

    /**
     * 下划线命名转换为驼峰命名
     *
     * @param param            输入的下划线命名字符串
     * @param isFirstUpperCase 首字母是否大写（大驼峰）
     * @return 驼峰命名字符串
     */
    public static String underlineToHump(String param, boolean isFirstUpperCase) {
        // 以下划线分隔字符串
        String[] params = param.split(STR_UNDERLINE);
        // 初始化返回对象
        StringBuilder result = new StringBuilder();
        // 遍历
        for (String str : params) {
            if (isAllLetters(str)) {
                if (isFirstUpperCase) {
                    result.append(str.substring(0, 1).toUpperCase());
                    result.append(str.substring(1).toLowerCase());
                } else {
                    if (result.length() == 0) {
                        result.append(str.toLowerCase());
                    } else {
                        result.append(str.substring(0, 1).toUpperCase());
                        result.append(str.substring(1).toLowerCase());
                    }
                }
            }
        }
        return result.toString();
    }
}
