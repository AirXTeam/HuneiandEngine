package cn.zry551.plugin.huneiand.UDP;

import cn.zry551.plugin.huneiand.DataSaveClass;
import cn.zry551.plugin.huneiand.Huneiand;
import cn.zry551.plugin.huneiand.Temp;
import cn.zry551.plugin.huneiand.Tools.B64T;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class DataEvent implements Listener {
    @EventHandler
    public void AllEvents(ServerLoadEvent e) {
        /*Huneiand JP = DataSaveClass.HP;

        BukkitRunnable BKR = new BukkitRunnable(){
            @Override
            public void run() {
                while(true) {
                    for (Object MSG : DataSaveClass.SynEvents.toArray()) {
                        try {
                            if (JP.GDebugMode) {
                                JP.Logger.warning("Debug : MSG : " + MSG);
                            }
                            DataSaveClass.SynEvents.remove(MSG);
                            Temp TP = new Temp();
                            TP.S0 = (String) MSG;
                            TP.SA0 = TP.S0.split("&");
                            if (TP.SA0.length == 2) {
                                if (TP.SA0[0].equals("#!!HUNEIAND#MSG#")) {
                                    TP.SA1 = TP.SA0[1].split("@");
                                    if (TP.SA1.length == 2) {
                                        try {
                                            switch (TP.SA1[0]) {
                                                case "PlayerChat":
                                                    TP.SA11 = TP.SA1[1].split("#");
                                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).chat(B64T.Decode(TP.SA11[1]));
                                                    //JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).performCommand(B64T.Decode(TP.SA11[1]));
                                                    //JP.CallEvent.Player_Chat(B64T.Decode(TP.SA11[0]),B64T.Decode(TP.SA11[1]));
                                                    //JP.getServer().
                                                    //break;


                                                default:
                                                    //pass



                                            }


                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                            //JP.getServer().getPlayer("").chat("");
                        } catch (Exception ex) {
                            ex.printStackTrace();

                        }
                    }
                }
            }
        };
        BKR.runTaskAsynchronously(JP);*/

    }
}
