package cn.zry551.plugin.huneiand.UDP;

import cn.zry551.plugin.huneiand.Huneiand;
import cn.zry551.plugin.huneiand.SendMSG;
import cn.zry551.plugin.huneiand.Temp;
import cn.zry551.plugin.huneiand.Tools.B64T;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.inventory.ItemStack;

import javax.xml.crypto.Data;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DataSyn {


    public static void run(Huneiand JP,String MSG) {
        try{
            if(JP.GDebugMode){
                JP.Logger.warning("Debug : MSG : " + MSG);
            }
            Temp TP = new Temp();
            TP.S0 = MSG;
            TP.SA0 = TP.S0.split("&");
            if(TP.SA0.length == 2){
                if(TP.SA0[0].equals("#!!HUNEIAND#MSG#")){
                    TP.SA1 = TP.SA0[1].split("@");
                    if(TP.SA1.length == 2){
                        try{
                            Temp TP2 = new Temp();
                            switch (TP.SA1[0]) {
                                case "Player_SendChat":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).chat(B64T.Decode(TP.SA11[1]));
                                    break;
                                case "Player_RunCommand":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).performCommand(B64T.Decode(TP.SA11[1]));
                                    break;
                                case "Player_Kick":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).kick(Component.newline().content(B64T.Decode(TP.SA11[1])));
                                    //BanPlayer
                                case "Player_Ban":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).banPlayer(B64T.Decode(TP.SA11[1]));
                                case "Player_Ban_Full":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).banPlayerFull(B64T.Decode(TP.SA11[1]));
                                case "Player_Ban_IP":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).banPlayerIP(B64T.Decode(TP.SA11[1]));
                                    //Ban Player Plus
                                case "Player_Ban_Plus":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[2];//13 * current Time
                                    TP2.S22 = TP.SA11[3];//soursedata
                                    TP2.S23 = TP.SA11[4];//kick now?
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).banPlayer(B64T.Decode(TP.SA11[1]), new Date(Long.parseLong(TP2.S21)),
                                            DataTools.StringCheckNull(TP2.S22), DataTools.StringToBoolean(TP2.S23));
                                case "Player_Ban_Full_Plus":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[2];//13 * current Time
                                    TP2.S22 = TP.SA11[3];//soursedata
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).banPlayerFull(B64T.Decode(TP.SA11[1]), new Date(Long.parseLong(TP2.S21)),
                                            DataTools.StringCheckNull(TP2.S22));
                                case "Player_Ban_IP_Plus":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[2];//13 * current Time
                                    TP2.S22 = TP.SA11[3];//soursedata
                                    TP2.S23 = TP.SA11[4];//kick now?
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).banPlayerIP(B64T.Decode(TP.SA11[1]), new Date(Long.parseLong(TP2.S21)),
                                            DataTools.StringCheckNull(TP2.S22), DataTools.StringToBoolean(TP2.S23));
                                    //DeBan Part
                                case "Player_ApplyMending":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[1];//number
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).applyMending(Integer.parseInt(TP2.S21));
                                case "Player_BreakBlock":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = B64T.Decode(TP.SA11[1]);//WorldName
                                    TP2.S22 = TP.SA11[2];//x
                                    TP2.S23 = TP.SA11[3];//y
                                    TP2.S24 = TP.SA11[4];//z
                                    TP2.S25 = TP.SA11[5];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).breakBlock(JP.getServer().getWorld(TP2.S21).getBlockAt(
                                            Integer.parseInt(TP2.S22),
                                            Integer.parseInt(TP2.S23),
                                            Integer.parseInt(TP2.S24)
                                    ));
                                    DataTools.SendReturnMSG(TP2.S25, "Player_BreakBlock", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_CanSee":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).canSee(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])));
                                    DataTools.SendReturnMSG(TP2.S22, "Player_CanSee", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_GetName":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = B64T.Encode(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getName());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetName", new String[]{TP2.S31});
                                case "Player_GetAddress":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = B64T.Encode("NetAddress@" + JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getAddress().getAddress().getHostName() + "#" +
                                            JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getAddress().getAddress().getHostAddress() + "#" +
                                            String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getAddress().getPort()));
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetAddress", new String[]{TP2.S31});
                                case "Player_GetAffectsSpawning":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getAffectsSpawning();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetAffectsSpawning", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_GetAllowFlight":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getAllowFlight();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetAllowFlight", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_GetBedSpawnLocation":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    if (JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getBedSpawnLocation() != null){
                                        TP2.S21 = B64T.Encode("BlockLocation@" + JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getBedSpawnLocation().getWorld().getName() + "#" +
                                                String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getBedSpawnLocation().getBlockX()) + "#" +
                                                String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getBedSpawnLocation().getBlockY()) + "#" +
                                                String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getBedSpawnLocation().getBlockZ())
                                        );
                                    }else{
                                        TP2.S21 = B64T.Encode("BlockLocation@%Null");
                                    }
                                    DataTools.SendReturnMSG(TP2.S22,"Player_GetBedSpawnLocation",new String[]{TP2.S21});
                                case "Player_GetClientBrandName":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = B64T.Encode(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getClientBrandName());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetClientBrandName", new String[]{TP2.S31});
                                case "Player_GetClientViewDistance":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getClientViewDistance());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetClientViewDistance", new String[]{TP2.S31});
                                case "Player_GetCompassTarget":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    if (JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCompassTarget() != null){
                                        TP2.S21 = B64T.Encode("BlockLocation@" + JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCompassTarget().getWorld().getName() + "#" +
                                                String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCompassTarget().getBlockX()) + "#" +
                                                String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCompassTarget().getBlockY()) + "#" +
                                                String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCompassTarget().getBlockZ())
                                        );
                                    }else{
                                        TP2.S21 = B64T.Encode("BlockLocation@%Null");
                                    }
                                    DataTools.SendReturnMSG(TP2.S22,"Player_GetCompassTarget",new String[]{TP2.S21});
                                case "Player_GetCooldownPeriod":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCooldownPeriod());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetCooldownPeriod", new String[]{TP2.S31});
                                case "Player_GetCooledAttackStrength":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[2];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getCooledAttackStrength(Float.parseFloat(TP.SA11[1])));
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetCooledAttackStrength", new String[]{TP2.S31});
                                case "Player_GetDisPlayName":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = B64T.Encode(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getDisplayName());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetDisPlayName", new String[]{TP2.S31});
                                case "Player_GetExp":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getExp());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetExp", new String[]{TP2.S31});
                                case "Player_GetFlySpeed":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getFlySpeed());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetFlySpeed", new String[]{TP2.S31});
                                case "Player_GetHealthScale":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getHealthScale());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetHealthScale", new String[]{TP2.S31});
                                case "Player_GetLevel":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getLevel());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetLevel", new String[]{TP2.S31});
                                /*case "Player_GetLocale":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getLocale();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetLocale", new String[]{TP2.S31});*/
                                case "Player_GetPing":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPing());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetPing", new String[]{TP2.S31});
                                case "Player_NameToUUID":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = B64T.Encode(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPlayerProfile().getId().toString());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_NameToUUID", new String[]{TP2.S31});
                                case "Player_UUIDToName":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = B64T.Encode(JP.getServer().getPlayer(UUID.fromString(B64T.Decode(TP.SA11[0]))).getPlayerProfile().getName());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_UUIDToName", new String[]{TP2.S31});
                                case "Player_GetPlayerTime":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPlayerTime());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetPlayerTime", new String[]{TP2.S31});
                                case "Player_GetPlayerTimeOffset":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPlayerTimeOffset());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetPlayerTimeOffset", new String[]{TP2.S31});
                                case "Player_GetPlayerWeather":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPlayerWeather() == WeatherType.CLEAR){
                                        TP2.S31 = "WeatherType@CLEAR";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPlayerWeather() == WeatherType.DOWNFALL){
                                        TP2.S31 = "WeatherType@DOWNFALL";
                                    }else{
                                        TP2.S31 = "WeatherType@%Null";
                                    }
                                    //TP2.S31 = String.valueOf();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetPlayerWeather", new String[]{TP2.S31});
                                case "Player_GetPreviousGameMode":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPreviousGameMode() == GameMode.ADVENTURE){
                                        TP2.S31 = "GameMode@ADVENTURE";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPreviousGameMode() == GameMode.CREATIVE){
                                        TP2.S31 = "GameMode@CREATIVE";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPreviousGameMode() == GameMode.SPECTATOR){
                                        TP2.S31 = "GameMode@SPECTATOR";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getPreviousGameMode() == GameMode.SURVIVAL){
                                        TP2.S31 = "GameMode@SURVIVAL";
                                    }else{
                                        TP2.S31 = "GameMode@%Null";
                                    }
                                    //TP2.S31 = String.valueOf();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetPreviousGameMode", new String[]{TP2.S31});
                                case "Player_GetResourcePackStatus":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getResourcePackStatus() == PlayerResourcePackStatusEvent.Status.ACCEPTED){
                                        TP2.S31 = "PlayerResourcePackStatus@ADVENTURE";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getResourcePackStatus() == PlayerResourcePackStatusEvent.Status.DECLINED){
                                        TP2.S31 = "PlayerResourcePackStatus@DECLINED";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getResourcePackStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD){
                                        TP2.S31 = "PlayerResourcePackStatus@FAILED_DOWNLOAD";
                                    }else if(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getResourcePackStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED){
                                        TP2.S31 = "PlayerResourcePackStatus@SUCCESSFULLY_LOADED";
                                    }else{
                                        TP2.S31 = "PlayerResourcePackStatus@%Null";
                                    }
                                    //TP2.S31 = String.valueOf();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetResourcePackStatus", new String[]{TP2.S31});
                                case "Player_GetSendViewDistance":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getSendViewDistance());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetSendViewDistance", new String[]{TP2.S31});
                                case "Player_GetSimulationDistance":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getSimulationDistance());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetSimulationDistance", new String[]{TP2.S31});
                                case "Player_GetTotalExperience":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getTotalExperience());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetTotalExperience", new String[]{TP2.S31});
                                case "Player_GetViewDistance":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getViewDistance());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetViewDistance", new String[]{TP2.S31});
                                case "Player_GetWalkSpeed":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.S31 = String.valueOf(JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).getWalkSpeed());
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetWalkSpeed", new String[]{TP2.S31});

                                //玩家经验部分
                                case "Player_GiveExp":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[1];//number
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).giveExp(Integer.parseInt(TP2.S21));
                                case "Player_GiveExp_Plus":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[1];//number
                                    TP2.S22 = TP.SA11[2];//fix now?
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).giveExp(Integer.parseInt(TP2.S21),DataTools.StringToBoolean(TP2.S22));
                                case "Player_GiveExpLevels":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S21 = TP.SA11[1];//number
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).giveExpLevels(Integer.parseInt(TP2.S21));


                                case "Player_HasResourcePack":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).hasResourcePack();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_GetAllowFlight", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_HideSelf":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    //TP2.S21 = TP.SA11[1];//number
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).hidePlayer(JP,JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])));
                                case "Player_IsAllowingServerListings":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isAllowingServerListings();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsAllowingServerListings", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_IsFlying":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isFlying();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsFlying", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_IsHealthScaled":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isHealthScaled();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsHealthScaled", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_IsPlayerTimeRelative":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isPlayerTimeRelative();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsPlayerTimeRelative", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_IsSleepingIgnored":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isSleepingIgnored();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsSleepingIgnored", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_IsSneaking":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isSneaking();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsSneaking", new String[]{DataTools.BooleanToString(TP2.B21)});
                                case "Player_IsSprinting":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    TP2.S22 = TP.SA11[1];//return addr
                                    TP2.B21 = JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).isSprinting();
                                    DataTools.SendReturnMSG(TP2.S22, "Player_IsSprinting", new String[]{DataTools.BooleanToString(TP2.B21)});

                                case "Player_LoadData":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).loadData();


                                case "Player_ReSetCooldown":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).resetCooldown();
                                case "Player_ReSetPlayerTime":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).resetPlayerTime();
                                case "Player_ReSetPlayerWeather":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).resetPlayerWeather();
                                case "Player_ReSetTitle":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).resetTitle();
                                case "Player_SaveData":
                                    TP.SA11 = TP.SA1[1].split("#");
                                    JP.getServer().getPlayer(B64T.Decode(TP.SA11[0])).saveData();






































                                default:
                                    //pass










                            }




                        }catch (Exception ex){
                            if(JP.GDebugMode) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
            //JP.getServer().getPlayer("").chat("");
        }catch (Exception ex){
            if(JP.GDebugMode) {
                ex.printStackTrace();
            }

        }

    }


}
