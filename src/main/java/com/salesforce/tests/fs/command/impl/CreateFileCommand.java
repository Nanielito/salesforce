package com.salesforce.tests.fs.command.impl;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.salesforce.tests.fs.command.spec.Command;

public class CreateFileCommand implements Command {

  private static final String USER_DIR_PROPERTY = "user.dir";
  private static final String NAME_EXCEEDED_LENGTH = "Name exceeded 100 characters";
  private static final String FILE_ALREADY_EXISTS = "File already exists";
  private static final String FILE_PATH_PATTERN = "%s%s%s";

  private static final int NAME_MAX_SIZE = 100;

  private void printError(Throwable throwable) {
    System.out.println(throwable.getMessage());
  }

  @Override
  @Deprecated
  public void execute() {

  }

  @Override
  public void execute(String parameter) {
    try {
      if (parameter.length() <= NAME_MAX_SIZE) {
        String filePath = String.format(
            FILE_PATH_PATTERN,
            System.getProperty(USER_DIR_PROPERTY),
            File.separator,
            parameter);
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
          Files.createFile(path);
        }
        else {
          throw new IOException(FILE_ALREADY_EXISTS);
        }
      }
      else {
        throw new IllegalArgumentException(NAME_EXCEEDED_LENGTH);
      }
    }
    catch (IllegalArgumentException ex) {
      printError(ex);
    }
    catch (IOException ex) {
      printError(ex);
    }
  }
}
