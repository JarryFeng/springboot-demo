package com.jarry.rpc;

import java.io.IOException;

/**
 * Created by jarry on 2018/5/31.
 * <p>
 * 服务中心
 */
public interface RpcServer {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
