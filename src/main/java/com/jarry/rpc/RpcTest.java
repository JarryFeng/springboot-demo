package com.jarry.rpc;

import com.jarry.service.HelloService;
import com.jarry.service.impl.HelloServiceImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by jarry on 2018/5/31.
 */
public class RpcTest {
    public static void main(String[] args) throws IOException {

        RpcServer serviceServer = new RpcServerImpl(8088);

        new Thread(new Runnable() {
            public void run() {
                try {
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));


        try {
            Thread.sleep(5000);
            serviceServer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service.sayHi("wocao"));

    }
}

