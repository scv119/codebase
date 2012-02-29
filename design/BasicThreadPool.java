public BasicThreadPool implements ThreadPool{
    private List<Thread> threads;
    private Queue<Runnable> blockQueue;

    public BasicThreadPool(int threadPoolSize){
        threads = new ArrayList<Thread>(threadPoolSize); 
        blockQueue = new BlockingQueue<Runnable>();
    }

    private class Worker extends Thread{
        
    }

    private abstract class Task{
        Runnable job; 
    }

    private class AsyncTask extends Task{
        Runnable callback;
    }

    private class syncTask extends Task{
        Lock lock = new ReentrantLock();
    }


}
