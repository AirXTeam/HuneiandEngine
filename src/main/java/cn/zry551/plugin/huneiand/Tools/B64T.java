package cn.zry551.plugin.huneiand.Tools;

//import java.util.Base64;


import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

public class B64T {
    /**
     * Java8中的Base64编码
     * @param str
     * @return
     */
    public static String Encode(String str) {
        try {
            return new String(Base64.encodeBase64(str.getBytes("UTF-8")));
            //return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Java8中的Base64解码
     * @param str
     * @return
     */
    public static String Decode(String str) {
        byte[] result = Base64.decodeBase64(str.getBytes());
        return new String(result);
    }
}
