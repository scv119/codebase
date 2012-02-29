public interface ThreadPool{
    void asyncRun(Runnable task, Runnable callback);
    void synRun(Runnable task);
    boolean stop();
    void cancel(Runnable task);
}
