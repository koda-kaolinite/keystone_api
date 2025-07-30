package com.ts.keystone.api.webAdapter.property.queries;

import com.ts.keystone.api.webAdapter.property.responses.ImageDTO;
import com.ts.keystone.api.sharedKernel.application.events.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
public class GetImageQuery extends BaseQuery<ImageDTO> {
    private UUID propertyUUID;
    private UUID imageUUID;
}
