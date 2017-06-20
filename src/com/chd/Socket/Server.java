package com.chd.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器Socket
 */
public class Server {
    private static ServerSocket socket = null;

    public static void main(String[] args) {
        try {
            //创建服务器socket
            socket = new ServerSocket(9999);
            System.out.println("服务器启动完成，等待客户端连接");
            //循环等待客户端连接
            while(true) {
                Socket clientSocket = socket.accept();
                //创建新的线程处理客户端请求
                ServerThread st = new ServerThread(clientSocket);
                //启动线程
                st.start();

                System.out.println("socket连接服务器"+clientSocket.getInetAddress().getHostAddress()
                        + "\t"+ clientSocket.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
