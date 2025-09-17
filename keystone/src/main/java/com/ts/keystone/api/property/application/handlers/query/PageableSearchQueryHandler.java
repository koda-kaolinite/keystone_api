package com.ts.keystone.api.property.application.handlers.query;

import com.ts.keystone.api.property.application.IPropertyRepository;
import com.ts.keystone.api.webAdapter.property.queries.PageableSearchQuery;
import com.ts.keystone.api.webAdapter.property.responses.PropertyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageableSearchQueryHandler {

    private final IPropertyRepository propertyRepository;

    @EventListener
    public void handle(PageableSearchQuery query) {
        try {
            Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
            // TODO: Apply filters from query.getFilters()
            Page<PropertyDTO> result = propertyRepository.findAll(pageable).map(PropertyDTO::convert);
            query.getResultFuture().complete(result);
        } catch (Exception e) {
            query.getResultFuture().completeExceptionally(e);
        }
    }
}