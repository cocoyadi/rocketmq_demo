/**
 * Copyright(C),2005-2019,深圳市珍爱网信息技术有限公司
 **/
package com.coco.rocketMq;

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
 * @Description: TODO
 * @author zhangxiaoxun
 * @date 2019/7/27 15:48
 * @Version: V1.0
 *
 **/
public class NormalRocketMQService implements MQService{
    @Override
    public void producerStart() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        consumerStart();

        // 构建生产者
        System.out.println("--starting producer------");
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
        producer.setNamesrvAddr("localhost:9876");
        producer.setInstanceName("Producer1");
        //超时时间
        producer.setSendMsgTimeout(1000);
        producer.start();
        // 向topic发送消息
        for (int i = 0; i < 10000; i++) {
            Message msg = new Message("topic_test1" ,"tag1",
                    ("mq test message " +i).getBytes("UTF-8"));
            SendResult sendResult = producer.send(msg);
            System.out.println(i +" :"+sendResult.toString());
        }
        producer.shutdown();
        System.out.println("--send message complete------");
    }

    @Override
    public  void consumerStart() throws MQClientException {

        System.out.println("--starting consumer------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setInstanceName("Consumber1");
        // 订阅topic下的所有tag消息
        consumer.subscribe("topic_test1", "tag1");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("receive:" + msgs );
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("--start consumer complete------");
    }
}
