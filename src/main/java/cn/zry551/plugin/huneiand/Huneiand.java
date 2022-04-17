package cn.zry551.plugin.huneiand;

import cn.zry551.plugin.huneiand.Tools.B64T;
import cn.zry551.plugin.huneiand.UDP.DataEvent;
import cn.zry551.plugin.huneiand.UDP.TickRun;
import cn.zry551.plugin.huneiand.UDP.UServer;
import jdk.javadoc.internal.tool.Start;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getPluginManager;

public final class Huneiand extends JavaPlugin {
    public Huneiand Self = this;
    public Logger Logger = getLogger();
    public FileConfiguration Config = getConfig();

    public boolean StartUDP = true;
    public String LAddr = "127.0.0.1";
    public int LPort = 25560;
    public List<String> RAddr = new ArrayList<>();
    public List<Integer> RPort = new ArrayList<>();
    public List<String> RAddrMS = new ArrayList<>();
    public List<Integer> RPortMS = new ArrayList<>();

    public String ConfigFileDir = "plugins/Huneiand";
    public String ConfigFilePath = "plugins/Huneiand/Config.yml";

    public TickRun DataSynThread = new TickRun(this);


    public boolean GDebugMode = true;

    @Override
    public void onEnable() {
        // Plugin startup logic
        File CfgDir = new File(ConfigFileDir);
        File CfgFile = new File(ConfigFilePath);

            if(!CfgDir.exists()){
                CfgDir.mkdirs();
            }
            if(!CfgFile.exists()){
                try {
                    CfgFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        try {
            getConfig().load(CfgFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }



        getLogger().info("Huneiand Plugin is Enable !");
        LAddr = getConfig().getString("ListenAddr","127.0.0.1");
        LPort = getConfig().getInt("ListenPort",25560);
        RAddr = getConfig().getStringList("RemoteAddr");
        RPort = getConfig().getIntegerList("RemotePort");
        StartUDP = getConfig().getBoolean("StartUDP",true);
        if(LAddr.equals("127.0.0.1")){
            LAddr = "127.0.0.1";
            getConfig().set("ListenAddr","127.0.0.1");
        }
        if(LPort == 25560){
            LPort = 25560;
            getConfig().set("ListenPort",25560);
        }
        if(StartUDP == true){
            StartUDP = true;
            getConfig().set("StartUDP",true);
        }
        if(RAddr == null | RAddr == RAddrMS | RAddr.size() == 0){
            List<String> RAddrs = new ArrayList<>();
            RAddrs.add("127.0.0.1");
            //getConfig().set("RemoteAddr",RAddrs);
            getConfig().set("RemoteAddr",new ArrayList<>());
        }
        if(RPort == null | RPort == RPortMS | RPort.size() == 0){
            List<Integer> RPorts = new ArrayList<>();
            RPorts.add(30000);
            //getConfig().set("RemotePort",RPorts);
            getConfig().set("RemotePort",new ArrayList<>());
        }
        try {
            getConfig().save(CfgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }






        getLogger().info("Config File Read Finish !");
        try{
            if(StartUDP) {
                getLogger().info("Loading UDP Server ......");
                UServer US = new UServer();
                US.Log = getLogger();
                US.JP = Self;
                US.LAddr = this.LAddr;
                US.LPort = this.LPort;
                US.RAddr = this.RAddr;
                US.RPort = this.RPort;
                US.start();//启动新线程，不然会导致服务端无法处理其他事件
            }
        }catch (Exception ex){
            getLogger().info("UDP Server Load Failed !");
            ex.printStackTrace();
        }


        try{
            getLogger().info("Registering Data Event ......");
            getPluginManager().registerEvents(new DataEvent(), this);
        }catch (Exception ex){
            getLogger().info("Data Event Register Failed !");
            ex.printStackTrace();
        }

        try{
            getLogger().info("Registering Data Runnable ......");
           BukkitTask Tasks = DataSynThread.runTaskTimer(this, 0, 2L);
        }catch (Exception ex){
            getLogger().info("Data Runnable Register Failed !");
            ex.printStackTrace();
        }




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Huneiand Plugin is Disable !");
    }





}

