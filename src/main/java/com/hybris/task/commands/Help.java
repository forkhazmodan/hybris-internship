package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.annotations.CommandParam;
import de.vandermeer.asciitable.AsciiTable;

import java.lang.reflect.InvocationTargetException;

@Command(regex = "help", description = "Show all commands")
public class Help implements CommandInterface {

    @Override
    public void run() {


            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow("Commands", "Params", "Description");
            at.addRule();
            CommandFacade.getSetOfCommandClass().forEach(cls -> {
                try {
                    CommandInterface commandInstance = null;
                        String commandRegex = cls.getAnnotation(Command.class).regex();
                        String commandDescription = cls.getAnnotation(Command.class).description();
                        String example = cls.getAnnotation(Command.class).example();

                        commandInstance = cls.getConstructor().newInstance();

                        StringBuilder sb = new StringBuilder();

                        CommandFacade.getAnnotatedFields(commandInstance).forEach(field -> {
                            String paramRegex = field.getAnnotation(CommandParam.class).regex();
                            String paramDescription = field.getAnnotation(CommandParam.class).description();
                            String paramExample = field.getAnnotation(CommandParam.class).example();

                            boolean paramIsRequired = field.getAnnotation(CommandParam.class).required();

                            if(paramIsRequired) {
                                sb.append("<br />").append("--").append(paramRegex).append("=").append(paramExample);
                            } else {
                                sb.append("<br />").append("[").append("--").append(paramRegex).append("=").append(paramExample).append("]");
                            }
                        });

                    at.addRow(commandRegex, sb.toString(), commandDescription);
                    at.addRule();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });

            System.out.println(at.render());

    }
}
