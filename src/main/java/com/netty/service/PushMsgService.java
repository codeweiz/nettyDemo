package com.netty.service;

/**
 * @author zhouwei
 */
public interface PushMsgService {

    /**
     * 推送给指定用户
     *
     * @param userId 用户id
     * @param msg 消息
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送给所有用户
     *
     * @param msg 消息
     */
    void pushMsgToAll(String msg);

    /**
     * 获取当前连接数
     *
     * @return int ws连接数
     * */
    int getChannelCount();

}
