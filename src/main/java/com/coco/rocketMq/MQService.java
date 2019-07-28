/**
 * Copyright(C),2005-2019,深圳市珍爱网信息技术有限公司
 **/
package com.coco.rocketMq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @Description: TODO
 * @author zhangxiaoxun
 * @date 2019/7/28 13:51
 * @Version: V1.0
 *
 **/
public interface MQService {
    void consumerStart() throws MQClientException;
    void producerStart() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException;
}
