package com.netty.utils;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouwei
 */
public class NettyUtils {

    /**
     * 定义全局单利channel组 管理所有channel
     */
    private static volatile ChannelGroup channelGroup = null;

    /**
     * 存放请求ID与channel的对应关系
     */
    private static volatile ConcurrentHashMap<String, Channel> channelMap = null;

    /**
     * 定义两把锁
     */
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();


    /**
     * 获取频道群
     * 单例、同步
     * */
    public static ChannelGroup getChannelGroup() {
        if (null == channelGroup) {
            synchronized (LOCK1) {
                if (null == channelGroup) {
                    channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                }
            }
        }
        return channelGroup;
    }

    public static ConcurrentHashMap<String, Channel> getChannelMap() {
        if (null == channelMap) {
            synchronized (LOCK2) {
                if (null == channelMap) {
                    channelMap = new ConcurrentHashMap<>();
                }
            }
        }
        return channelMap;
    }

    public static Channel getChannel(String userId) {
        if (null == channelMap) {
            return getChannelMap().get(userId);
        }
        return channelMap.get(userId);
    }
}
