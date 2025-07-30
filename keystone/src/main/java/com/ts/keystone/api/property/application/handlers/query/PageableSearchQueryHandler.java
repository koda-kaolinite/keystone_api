package com.ts.keystone.api.property.application.handlers.query;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.sharedKernel.application.events.query.IQueryHandler;
import com.ts.keystone.api.webAdapter.property.queries.PageableSearchQuery;
import com.ts.keystone.api.webAdapter.property.responses.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class PageableSearchQueryHandler implements IQueryHandler<PageableSearchQuery, Page<PropertyDTO>> {

    private final IPropertyRepository propertyRepository;

    @Override
    public CompletableFuture<Page<PropertyDTO>> handle(PageableSearchQuery query) {
        return CompletableFuture.supplyAsync(() -> {
            Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
            // TODO: Apply filters from query.getFilters()
            return propertyRepository.findAll(pageable).map(PropertyDTO::convert);
        });
    }
}
