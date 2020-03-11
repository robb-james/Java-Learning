package TCP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket client = new Socket();
        client.connect(new InetSocketAddress("127.0.0.1",8888));
        Map<String, Object> map = new HashMap<>();
        map.put("id", "123");
        map.put("name", "james");
        String str = JSON.toJSONString(map);
        //获取长度
        int len = str.getBytes().length;
        //拼报文
        str = String.format("%08d", len) + str;
        client.getOutputStream().write(str.getBytes());
        byte[] data = new byte[1024];
        int readLen = 0;
        byte[] len8 = new byte[8];
        while(true){
            readLen += client.getInputStream().read(len8, readLen, 8-readLen);
            if(readLen == 8){
                break;
            }
        }
        String readStr = new String(len8);
        System.out.println("client read len:" + readStr);
        int strLen = Integer.parseInt(readStr);
        byte[] body = new byte[strLen];
        readLen = 0;
        while (true){
            readLen += client.getInputStream().read(body, readLen,strLen - readLen);
            if (readLen >= strLen){
                break;
            }
        }
        client.close();
        String bodyStr = new String(body);
        System.out.println("client read body:" + bodyStr);
        Map<String, Object> result = JSON.parseObject(bodyStr, new TypeReference<Map<String, Object>>(){});
        System.out.println("result:" + result.get("result"));
        System.out.println("dateTime:" + result.get("dateTime"));
    }
}
