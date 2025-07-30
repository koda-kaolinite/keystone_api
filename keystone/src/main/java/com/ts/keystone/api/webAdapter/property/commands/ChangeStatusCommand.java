package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.webAdapter.property.requests.ChangeStatusRequest;
import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
public class ChangeStatusCommand extends BaseCommand<Void> {
    private UUID propertyUUID;
    private ChangeStatusRequest request;
}
