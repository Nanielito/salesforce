package com.salesforce.tests.fs.command.impl;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.salesforce.tests.fs.command.spec.Command;

public class ListContentCommand implements Command {

  private static final String USER_DIR_PROPERTY = "user.dir";
  private static final String RECURSIVE_FLAG = "-r";
  private static final String ILLEGAL_FLAG_PARAMETER = "Illegal flag parameter";

  private void printError(Throwable throwable) {
    System.out.println(throwable.getMessage());
  }

  @Override
  public void execute() {
    String directoryPath = String.format(System.getProperty(USER_DIR_PROPERTY));

    try {
      Files.newDirectoryStream(Paths.get(directoryPath))
        .forEach(System.out::println);
    }
    catch (IOException ex) {
      printError(ex);
    }
  }

  @Override
  public void execute(String parameter) {
    String directoryPath = String.format(System.getProperty(USER_DIR_PROPERTY));

    try {
      if (parameter.equals(RECURSIVE_FLAG)) {
        Files.walk(Paths.get(directoryPath))
          .filter(Files::isRegularFile)
          .forEach(System.out::println);
      }
      else {
        throw new IllegalArgumentException(ILLEGAL_FLAG_PARAMETER);
      }
    }
    catch (IOException | IllegalArgumentException ex) {
      printError(ex);
    }
  }
}
