package cn.fk.te;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @Author frank.zhao
 * @Date 2018/6/29
 */
public class ThreadTaskUtils {

    private static int corePoolSize=30;
    private static int maximumPoolSize=50;
    private static long keepAliveTime=300L;

    public static ThreadPoolExecutor threadPool=new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>());

    /**
     *  执行任务
     * @param runnable
     */
    public static void excute(Runnable runnable){
        threadPool.execute(runnable);
    }

    /**
     * 停止所有线程
     */
    public static void close(){
        threadPool.shutdownNow();
    }


    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(50);
        executor.setKeepAliveSeconds(300);
        executor.setQueueCapacity(250);
        executor.setThreadNamePrefix("Async-Executor-");
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                String message = "kafka对应消费线程已满";
                if (!e.isShutdown()) {
                    message += ",已执行,当前队列："+e.getQueue().size()+",活跃线程数："+e.getActiveCount();
                    r.run();
                }else {
                    message += ",未执行,当前队列："+e.getQueue().size()+",活跃线程数："+e.getActiveCount();
                }
            }
        });
        executor.initialize();
        return executor;
    }

}
