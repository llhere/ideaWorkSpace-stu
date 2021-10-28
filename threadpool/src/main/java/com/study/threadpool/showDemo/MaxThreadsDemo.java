package com.study.threadpool.showDemo;

import java.util.concurrent.CountDownLatch;

public class MaxThreadsDemo {

	public static void main(String[] args) {
		final CountDownLatch cdl = new CountDownLatch(1);
		try {
			Thread.sleep(20000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < 10000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cdl.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();

			System.out.println("i=" + i);
		}
	}
}
