package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import com.ts.keystone.api.webAdapter.property.requests.CreateHouseRequest;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateHouseCommand extends BaseCommand<UUID> {
    private final CreateHouseRequest createHouseRequest;
}
