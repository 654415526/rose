package com.lx.rose.retry.core.temptale;

import com.lx.rose.retry.core.callback.AbstractCallBack;
import com.lx.rose.retry.core.configurable.RetryConfigurable;
import com.lx.rose.retry.core.exception.retry.RetryException;
import com.lx.rose.retry.core.retry.IRetryInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-04 15:11
 */
public abstract class AbstractRetryTemplate extends AbstractRetry {

    private LongAdder longAdder;

    private ExecutorService executorService;

    private RetryConfigurable retryConfigurable;

    private IRetryInterface iRetryInterface;

    /**
     * retry 有参构造器，retryConfigurable参数为默认值
     * 植入IRetryInterface的doBiz方法
     */
    protected AbstractRetryTemplate(IRetryInterface iRetryInterface) {
        Objects.requireNonNull(iRetryInterface);
        init(new RetryConfigurable(), iRetryInterface);
    }

    /**
     * retry 有参构造器
     * 通过RetryConfigurable来build参数
     * 植入IRetryInterface的doBiz方法
     *
     * @param retryConfigurable the RetryConfigurable
     */
    protected AbstractRetryTemplate(RetryConfigurable retryConfigurable, IRetryInterface iRetryInterface) {
        Objects.requireNonNull(retryConfigurable);
        Objects.requireNonNull(iRetryInterface);
        init(retryConfigurable, iRetryInterface);
    }

    /**
     * retry 初始化参数方法
     */
    private void init(RetryConfigurable retryConfigurable, IRetryInterface iRetryInterface) {
        this.retryConfigurable = retryConfigurable;
        this.iRetryInterface = iRetryInterface;
        this.longAdder = new LongAdder();
        // 给计数器赋初始值
        this.longAdder.add(retryConfigurable.getRetryCount());
        if (this.retryConfigurable.getAsync()) {
            this.executorService = new ThreadPoolExecutor(1, 1, 3L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        }
    }

    /**
     * need call back execute method
     *
     * @param abstractCallBack the abstract call back
     * @throws RetryException       the retry exception
     * @throws InterruptedException the interrupted exception
     */
    @Override
    public void execute(AbstractCallBack abstractCallBack) throws RetryException, InterruptedException {
        strategyChoose(abstractCallBack);
    }

    /**
     * not need call back execute method
     *
     * @throws RetryException       the retry exception
     * @throws InterruptedException the interrupted exception
     */
    @Override
    public void execute() throws RetryException, InterruptedException {
        strategyChoose(null);
    }

    /**
     * abstractCallBack is null 则不回调
     *
     * @param abstractCallBack the abstract call back
     * @throws RetryException       the retry exception
     * @throws InterruptedException the interrupted exception
     */
    private void doRun(AbstractCallBack abstractCallBack) throws RetryException, InterruptedException {
        try {
            if (null == abstractCallBack) {
                iRetryInterface.doBiz();
            } else {
                abstractCallBack.onSuccess(iRetryInterface.doBiz());
            }
        } catch (Throwable e) {
            // 重试次数减一
            longAdder.decrement();
            // 失败回调
            if (null != abstractCallBack) {
                abstractCallBack.onFailure(e);
            }
            // 计数器不大于等于0则重试
            if (longAdder.intValue() >= 0) {
                // 重试间隔时间
                TimeUnit.SECONDS.sleep(retryConfigurable.getIntervals());
                // log.error(String.format("error info: =====> %s,再次尝试...", e.getMessage()));
                // 重新调用
                doRun(abstractCallBack);
            } else {
                throw new RetryException(String.format("retry end,exception info: %s", e.getMessage()));
            }
        }
    }

    /**
     * 异步运行，通过线程池获取新的线程运行
     *
     * @param executorService  the executor service
     * @param abstractCallBack the abstract call back
     * @throws RetryException the retry exception
     */
    private void doAsyncRun(ExecutorService executorService, AbstractCallBack abstractCallBack) throws RetryException {
        if (null == executorService) {
            throw new RetryException("please choose executorService!");
        }
        executorService.execute(() -> {
            try {
                doRun(abstractCallBack);
            } catch (RetryException | InterruptedException e) {
//                 log.error(String.format("error info: =====> %s", e.getMessage()));
            }
        });
        executorService.shutdown();
    }

    /**
     * 策略选择 是否需要异步 or 回调
     *
     * @param abstractCallBack the abstract call back
     * @throws RetryException       the retry exception
     * @throws InterruptedException the interrupted exception
     */
    private void strategyChoose(AbstractCallBack abstractCallBack) throws RetryException, InterruptedException {
        if (retryConfigurable.getAsync()) {
            doAsyncRun(executorService, abstractCallBack);
        } else {
            doRun(abstractCallBack);
        }
    }

}
