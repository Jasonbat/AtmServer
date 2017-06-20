package com.chd.Socket;

import com.chd.entity.Account;
import com.chd.entity.Packet;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户端测试类
 */
public class Client {
    private static Socket socket = null;
    private static ObjectInputStream ois = null;
    private static ObjectOutputStream oos = null;

    public static void main(String[] args) {
        try {
            //连接客户端
            socket = new Socket("localhost", 9999);
            //创建account对象
            Account account = new Account("admin","admin",0);
            //传输对象
            Packet packet = new Packet("LOGIN",account);
            System.out.println("客户端启动");

            //发送数据
            sendPacket(packet);

            //接受服务器返回的数据
            packet = receivePacket();

            //处理服务器返回的数据
            if("success".equals(packet.getCmd())){
                System.out.println("登陆成功");
            } else {
                System.out.println("登录失败");
            }

            Thread.sleep(30000);

            socket = new Socket("localhost", 9999);
            sendPacket(new Packet("query",null));

            packet = receivePacket();

            System.out.println(packet.getCmd());
            if(packet.getData()!=null) {
                System.out.println("!null");
                List<Account> list = (List<Account>) packet.getData();
                for (Account ac : list) {
                    System.out.println(ac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendPacket(Packet packet) {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(packet);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Packet receivePacket() {
        Packet packet = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            packet = (Packet) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packet;
    }
}
