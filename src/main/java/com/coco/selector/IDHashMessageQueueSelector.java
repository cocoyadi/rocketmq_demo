/**
 * Copyright(C),2005-2019,深圳市珍爱网信息技术有限公司
 **/
package com.coco.selector;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @Description: 队列选择器
 * @author zhangxiaoxun
 * @date 2019/7/28 13:42
 * @Version: V1.0
 *
 **/
public class IDHashMessageQueueSelector  implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg,
                               Object arg) {
        Integer id = Integer.parseInt(arg.toString());
        int size = mqs.size();
        int index = id%size;
        return mqs.get(index);
    }
}
