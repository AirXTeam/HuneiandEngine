package cn.zry551.plugin.huneiand;

import java.io.IOException;
import java.net.*;

public class SendMSG {
    public static boolean Send(String SData, String SAddr, int SPort) {
        try {
            InetAddress address = InetAddress.getByName(SAddr); // 1.定义服务器的地址、端口号、数据
            int port = SPort;//定义端口类型
            //Scanner scanner = new Scanner(System.in);//从键盘接受数据
            //String send = scanner.nextLine();//nextLine方式接受字符串
            byte[] data = SData.getBytes();//将接收到的数据变成字节数组
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);//2.创建数据报，包含发送的数据信息
            DatagramSocket socket = new DatagramSocket(); // 3.创建DatagramSocket对象
            socket.send(packet);// 4.向服务器端发送数据报
            socket.close();
            /*
             * 接收服务器端响应的数据
             */
            //byte[] data2 = new byte[4096];//创建字节数组
            //DatagramPacket packet2 = new DatagramPacket(data2, data2.length);// 1.创建数据报，用于接收服务器端响应的数据
            //socket.receive(packet2);// 2.接收服务器响应的数据
            //3.读取数据
            //String reply = new String(data2, 0, packet2.getLength());//创建字符串对象
            return true;
        }catch (SocketException e) {
            e.printStackTrace();
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

