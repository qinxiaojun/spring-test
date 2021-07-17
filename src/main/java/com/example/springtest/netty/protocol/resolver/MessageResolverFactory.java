package com.example.springtest.netty.protocol.resolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2021-07-03 18:10
 */
public class MessageResolverFactory {
    private static Map<String,IMessageResolver> map = new HashMap<>();
    static {
        map.put("login",new LoginResolver());
        map.put("heartbeat",new HeartbeatResolver());
    }

    public static IMessageResolver getMessageResolver(String command){
        if (map.containsKey(command)){
            return map.get(command);
        }
        throw new RuntimeException("unsupported protocol type");
    }
}
