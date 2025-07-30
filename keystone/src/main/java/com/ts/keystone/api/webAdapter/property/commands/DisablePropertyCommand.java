package com.ts.keystone.api.webAdapter.property.commands;

import com.ts.keystone.api.sharedKernel.application.events.commands.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
public class DisablePropertyCommand extends BaseCommand<Void> {
    private UUID propertyUUID;
}
