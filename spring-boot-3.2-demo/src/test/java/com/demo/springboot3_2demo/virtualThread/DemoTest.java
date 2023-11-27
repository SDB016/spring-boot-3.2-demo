package com.demo.springboot3_2demo.virtualThread;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class DemoTest {

    Demo demo = new Demo();

    @Test
    public void repeatPlatformThreadTest() throws InterruptedException {
        ConcurrentSkipListSet<String> threadsToString = demo.repeatThread(Thread.ofPlatform());
        assertEquals(1, threadsToString.size());  // 하나의 스레드를 사용
        assertFalse(threadsToString.contains("VirtualThread"));
    }

    @Test
    public void repeatVirtualThreadTest() throws InterruptedException {
        ConcurrentSkipListSet<String> threadsToString = demo.repeatThread(Thread.ofVirtual());
        Set<String> virtualThreadSubStringSet = getSubStringSet(threadsToString, 0);
        Set<String> actualThreadSubStringSet = getSubStringSet(threadsToString, 1);

        assertEquals(1, virtualThreadSubStringSet.size());
        assertNotEquals(1, actualThreadSubStringSet.size());  // delay 때문에 실행마다 다른 스레드를 사용
    }

    @NotNull
    private static Set<String> getSubStringSet(ConcurrentSkipListSet<String> threadsToString, int index) {
        return threadsToString.stream()
                .map(s -> s.split("/")[index])
                .collect(Collectors.toSet());
    }

}