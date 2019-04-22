package com.salesforce.tests.fs;

import java.util.HashMap;

import com.salesforce.tests.fs.command.impl.ChangeDirectoryCommand;
import com.salesforce.tests.fs.command.impl.CreateFileCommand;
import com.salesforce.tests.fs.command.impl.CurrentDirectoryCommand;
import com.salesforce.tests.fs.command.impl.ListContentCommand;
import com.salesforce.tests.fs.command.impl.MakeDirectoryCommand;
import com.salesforce.tests.fs.command.impl.QuitCommand;

import com.salesforce.tests.fs.command.spec.Command;

public class CommandExecutor {

  private static final String QUIT = "quit";
  private static final String PWD = "pwd";
  private static final String LS = "ls";
  private static final String MKDIR = "mkdir";
  private static final String CD = "cd";
  private static final String TOUCH = "touch";

  private static final HashMap<String, Command> COMMANDS = new HashMap<String, Command>() {{
    put(QUIT, new QuitCommand());
    put(PWD, new CurrentDirectoryCommand());
    put(LS, new ListContentCommand());
    put(MKDIR, new MakeDirectoryCommand());
    put(CD, new ChangeDirectoryCommand());
    put(TOUCH, new CreateFileCommand());
  }};

  static boolean isValidCommand(String command) {
    boolean isValidCommand = false;

    for (String key : COMMANDS.keySet()) {
      if (command.startsWith(key) == true) {
        isValidCommand = true;
        break;
      }
    }

    return isValidCommand;
  }

  static void execute(String command) {
    String[] parameters;

    if (command.startsWith(QUIT) == true) {
      COMMANDS.get(QUIT).execute();
    }
    else if (command.startsWith(PWD) == true) {
      COMMANDS.get(PWD).execute();
    }
    else if (command.startsWith(LS) == true) {
      parameters = command.split(" ");

      if (parameters.length == 1) {
        COMMANDS.get(LS).execute();
      }
      else if (parameters.length == 2) {
        COMMANDS.get(LS).execute(parameters[1]);
      }
    }
    else if (command.startsWith(MKDIR) == true) {
      parameters = command.split(" ");

      COMMANDS.get(MKDIR).execute(parameters[1]);
    }
    else if (command.startsWith(CD) == true) {
      parameters = command.split(" ");

      if (parameters.length == 1) {
        COMMANDS.get(CD).execute();
      }
      else if (parameters.length == 2) {
        COMMANDS.get(CD).execute(parameters[1]);
      }
    }
    else {
      parameters = command.split(" ");

      COMMANDS.get(TOUCH).execute(parameters[1]);
    }
  }
}
