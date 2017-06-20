package com.chd.socket;

import com.chd.entity.Account;
import com.chd.entity.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器线程类，处理客户端请求
 */
public class ServerThread extends Thread {
    private Socket socket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private Packet packet = null;

    /**
     * 构造方法
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * 线程运行方法
     */
    @Override
    public void run() {
        Packet packet = receivePacket();
        if ("LOGIN".equals(packet.getCmd())) {
            login();
        } else if ("query".equals(packet.getCmd())) {
            query();
        }
    }


    /**
     * 获取输入数据
     */
    private Packet receivePacket() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            packet = (Packet) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packet;
    }


    /**
     * 回送数据
     */
    private void sendPacket(Packet packet) {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(packet);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        System.out.println("进入login函数");
        Account account = (Account) packet.getData();
        System.out.println(account);
        Packet result = null;
        if ("admin".equals(account.getName()) && "admin".equals(account.getPassword())) {
            //登陆成功
            result = new Packet("success", null);
            System.out.println("回发packet");
            sendPacket(result);
        }
    }

    /**
     * 执行查询数据操作
     */
    private void query() {
        List<Account> list = new ArrayList<>();
        list.add(new Account("1", "1", 1));
        list.add(new Account("2", "2", 2));

        packet.setCmd("success");
        packet.setData(list);
        sendPacket(packet);
    }
}
