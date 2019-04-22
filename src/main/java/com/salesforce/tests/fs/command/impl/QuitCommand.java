package com.salesforce.tests.fs.command.impl;

import com.salesforce.tests.fs.command.spec.Command;

public class QuitCommand implements Command {

  @Override
  public void execute() {
    System.exit(0);
  }

  @Override
  @Deprecated
  public void execute(String parameter) {

  }
}
