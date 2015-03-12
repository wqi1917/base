/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.core.kafka;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import org.apache.log4j.Logger;

/**
 * 
 * @author wqi
 * @version $Id: MQKafkaConsumer.java, v 0.1 2015-3-3 下午2:11:25 wangq Exp $
 */
public class MQKafkaConsumer {

    public static Logger              logger     = Logger.getLogger(MQKafkaConsumer.class);
    // 配置文件
    private static Properties         props;
    // kafka 配置
    private static ConsumerConfig     config;

    private static int                partition = 1;

    private String                    topic;
    private MessageExecutor           executor;
    private ExecutorService       threadPool;
    private ConsumerConnector         connector;


    /**
     * 初始化
     */
    private static void initialize() {
        if (config == null) {
            synchronized ("config") {
                if (config == null) {
                    props = new Properties();
                    ResourceBundle bundle = ResourceBundle.getBundle("kafka");

                    Enumeration<String> keys = bundle.getKeys();
                    while (keys.hasMoreElements()) {
                        String key = (String) keys.nextElement();
                        props.put(key, bundle.getObject(key));
                    }
                    config = new ConsumerConfig(props);

                    String partitionNum = props.getProperty("partitions.num", "1");
                    partition = Integer.parseInt(partitionNum);
                }
            }
        }

    }

    public MQKafkaConsumer(String topic, MessageExecutor executor) {
        initialize();

        //        //设置zookeeper的链接地址
        //        props.put("zookeeper.connect", "218.205.81.50:7281");
        //
        //        // 设置group id
        //        props.put("group.id", "test_topic222");
        //        // kafka的group 消费记录是保存在zookeeper上的, 但这个信息在zookeeper上不是实时更新的, 需要有个间隔时间更新
        //        props.put("auto.commit.interval.ms", "1000");
        //        props.put("zookeeper.session.timeout.ms", "1000000");
        //
        //        //配置value的序列化类
        //        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //        //配置key的序列化类
        //        props.put("key.serializer.class", "kafka.serializer.StringEncoder");


        this.topic = topic;
        this.executor = executor;
    }


    /**
     * 开始接收消息
     * 
     * @throws Exception
     */
    public void start() {
        try {
            connector = Consumer.createJavaConsumerConnector(config);
            Map<String, Integer> topics = new HashMap<String, Integer>();
            topics.put(topic, partition);

            Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topics);
            List<KafkaStream<byte[], byte[]>> partitions = streams.get(topic);
            threadPool = Executors.newFixedThreadPool(partition);
            for (KafkaStream<byte[], byte[]> partition : partitions) {
                threadPool.execute(new MessageRunner(partition));
            }
        } catch (Exception e) {
            logger.error("topic:" + topic + " 获取消息异常：", e);
        }
        
    }

    /**
     * 关闭
     */
    public void close() {
        try {
            threadPool.shutdownNow();
        } catch (Exception e) {
            //
        } finally {
            connector.shutdown();
        }

    }

    /**
     * 消息处理
     * 
     * @author wqi
     * @version $Id: MQKafkaConsumer.java, v 0.1 2015-3-3 下午5:33:18 wangq Exp $
     */
    class MessageRunner implements Runnable {
        private KafkaStream<byte[], byte[]> partition;

        private MessageExecutor             executor;

        MessageRunner(KafkaStream<byte[], byte[]> partition) {
            this.partition = partition;
        }

        public MessageRunner(KafkaStream<byte[], byte[]> partition, MessageExecutor executor) {
            this.partition = partition;
            this.executor = executor;
        }

        public void run() {

            ConsumerIterator<byte[], byte[]> it = partition.iterator();
            while (it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> item = it.next();
                //                System.out.println("partiton:" + item.partition());
                //                System.out.println("offset:" + item.offset());
                executor.execute(new String(item.message()));
            }
        }
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        MQKafkaConsumer consumer = null;
        try {
            MessageExecutor executor = new MessageExecutor() {
                public void execute(Object obj) {
                    System.out.println(obj);
                }

                public boolean isBatch() {
                    return false;
                }
            };
            consumer = new MQKafkaConsumer("test_topic", executor);
            consumer.start();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //            if(consumer != null){
            //                consumer.close();
            //            }
        }

    }

}
