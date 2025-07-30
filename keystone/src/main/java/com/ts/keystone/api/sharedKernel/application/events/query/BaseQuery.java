package com.ts.keystone.api.sharedKernel.application.events.query;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BaseQuery<TResult> implements IQuery<TResult> {
    private UUID queryUUID;
    private LocalDateTime dateOcurred;
}
