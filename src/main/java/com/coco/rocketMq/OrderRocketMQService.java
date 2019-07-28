/**
 * Copyright(C),2005-2019,深圳市珍爱网信息技术有限公司
 **/
package com.coco.rocketMq;

import com.coco.entity.OrderDO;
import com.coco.selector.IDHashMessageQueueSelector;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description: 顺序rocket mq
 * @author zhangxiaoxun
 * @date 2019/7/28 13:50
 * @Version: V1.0
 *
 **/
public class OrderRocketMQService {
    public void producerStart() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
//        consumerStart();

        List<OrderDO> list = buildOrderList();

        // 构建生产者
        System.out.println("--starting producer------");
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
        producer.setNamesrvAddr("localhost:9876");
        producer.setInstanceName("Producer1");
        producer.setSendMsgTimeout(1000);
        producer.start();
        IDHashMessageQueueSelector selector = new IDHashMessageQueueSelector();
        // 向topic发送消息

        for (int i = 0; i < list.size(); i++) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(date);
            String body = dateStr+" "+ list.get(i) +" "+i;
            Message msg = new Message("topic_test1" ,"tag1",
                    body.getBytes("UTF-8"));
            SendResult sendResult = producer.send(msg, selector, list.get(i).getOrderId());
            System.out.println(sendResult + ", body:" + body);
        }
        producer.shutdown();
        System.out.println("--send message complete------");
    }



    /**
     * 顺序消费
     * @throws MQClientException
     */
    public  void consumerStart() throws MQClientException {

        System.out.println("--starting consumer------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setInstanceName("Consumber1");
        // 订阅topic下的所有tag消息
        consumer.subscribe("topic_test1", "tag1");
        //顺序读取使用MessageListenerOrderly
        consumer.registerMessageListener(new MessageListenerOrderly() {

            Random random = new Random();

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                System.out.print(Thread.currentThread().getName() + " Receive New Messages: " );
                for (MessageExt msg: msgs) {
                    System.out.println(msg + ", content:" + new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("--start consumer complete------");
    }

    /**
     * 创建订单list
     * @return
     */
    private List<OrderDO> buildOrderList() {
        List<OrderDO> list = new ArrayList<>();
        OrderDO orderDO1 = new OrderDO();
        orderDO1.setOrderId(11111);
        orderDO1.setDesc("创建");

        OrderDO orderDO2 = new OrderDO();
        orderDO2.setOrderId(11111);
        orderDO2.setDesc("付款");

        OrderDO orderDO3 = new OrderDO();
        orderDO3.setOrderId(11112);
        orderDO3.setDesc("完成");

        OrderDO orderDO11 = new OrderDO();
        orderDO11.setOrderId(11112);
        orderDO11.setDesc("创建");

        OrderDO orderDO12 = new OrderDO();
        orderDO12.setOrderId(11112);
        orderDO12.setDesc("付款");

        OrderDO orderDO13 = new OrderDO();
        orderDO13.setOrderId(11112);
        orderDO13.setDesc("完成");


        OrderDO orderDO21 = new OrderDO();
        orderDO21.setOrderId(11113);
        orderDO21.setDesc("创建");

        OrderDO orderDO22 = new OrderDO();
        orderDO22.setOrderId(11113);
        orderDO22.setDesc("付款");

        OrderDO orderDO23 = new OrderDO();
        orderDO23.setOrderId(11113);
        orderDO23.setDesc("完成");

        OrderDO orderDO31 = new OrderDO();
        orderDO21.setOrderId(11114);
        orderDO21.setDesc("创建");

        OrderDO orderDO32 = new OrderDO();
        orderDO22.setOrderId(11114);
        orderDO22.setDesc("付款");

        OrderDO orderDO33 = new OrderDO();
        orderDO23.setOrderId(11114);
        orderDO23.setDesc("完成");

        list.add(orderDO1);
        list.add(orderDO2);
        list.add(orderDO3);
        list.add(orderDO11);
        list.add(orderDO12);
        list.add(orderDO13);
        list.add(orderDO21);
        list.add(orderDO22);
        list.add(orderDO23);
        list.add(orderDO31);
        list.add(orderDO32);
        list.add(orderDO33);
        return list;
    }
}
