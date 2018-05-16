package com.yuntian.newsframe.storage;

/**
 * @author guangleilei
 * @version 1.0 2017-04-06
 */
public class ApiConstants {

    // 房产id
    public static final String HOUSE_ID = "5YyX5Lqs";

    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";

    // 头条id
    public static final String HEADLINE_ID = "T1348647909107";


    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    public static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";


    /**
     * 新闻id获取类型
     *
     * @param id 新闻id
     * @return 新闻类型
     */
    public static String getType(String id) {
        switch (id) {
            case HEADLINE_ID:
                return HEADLINE_TYPE;
            case HOUSE_ID:
                return HOUSE_TYPE;
            default:
                break;
        }
        return OTHER_TYPE;
    }



}
