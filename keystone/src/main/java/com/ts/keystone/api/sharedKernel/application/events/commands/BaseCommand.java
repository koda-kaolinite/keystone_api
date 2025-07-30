package com.ts.keystone.api.sharedKernel.application.events.commands;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BaseCommand<TResult> implements ICommand<TResult> {
    private UUID commandUUID;
    private LocalDateTime dateOcurred;
}
