/**
 * ydhz_yjy.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.hms.core.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

/**
 * 
 * @author wqi
 * @version $Id: MQKafkaConsumer.java, v 0.1 2015-3-3 下午2:11:25 wangq Exp $
 */
public class CopyOfMQKafkaConsumer {

    private ConsumerConfig    config;
    private String            topic;
    private int               partitionsNum;
    private MessageExecutor   executor;
    private ConsumerConnector connector;
    private ExecutorService   threadPool;

    public CopyOfMQKafkaConsumer(String topic, int partitionsNum, MessageExecutor executor) throws Exception {

        Properties props = new Properties();

        //设置zookeeper的链接地址
        props.put("zookeeper.connect", "218.205.81.50:7281");

        // 设置group id
        props.put("group.id", "test_topic");
        // kafka的group 消费记录是保存在zookeeper上的, 但这个信息在zookeeper上不是实时更新的, 需要有个间隔时间更新
        props.put("auto.commit.interval.ms", "1000");
        props.put("zookeeper.session.timeout.ms", "1000000");

        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        //        Properties properties = new Properties();
        //        properties.load(ClassLoader.getSystemResourceAsStream("consumer.properties"));
        config = new ConsumerConfig(props);
        this.topic = topic;
        this.partitionsNum = partitionsNum;
        this.executor = executor;
    }

    public void start() throws Exception {
        connector = Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topics = new HashMap<String, Integer>();
        topics.put(topic, partitionsNum);
        Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topics);
        List<KafkaStream<byte[], byte[]>> partitions = streams.get(topic);
        threadPool = Executors.newFixedThreadPool(partitionsNum);
        for (KafkaStream<byte[], byte[]> partition : partitions) {
            threadPool.execute(new MessageRunner(partition));
        }
    }

    public void close() {
        try {
            threadPool.shutdownNow();
        } catch (Exception e) {
            //
        } finally {
            connector.shutdown();
        }

    }

    class MessageRunner implements Runnable {
        private KafkaStream<byte[], byte[]> partition;

        MessageRunner(KafkaStream<byte[], byte[]> partition) {
            this.partition = partition;
        }

        public void run() {
            ConsumerIterator<byte[], byte[]> it = partition.iterator();
            while (it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> item = it.next();
                System.out.println("partiton:" + item.partition());
                System.out.println("offset:" + item.offset());
                executor.execute(new String(item.message()));//UTF-8
            }
        }
    }

    interface MessageExecutor {

        public void execute(String message);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        CopyOfMQKafkaConsumer consumer = null;
        try {
            MessageExecutor executor = new MessageExecutor() {

                public void execute(String message) {
                    System.out.println(message);

                }
            };
            consumer = new CopyOfMQKafkaConsumer("test_topic", 2, executor);
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
