package com.demo.springboot3_2demo.virtualThread;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;

public class Demo {

    ConcurrentSkipListSet<String> repeatThread(Thread.Builder builder) throws InterruptedException {
//        var es = Executors.newVirtualThreadPerTaskExecutor();  // 스레드 풀이 아님
        var observed = new ConcurrentSkipListSet<String>();
        var threads = new ArrayList<Thread>();

        for (int i = 0; i < 1000; i++) {
            var index = i;
            threads.add(builder.unstarted(  // Thread.ofPlatform() or Thread.ofVirtual()
                () -> {
                    for (int j = 0; j < 5; j++) {
                        if (index == 0) {
                            observed.add(Thread.currentThread().toString());
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        }

        for (var t : threads) t.start();

        for (var t : threads) t.join();

        System.out.println(observed);

        return observed;
    }
}

