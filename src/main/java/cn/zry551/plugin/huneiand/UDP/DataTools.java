package cn.zry551.plugin.huneiand.UDP;

import cn.zry551.plugin.huneiand.SendMSG;
import cn.zry551.plugin.huneiand.Temp;
import cn.zry551.plugin.huneiand.Tools.B64T;

import javax.annotation.Nullable;

public class DataTools {
    public static boolean StringToBoolean(String Strs){
        if(Strs.equals("True")){
            return true;
        }else{
            return false;
        }
    }

    public static String BooleanToString(Boolean Bool){
        if(Bool == null){
            return "False";
        }
        if(Bool){
            return "True";
        }else{
            return "False";
        }
    }

    @Nullable
    public static String StringCheckNull(String Strs){
        if(Strs == null || Strs.equals("%Null")){
            return null;
        }else{
            return Strs;
        }
    }

    public static ReturnAddr HandleReturnAddr(String B64_Addr){
        ReturnAddr RA = new ReturnAddr();
        try{
            if(B64_Addr == null || B64_Addr.equals("%Null")){
                return new ReturnAddr();
            }
            Temp TM = new Temp();
            TM.S0 = B64T.Decode(B64_Addr);
            TM.SA0 = TM.S0.split("@");
            TM.SA1 = TM.SA0[1].split("#");
            if(TM.SA1.length == 3){
                RA.isNull = false;
                RA.RAddr = TM.SA1[0];
                RA.RPort = Integer.parseInt(TM.SA1[1]);
                RA.MSG_ID = Long.parseLong(TM.SA1[2]);
                return RA;
            }else{
                return new ReturnAddr();
            }
        }catch (Exception ex){
            return new ReturnAddr();
        }
    }

    public static boolean SendReturnMSG(String AddrStr,String FuncName,String[] Args){
        String Heads = "#!!HUNEIAND#RETURN#&" + FuncName + "@";
        try{
            ReturnAddr Addr = HandleReturnAddr(AddrStr);
            Heads = Heads + String.valueOf(Addr.MSG_ID) + "@";
            for (String Arg:Args) {
                Heads = Heads + Arg + "#";
            }
            if(Heads.substring(Heads.length()-1).equals("#")){
                Heads = Heads.substring(0,Heads.length()-1);
            }
            if(!Addr.isNull) {
                SendMSG.Send(Heads, Addr.RAddr, Addr.RPort);
            }

            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
