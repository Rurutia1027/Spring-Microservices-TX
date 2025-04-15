package com.mini.payment.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * String Util
 */
public final class StringUtil {

    private static final Log LOG = LogFactory.getLog(StringUtil.class);

    private StringUtil() {
    }

    /**
     * Verify whether string is empty
     *
     * @param str
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * Verify whether given object array is empty
     *
     * @param obj
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    /**
     * Verify whether object is empty
     *
     * @param obj
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }

    /**
     * verify whether collection is empty
     *
     * @param obj
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(List<?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * Verify whether given map is empty
     *
     * @param obj
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * Fetch given file suffix and return
     *
     * @param fileName
     * @return String
     * @throws
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Fetch removed '-' uuid string and return
     *
     * @return uuid.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Fetch uuid that contains '-' and return
     * <p>
     *
     * @return uuid.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Verify whether a given string's characters are all numeric
     *
     * @param str
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }

    /**
     * Fetch byte number of the given content that encoded via utf-8
     *
     * @param content
     * @return int
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                LOG.error(e);
            }
        }
        return size;
    }

    /**
     * @param param
     * @return String
     * @throws
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<String>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }

    /**
     * Verify whether given object is not null
     *
     * @param obj
     * @return boolean
     */
    public static boolean isNotNull(Object obj) {
        if (obj != null && obj.toString() != null && !"".equals(obj.toString().trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static List<String> makeStringList(Object value) {
        if (value == null) {
            value = "";
        }
        List<String> result = new ArrayList<String>();
        if (value.getClass().isArray()) {
            for (int j = 0; j < Array.getLength(value); j++) {
                Object obj = Array.get(value, j);
                result.add(obj != null ? obj.toString() : "");
            }
            return result;
        }

        if (value instanceof Iterator) {
            Iterator it = (Iterator) value;
            while (it.hasNext()) {
                Object obj = it.next();
                result.add(obj != null ? obj.toString() : "");
            }
            return result;
        }

        if (value instanceof Collection) {
            for (Object obj : (Collection) value) {
                result.add(obj != null ? obj.toString() : "");
            }
            return result;
        }

        if (value instanceof Enumeration) {
            Enumeration enumeration = (Enumeration) value;
            while (enumeration.hasMoreElements()) {
                Object obj = enumeration.nextElement();
                result.add(obj != null ? obj.toString() : "");
            }
            return result;
        }

        result.add(value.toString());
        return result;
    }
}
