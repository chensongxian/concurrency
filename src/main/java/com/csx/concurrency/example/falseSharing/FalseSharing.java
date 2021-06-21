package com.csx.concurrency.example.falseSharing;

/**
 * @Description: TODO
 * @author: lucachen
 * @Date: 2021-06-04
 */
public final class FalseSharing implements Runnable {
    public static int THREADS = 4;
    public final static long ITERATIONS = 100L * 1000L * 1000L;
    private final int arrayIndex;
    private static VLong[] longs;
    public static long SUM_TIME = 0l;
    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static class VLong {
        public volatile long value = 0L;
        public long p1, p2, p3, p4, p5, p6;     //是否屏蔽此行？
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }


    public static void main(final String[] args) throws Exception {
        Thread.sleep(2000);
        for(int i=0; i<10; i++){
            System.out.println(i);
            if (args.length == 1) {
                THREADS = Integer.parseInt(args[0]);
            }
            longs = new VLong[THREADS];
            for (int j = 0; j < longs.length; j++) {
                longs[j] = new VLong();
            }
            final long start = System.nanoTime();
            runTest();
            final long end = System.nanoTime();
            SUM_TIME += end - start;
        }
        System.out.println("cost time in arverage："+SUM_TIME/10);
    }
}
