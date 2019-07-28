package com.coco;

import com.coco.rocketMq.NormalRocketMQService;
import com.coco.rocketMq.OrderRocketMQService;
import com.coco.selector.IDHashMessageQueueSelector;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author zhangxiaoxun
 * rocket测测试类
 * @date 2019/3/22  17:33
 **/
public class StartUp {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException, MQBrokerException {
        OrderRocketMQService service = new OrderRocketMQService();
        service.consumerStart();
    }

}
