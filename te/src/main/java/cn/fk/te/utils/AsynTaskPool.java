package cn.fk.te.utils;

import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AsynTaskPool {

	/**
	 * 线程名称
	 */
	private static String threadName = "task-pool-";
	
	/**
	 * 最小线程数
	 */
	private int minPoolSize = 1;

	/**
	 * 最大线程数,等于CPU核数
	 */
	//private int maxPoolSize = Runtime.getRuntime().availableProcessors();
	private int maxPoolSize = 8;
	
	/**
	 * 线程池
	 */
	private ThreadPoolExecutor executor = new ThreadPoolExecutor(
			minPoolSize,
			maxPoolSize, 60L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(), new TaskThreadFactory());
	
	/**
	 * 执行任务
	 * @param command
	 */
	public void executor(Runnable task){
		executor.execute(task);
	}
	
	/**
	 * 关闭所有线程
	 */
	public void destroy(){
		try {
			executor.shutdownNow();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/** 
	 * @ProjectName：买买提钱包
	 * @ClassName：TaskThreadFactory 
	 * @Description： 线程工程
	 * @author：le.liu
	 * @date：2017年3月7日 下午20:45:20
	 * @version 
	 */ 
	public static class TaskThreadFactory implements ThreadFactory {
		final ThreadGroup group;
		final AtomicInteger threadNumber = new AtomicInteger(1);

		TaskThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
					.getThreadGroup();
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, threadName
					+ threadNumber.getAndIncrement(), 0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}
}
