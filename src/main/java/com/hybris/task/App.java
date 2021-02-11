package com.hybris.task;

import com.hybris.task.commands.CommandFacade;
import com.hybris.task.util.AppBootstrap;

import java.util.Scanner;

public class App 
{
//    public static void main( String[] args )
//    {
//        Options options = new Options();
//
//        Option input = new Option("i", "input", true, "input file path");
//        input.setRequired(true);
//        options.addOption(input);
//
//        Option output = new Option("o", "output", true, "output file");
//        output.setRequired(true);
//        options.addOption(output);
//
//        CommandLineParser parser = new DefaultParser();
//        HelpFormatter formatter = new HelpFormatter();
//        CommandLine cmd = null;
//        Scanner sc = new Scanner(System.in);
//
//
//        while (true) {
//            try {
//
//                for (int i = 0; i < args.length; i++) {
//                    System.out.println(args[i]);
//                }
//
//                Arrays.stream(args).forEach(System.out::println);
//
//                String line = sc.nextLine().trim();
//
//                if(line.length() > 0) {
//                    cmd = parser.parse(options, args);
//                    String inputFilePath = cmd.getOptionValue("input");
//                    String outputFilePath = cmd.getOptionValue("output");
//
//                    System.out.println(inputFilePath);
//                    System.out.println(outputFilePath);
////                    CommandFacade.run(line);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//                formatter.printHelp("utility-name", options);
//            }
//        }


//    }


    public static void main( String[] args )
    {
        try{
            AppBootstrap.init();

            Scanner sc = new Scanner(System.in);

            CommandFacade.run("help");

            while (true) {
                String line = sc.nextLine().trim();

                if(line.length() > 0) {
                    CommandFacade.run(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
