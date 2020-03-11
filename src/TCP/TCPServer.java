package TCP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Map;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(socketAddress, 50);
        System.out.println("服务端启动在：" + socketAddress);
        while (true){
            Socket socket = serverSocket.accept();
            InetAddress inetAddress =socket.getInetAddress();
            byte[] len8 = new byte[8];
            int readLen = 0;
            while (true){
                readLen += socket.getInputStream().read(len8, readLen, 8-readLen);
                if (readLen == 8){
                    break;
                }
            }
            String strLen = new String(len8);
            System.out.println("Server read len：" + strLen);
            int shouldLen = Integer.parseInt(strLen);
            byte[] body = new byte[shouldLen];
            readLen = 0 ;
            while (true) {
                readLen += socket.getInputStream().read(body, readLen, shouldLen - readLen);
                if (readLen >= shouldLen){
                    break;
                }
            }
            String readStr = new String(body);
            System.out.println("Server read body：" + readStr);
            Map<String ,Object> map = JSON.parseObject(readStr, new TypeReference<Map<String, Object>>(){});
            map.put("result", "success");
            map.put("dateTime", new Date().toString());
            String send = JSON.toJSONString(map);
            int sendLen = send.getBytes().length;
            socket.getOutputStream().write((String.format("%08d",sendLen) + send).getBytes());
        }
    }
}
