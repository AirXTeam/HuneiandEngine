package cn.zry551.plugin.huneiand.Tools;

import java.util.UUID;

public class UuidT {
    public static String GetUUID(){
        String UStr = UUID.randomUUID().toString();
        return UStr;
    }
}
