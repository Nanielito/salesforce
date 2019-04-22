package com.salesforce.tests.fs.command.impl;

import java.io.File;

import com.salesforce.tests.fs.command.spec.Command;

public class ChangeDirectoryCommand implements Command {

  private static final String USER_DIR_PROPERTY = "user.dir";
  private static final String DIRECTORY_NOT_FOUND = "Directory not found";
  private static final String FILE_IS_NOT_A_DIRECTORY = "%s is not a directory";
  private static final String DIRECTORY_PATH_PATTERN = "%s%s%s";

  private String rootDirectoryPath = USER_DIR_PROPERTY;

  private void changeDirectory(String directoryPath) {
    File file = new File(directoryPath);

    if (file.exists() == true) {
      if (file.isDirectory() == true) {
        System.setProperty(USER_DIR_PROPERTY, file.getAbsolutePath());
      }
      else {
        System.out.println(String.format(FILE_IS_NOT_A_DIRECTORY, directoryPath));
      }
    }
    else {
      System.out.println(DIRECTORY_NOT_FOUND);
    }
  }

  @Override
  public void execute() {
    changeDirectory(rootDirectoryPath);
  }

  @Override
  public void execute(String parameter) {
    String directoryPath = String.format(
        DIRECTORY_PATH_PATTERN,
        System.getProperty(USER_DIR_PROPERTY),
        File.separator,
        parameter);

    changeDirectory(directoryPath);
  }
}
