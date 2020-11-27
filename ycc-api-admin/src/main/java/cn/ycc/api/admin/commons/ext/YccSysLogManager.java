package cn.ycc.api.admin.commons.ext;

import cn.ycc.api.admin.commons.support.CountableThreadPool;
import cn.ycc.api.admin.entity.YccSysLogs;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;

import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author yuchaoqun
 * @email chaoqunyu9311@163.com
 * @date 2020.11.26 20:50
 */
public class YccSysLogManager implements ApplicationListener<ContextRefreshedEvent> {

    private final BlockingQueue<YccSysLogs> QUEUE = new LinkedBlockingQueue<>(2000);
    private final SysLogConsumer consumer;
    private Thread thread;
    private volatile boolean started = false;
    private CountableThreadPool countableThreadPool = new CountableThreadPool(5);

    public YccSysLogManager(SysLogConsumer consumer) {
        this.consumer = consumer;
    }

    public void pushNewLog(YccSysLogs sysLogs){
        QUEUE.offer(sysLogs);
    }
    public synchronized void start(){
        if(started){
            throw new RuntimeException("服务已运行");
        }
        started=true;

        thread = new Thread(new LogComsumerThread());
        thread.setPriority(5);
        thread.setName("log-save");
        thread.setDaemon(true);
        thread.start();
    }
    public synchronized void stop(){
        if(!started){
            throw new RuntimeException("服务尚未运行");
        }
        started=false;
        thread.interrupt();
        countableThreadPool.shutdown();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent refreshedEvent) {
        this.start();
    }

    class LogComsumerThread implements Runnable{

        @Override
        public void run() {
            while (started){
                try {
                    YccSysLogs log = QUEUE.poll(5, TimeUnit.SECONDS);
                    if(log != null){
                        countableThreadPool.execute(()->{
                            consumer.accept(log);
                        });
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

}
