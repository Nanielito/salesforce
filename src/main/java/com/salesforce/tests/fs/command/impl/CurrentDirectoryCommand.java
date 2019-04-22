package com.salesforce.tests.fs.command.impl;

import com.salesforce.tests.fs.command.spec.Command;

public class CurrentDirectoryCommand implements Command {

  private static final String USER_DIR_PROPERTY = "user.dir";

  @Override
  public void execute() {
    String pwd = System.getProperty(USER_DIR_PROPERTY);
    System.out.println(pwd);
  }

  @Override
  @Deprecated
  public void execute(String parameter) {

  }
}
