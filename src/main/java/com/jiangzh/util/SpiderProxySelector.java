package com.jiangzh.util;

import com.jiangzh.constants.Constants;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangzh
 */
public class SpiderProxySelector extends ProxySelector {
    @Override
    public List<Proxy> select(URI uri) {
        List<Proxy> result = new ArrayList<>();
        result.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Constants.IP, Constants.PORT)));
        return result;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress socketAddress, IOException e) {
        System.out.println("无法连接到指定代理服务器上");
    }
}
