package com.hybris.task;

import com.hybris.task.commands.CommandFacade;
import com.hybris.task.util.AppBootstrap;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            AppBootstrap.init();

            Scanner sc = new Scanner(System.in);

            CommandFacade.run("help");

            while (true) {
                String line = sc.nextLine().trim();

                if (line.length() > 0) {
                    CommandFacade.run(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
