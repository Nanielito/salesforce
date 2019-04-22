package com.salesforce.tests.fs.command.spec;

public interface Command {

  void execute();

  void execute(String parameter);
}
