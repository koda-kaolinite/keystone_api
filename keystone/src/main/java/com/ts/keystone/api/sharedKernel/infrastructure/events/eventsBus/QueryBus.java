package com.ts.keystone.api.sharedKernel.infrastructure.events.eventsBus;

import com.ts.keystone.api.sharedKernel.application.events.query.IQuery;
import com.ts.keystone.api.sharedKernel.application.events.query.IQueryHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class QueryBus {

    private final ApplicationContext applicationContext;
    private final Map<Class<? extends IQuery>, IQueryHandler> queryHandlers = new HashMap<>();

    public QueryBus(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void RegisterHandlers() {
        Map<String, IQueryHandler> beans = applicationContext.getBeansOfType(IQueryHandler.class);
        for (IQueryHandler queryHandler : beans.values()) {
            Class<?> queryType = GenericTypeResolver.resolveTypeArgument(queryHandler.getClass(), IQueryHandler.class);

            if (queryType != null) {
                queryHandlers.put((Class<? extends IQuery>) queryType, queryHandler);
            }
        }
    }

    public void register(Class<? extends IQuery> queryClass, IQueryHandler handler) {
        queryHandlers.put(queryClass, handler);
    }

    @SuppressWarnings("unchecked")
    public <R> CompletableFuture<R> dispatch(IQuery<R> query) {
        IQueryHandler handler = queryHandlers.get(query.getClass());
        if (handler == null) {
            return CompletableFuture.failedFuture(new IllegalStateException("No handler for " + query.getClass()));
        }
        return handler.handle(query);
    }
}
