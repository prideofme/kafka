
package kafka.examples;

public class KafkaProperties {
    public static final String TOPIC = "自己设置的topic名称";
    public static final String KAFKA_SERVER_URL = "**.**.**.**";//配置自己的kafka地址
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String TOPIC2 = "自己设置的topic名称";
    public static final String TOPIC3 = "自己设置的topic名称";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    private KafkaProperties() {}
}
