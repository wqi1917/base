/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.core.kafka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


/**
 * 
 * @author wqi
 * @version $Id: PackageProducer.java, v 0.1 2015-3-3 上午9:40:59 wangq Exp $
 */
public class MQKafkaProducer {

    private static Producer<String, String> inner;

    private static Producer<String, String> initialize() {
        if (inner == null) {
            synchronized ("inner") {
                if (inner == null) {
                    Properties props = new Properties();

                    //此处配置的是kafka的端口
                    props.put("metadata.broker.list", "218.205.81.50:7201");
                    props.put("producer.type", "sync");
                    props.put("compression.codec", "0");

                    //配置value的序列化类
                    props.put("serializer.class", "kafka.serializer.StringEncoder");
                    //配置key的序列化类
                    props.put("key.serializer.class", "kafka.serializer.StringEncoder");

                    //        properties.load(ClassLoader.getSystemResourceAsStream("producer.properties"));
                    ProducerConfig config = new ProducerConfig(props);
                    inner = new Producer<String, String>(config);
                }
            }
        }
        return inner;
    }

    /**
     * 发送消息
     * 
     * @param topicName
     * @param message
     */
    public static void send(String topicName, String message) {
        if (topicName == null || message == null) {
            return;
        }
        KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, message);
        initialize().send(km);
    }

    /**
     * 批量发送消息
     * 
     * @param topicName
     * @param messages
     */
    public static void send(String topicName, Collection<String> messages) {
        if (topicName == null || messages == null) {
            return;
        }
        if (messages.isEmpty()) {
            return;
        }
        List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
        for (String entry : messages) {
            KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, entry);
            kms.add(km);
        }
        initialize().send(kms);
    }

    public static void close() {
        if (inner != null) {
            inner.close();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            int i = 0;
            for (int j = 0; j < 10; j++) {
                MQKafkaProducer.send("im_topic_group", "this is a sample test_topic111 " + i);
                MQKafkaProducer.send("im_topic_chart", "this is a sample test_topic111 " + i);
                //                producer.send("test_topic", "this is a sample test_topic " + i);
                //                producer.send("test_topic222", "this is a sample test_topic222 " + i);
                System.out.println("this is a sample " + i);

                i++;
                Thread.sleep(200);
            }

            //            while (true) {
            //                producer.send("test_topic", "this is a sample" + i);
            //                i++;
            //                Thread.sleep(2000);
            //            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MQKafkaProducer.close();
        }

    }

}
