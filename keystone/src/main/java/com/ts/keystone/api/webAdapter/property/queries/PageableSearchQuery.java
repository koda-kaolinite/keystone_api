package com.ts.keystone.api.webAdapter.property.queries;

import com.ts.keystone.api.sharedKernel.application.events.query.BaseQuery;
import com.ts.keystone.api.webAdapter.property.requests.PageableFilters;
import com.ts.keystone.api.webAdapter.property.responses.PropertyDTO;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;

@Getter
public class PageableSearchQuery extends BaseQuery<Page<PropertyDTO>> {
    private final PageableFilters filters;
    private final int page;
    private final int size;

    public PageableSearchQuery(PageableFilters filters, int page, int size, CompletableFuture<Page<PropertyDTO>> resultFuture) {
        super(filters, resultFuture);
        this.filters = filters;
        this.page = page;
        this.size = size;
    }
}