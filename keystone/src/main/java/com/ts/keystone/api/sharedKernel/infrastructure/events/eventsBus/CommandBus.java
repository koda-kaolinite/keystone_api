package com.ts.keystone.api.sharedKernel.infrastructure.events.eventsBus;

import com.ts.keystone.api.sharedKernel.application.events.commands.ICommand;
import com.ts.keystone.api.sharedKernel.application.events.commands.ICommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class CommandBus {

    private final ApplicationContext applicationContext;
    private final Map<Class<? extends ICommand>, ICommandHandler> commandHandlers = new HashMap<>();

    public CommandBus(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void RegisterHandlers() {
        Map<String, ICommandHandler> beans = applicationContext.getBeansOfType(ICommandHandler.class);
        for (ICommandHandler commandHandler : beans.values()) {
            Class<?> commandType = GenericTypeResolver.resolveTypeArgument(commandHandler.getClass(), ICommandHandler.class);

            if (commandType != null) {
                commandHandlers.put((Class<? extends ICommand<?>>) commandType, commandHandler);
            }
        }
    }

    public void register(Class<? extends ICommand> commandClass, ICommandHandler handler) {
        commandHandlers.put(commandClass, handler);
    }

    @SuppressWarnings("unchecked")
    public <R> CompletableFuture<R> dispatch(ICommand<R> command) {
        ICommandHandler handler = commandHandlers.get(command.getClass());
        if (handler == null) {
            return CompletableFuture.failedFuture(new IllegalStateException("No handler for " + command.getClass()));
        }
        return handler.handle(command);
    }
}


