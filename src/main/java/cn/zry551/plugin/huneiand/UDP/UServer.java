package cn.zry551.plugin.huneiand.UDP;

import cn.zry551.plugin.huneiand.DataSaveClass;
import cn.zry551.plugin.huneiand.Huneiand;
import cn.zry551.plugin.huneiand.Tools.B64T;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class UServer extends Thread{
    public Logger Log = Logger.getLogger("UDPServer");
    public Huneiand JP;

    public String LAddr = "127.0.0.1";
    public int LPort = 25560;
    public List<String> RAddr = new ArrayList<>();
    public List<Integer> RPort = new ArrayList<>();


    private static Charset charset = Charset.forName("utf8");
    public void NoSynUDPServer(){
        try{
            JP.Logger.info("UDP Server Start !");
            //创建channel
            DatagramChannel channel = DatagramChannel.open();
            //指定为非阻塞方式
            channel.configureBlocking(false);
            DatagramSocket socket = channel.socket();
            //绑定ip和端口
            InetSocketAddress address = new InetSocketAddress(JP.LAddr,JP.LPort);
            socket.bind(address);

            //创建监听器
            Selector selector = Selector.open();
            //注册读事件
            channel.register(selector, SelectionKey.OP_READ);

            //记录前一客户端地址，用于新起发送线程，仅示例，实际中用map等方式标记
            String preClientAddress = "";

            DataSaveClass.HP = JP;

            //读缓冲
            ByteBuffer readBuffer = ByteBuffer.allocate(32767);
            while (true) {
                //等事件出现
                if (selector.select() < 1) {
                    continue;
                }

                //获取发生的事件
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    //获取事件，移除正在处理的事件
                    SelectionKey key = it.next();
                    it.remove();

                    //读取消息
                    if(key.isReadable()){
                        DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                        readBuffer.clear();
                        SocketAddress sa = datagramChannel.receive(readBuffer);
                        //新建发送消息线程

                        readBuffer.flip();
                        String msg = charset.decode(readBuffer).toString();
                        //###if(!preClientAddress.equals(sa.toString())){
                            //new DataThread(msg,channel, sa,JP).start();
                            //由于mojang历史遗留原因，多线程无法使用，因此使用其他方法来处理信息
                            //DataSaveClass.SynEvents.add(msg);
                            JP.DataSynThread.Tasks.add(msg);
                            //JP.MainCalledData.add(msg);
                            preClientAddress = sa.toString();
                        //###}


                        //System.out.println("server receive msg : " + msg);
                        //JP.CallEvent.Player_Chat("MC_ZRY_163","TEST");

                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void run(){
        this.NoSynUDPServer();
    }
}
