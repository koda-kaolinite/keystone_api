package com.ts.keystone.api.webAdapter.property.queries;

import com.ts.keystone.api.sharedKernel.application.events.query.BaseQuery;
import com.ts.keystone.api.webAdapter.property.requests.PageableFilters;
import com.ts.keystone.api.webAdapter.property.responses.PropertyDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
public class PageableSearchQuery extends BaseQuery<Page<PropertyDTO>> {
    private PageableFilters filters;
    private int page;
    private int size;
}
