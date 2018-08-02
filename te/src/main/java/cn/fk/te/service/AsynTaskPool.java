package cn.fk.te.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class AsynTaskPool {

    private static int corePoolSize=1;
    private static int maximumPoolSize=8;
    private static long keepAliveTime=300L;

    //队列：直接提交SynchronousQueue，无界队列LinkedBlockingQueue，有界队列ArrayBlockingQueue
    //ThreadPoolExecutor自己已经提供了四个拒绝策略，分别是CallerRunsPolicy,AbortPolicy,DiscardPolicy,DiscardOldestPolicy

    public static ThreadPoolExecutor threadPool=new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy()
            );

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


    /*public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(50);
        executor.setKeepAliveSeconds(300);
        executor.setQueueCapacity(250);
        executor.setThreadNamePrefix("Async-Executor-");
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                String message = "对应消费线程已满";
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
    }*/

}
