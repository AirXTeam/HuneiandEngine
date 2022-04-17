package cn.zry551.plugin.huneiand.UDP;

import cn.zry551.plugin.huneiand.Huneiand;
import cn.zry551.plugin.huneiand.Temp;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class NotUse_DataThread extends Thread {
    private String MSG = "";
    private DatagramChannel channel;
    private SocketAddress skaddr;
    private Huneiand JP;

    public NotUse_DataThread(String msg, DatagramChannel channel, SocketAddress sa, Huneiand JPs) {
        this.MSG = msg;
        this.channel = channel;
        this.skaddr = sa;
        this.JP = JPs;
    }

    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !
    //This Class is not use !

    @Override
    public void run() {
        try{
            if(JP.GDebugMode){
                JP.Logger.warning("Debug : MSG : " + MSG);
            }
            Temp TP = new Temp();
            TP.S0 = this.MSG;
            TP.SA0 = TP.S0.split("&");
            if(TP.SA0.length == 2){
                if(TP.SA0[0].equals("#!!HUNEIAND#MSG#")){
                    TP.SA1 = TP.SA0[1].split("@");
                    if(TP.SA1.length == 2){
                        try{
                            switch (TP.SA1[0]){
                                case "PlayerChat":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    //JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).chat(B64T.Decode(TP.SA11[1]));
                                    //JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).performCommand(B64T.Decode(TP.SA11[1]));
                                    //JP.CallEvent.Player_Chat(B64T.Decode(TP.SA11[0]),B64T.Decode(TP.SA11[1]));
                                    //JP.getServer().
                                    break;


                                default:
                                    //pass













                            }




                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }
            //JP.getServer().getPlayer("").chat("");
        }catch (Exception ex){
            ex.printStackTrace();

        }

    }


}
