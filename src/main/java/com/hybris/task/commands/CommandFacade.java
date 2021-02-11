package com.hybris.task.commands;

import com.hybris.task.App;
import com.hybris.task.annotations.Command;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hybris.task.annotations.CommandParam;
import com.hybris.task.exceptions.CommandErrorException;
import org.reflections.Reflections;

public class CommandFacade {

    public static void run(String command) {

        try {
            CommandInterface commandInstance = CommandFacade.findCommand(command);
            CommandFacade.prepareCommand(commandInstance, command);
            commandInstance.run();

            System.out.println("> Success");
        } catch (CommandErrorException e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }

    public static List<Field> getAnnotatedFields(CommandInterface commandInstance) throws CommandErrorException {

        List<Field> fields = new ArrayList<>();

        for (Field field : commandInstance.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(CommandParam.class)) {
                continue;
            }
            fields.add(field);
        }

        return fields;
    }

    public static CommandInterface prepareCommand(CommandInterface commandInstance, String command) throws CommandErrorException { //TODO: refactor this method

        try {
            /* Check if any named params matches to params in command class */
            for (Field field : CommandFacade.getAnnotatedFields(commandInstance)) {

                CommandParam param = field.getAnnotation(CommandParam.class);

                String paramName = !"".equals(param.regex()) ? param.regex() : field.getName();
                String regex = String.format("(?<=[--{1,2}|\\/])(?<name>%s)=[\"|`]*(?<value>[\\w|.|,|(|)|?|&|+| |:|\\/|\\\\|\\-]*)(?=[ |\"]|$)", paramName);

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String value = matcher.group("value");
                    field.setAccessible(true);
                    field.set(commandInstance, value);
                } else {
                    if (param.required()) {
                        throw new CommandErrorException(String.format("--%s param is required", param.regex()));
                    }
                }
            }

        } catch (IllegalAccessException e) {
            throw new CommandErrorException(e.getMessage());
        }

        return commandInstance;
    }

    public static Set<Class<CommandInterface>> getSetOfCommandClass() {
        String packageName = App.class.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<CommandInterface>> set = new HashSet<>();
        reflections.getTypesAnnotatedWith(Command.class).forEach(cls -> {
            if (cls.isAnnotationPresent(Command.class)) {
                if (CommandInterface.class.isAssignableFrom(cls)) {
                    set.add((Class<CommandInterface>) cls);
                }
            }
        });
        return set;
    }

    public static CommandInterface findCommand(String command) throws CommandErrorException {

        /* Try found command class marked @Command annotation by regex*/
        for (Class<CommandInterface> annotatedClass : CommandFacade.getSetOfCommandClass()) {
            String commandName = annotatedClass.getAnnotation(Command.class).regex();
            String regex = String.format("^(?<command>%s)\\s?", commandName);
            command = command.trim();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(command);

            if (!matcher.find()) continue;

            try {
                return annotatedClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new CommandErrorException(e.getMessage());
            }
        }

        throw new CommandErrorException("Command not found");
    }
}
