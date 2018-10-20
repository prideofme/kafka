package kafka.examples;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.*;
import java.util.Collections;
import java.util.Properties;

public class Consumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;


    public Consumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.172.13.16:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "us_ksender_secondlevel");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        for (ConsumerRecord<Integer, String> record : records) {
            String value = record.value();
            print_Byte(value.getBytes());
            //System.out.println();
            //method1("D:\\kafka.txt",value);
            method2("D:\\kafka1.txt",value);
        }
    }

    public void method1(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(String file, String conent){
        FileOutputStream fos = null;
        try{
            byte[] buf = conent.getBytes();
            buf[0] = -95;
            buf[1] = 44;
            buf[2] = 2;
/*
            for (int i = 0; i < buf.length; i++) {
                if(buf[i] == -17){
                    buf[i] = -95;
                }else if(buf[i] == -65){
                    buf[i] = 44;
                }else if(buf[i] == -67){
                    buf[i] = 2;
                }
            }*/
            //print_Byte(buf);

            fos = new FileOutputStream(file,true);
            fos.write(buf);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void print_Byte(byte[] buf){
        for (int i = 0; i < buf.length; i++) {
            System.out.print(buf[i]+",");
        }
        System.out.println();
    }

}
