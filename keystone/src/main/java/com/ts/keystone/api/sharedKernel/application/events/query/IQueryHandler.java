package com.ts.keystone.api.sharedKernel.application.events.query;

import java.util.concurrent.CompletableFuture;

public interface IQueryHandler<Q extends IQuery<R>, R> {
    CompletableFuture<R> handle(Q query);
}
