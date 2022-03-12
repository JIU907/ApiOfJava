package com.jiu907.api.juc.demo.chapter5;


import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author LeiLiMin
 * @Description: 阻塞队列实现生产者消费者模式 01
 * @date: 2022/3/11
 * <p>
 * 模拟了一个
 * 1.可支持自由输入数据
 * 2.消费者不断处理数据的建议，消费者-生产者模型
 */
public class T03_BlockingQueue_01 {
    private final Producer producer;
    private final Consumer consumer;

    /**
     * 1.用外部类封装2个内部类，来保证生产者和消费者来保证用的是同一个消费队列
     */
    public T03_BlockingQueue_01() {
        ArrayBlockingQueue<String> taskQueue = new ArrayBlockingQueue(100);
        producer = new Producer(taskQueue);
        consumer = new Consumer(taskQueue);
    }

    public void producerData(String string) throws InterruptedException {
        producer.produceData(string);
    }

    // 内部类：生产者
    class Producer {
        private final ArrayBlockingQueue<String> taskQueue;

        public Producer(ArrayBlockingQueue<String> taskQueue) {
            this.taskQueue = taskQueue;
        }

        public void produceData(String data) throws InterruptedException {
            taskQueue.put(data);
        }
    }

    // 内部类：消费者
    class Consumer implements Runnable {
        private final ArrayBlockingQueue<String> taskQueue;

        public Consumer(ArrayBlockingQueue<String> taskQueue) {
            this.taskQueue = taskQueue;
        }

        public String consumeData() throws InterruptedException {
            return taskQueue.take();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    consumeData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
