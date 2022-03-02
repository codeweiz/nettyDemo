package com.netty.service.impl;

import com.netty.utils.NettyUtils;
import com.netty.service.PushMsgService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhouwei
 */
@Service
public class PushMsgServiceImpl implements PushMsgService {

    @Override
    public void pushMsgToOne(String userId, String msg) {
        Channel channel = NettyUtils.getChannel(userId);
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }

        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyUtils.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public int getChannelCount() {
        ChannelGroup channelGroup = NettyUtils.getChannelGroup();
        return channelGroup.size();
    }
}
