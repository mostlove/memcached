package com.magicbeans.util;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.magicbeans.config.Config;

import java.util.Date;

/**
 * Created by Eric Xie on 2017/1/4 0004.
 */
public class Memcached {

    private static MemCachedClient memCachedClient;
    private static Memcached memcached = new Memcached();



    private Memcached() {
        memCachedClient = new MemCachedClient();
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(new String[] {Config.getValue("ip")});
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(500);
        pool.setMaxIdle(1000 * 60 * 60);
        pool.setMaintSleep(60);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);
        pool.initialize();

    }

    /**
     *  GET INSTANCE
     * @return Memcached
     */
    public static Memcached getInstance() {
        if(null == memcached){
            memcached = new Memcached();
        }
        return memcached;
    }

    /**
     *  不限时长的存储
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value) {
        return memCachedClient.add(key, value,0);
    }

    /**
     *  以秒为单位
     * @param key
     * @param value
     * @param milliseconds
     */
    public boolean add(String key, Object value, int milliseconds) {
        return memCachedClient.add(key, value, new Date(1000 * milliseconds));
    }

    public boolean set(String key, Object value) {
        return memCachedClient.set(key, value,0);
    }
    public void set(String key, Object value, int milliseconds) {
        memCachedClient.set(key, value, new Date(1000 * milliseconds));
    }


    public Object get(String key) {
        return memCachedClient.get(key);
    }

    public boolean delObj(String key){
        return memCachedClient.delete(key);
    }

    public boolean replace(String key,Object obj){
        return memCachedClient.replace(key, obj);
    }


}
