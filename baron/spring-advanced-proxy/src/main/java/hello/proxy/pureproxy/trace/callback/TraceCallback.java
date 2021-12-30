package hello.proxy.pureproxy.trace.callback;

public interface TraceCallback<T> {
    T call();
}
