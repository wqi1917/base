/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.core.kafka;

import java.util.HashMap;



/**
 * 
 * @author wqi
 * @version $Id: MQKafkaConsumer.java, v 0.1 2015-3-3 下午2:11:25 wangq Exp $
 */
public class MQKafkaConsumerFacroty {

    private static HashMap<String, MQKafkaConsumer> consumerMap = null;

    public MQKafkaConsumerFacroty() {
        initialize();
    }

    public static void initialize() {
        if (consumerMap == null) {
            synchronized ("consumerMap") {
                if (consumerMap == null) {
                    consumerMap = new HashMap<String, MQKafkaConsumer>();
                }
            }
        }
        // 初始化topic 

    }

    /**
     * 注册
     * 
     * @param topicName
     * @param executor
     */
    public static void register(String topicName, MessageExecutor executor) {
        initialize();
        MQKafkaConsumer consumer = new MQKafkaConsumer(topicName, executor);
        consumerMap.put(topicName, consumer);
        consumer.start();
    }

    /**
     * 注销
     * 
     * @param topicName
     */
    public static void destroy(String topicName) {
        initialize();
        MQKafkaConsumer consumer = consumerMap.get(topicName);
        if (consumer != null) {
            consumer.close();
        }
    }

    /**
     * 关闭
     */
    public static void close() {
        if (consumerMap == null) {
            return;
        }
        for (String topic : consumerMap.keySet()) {
            MQKafkaConsumer consumer = consumerMap.get(topic);
            if (consumer != null) {
                consumer.close();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            MessageExecutor executor = new MessageExecutor() {
                public void execute(Object obj) {
                    System.out.println(obj);
                }

                public boolean isBatch() {
                    return false;
                }
            };
            MQKafkaConsumerFacroty.register("test_topic", executor);
            MQKafkaConsumerFacroty.register("test_topic111", executor);
            MQKafkaConsumerFacroty.register("test_topic222", executor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //            if(consumer != null){
            //                consumer.close();
            //            }
        }

    }

}
