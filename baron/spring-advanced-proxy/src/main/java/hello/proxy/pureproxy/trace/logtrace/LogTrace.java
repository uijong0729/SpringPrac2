package hello.proxy.pureproxy.trace.logtrace;

import hello.proxy.pureproxy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
