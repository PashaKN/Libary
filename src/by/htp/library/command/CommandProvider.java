package by.htp.library.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.commandImpl.ChangePassword;
import by.htp.library.commandImpl.GoTo1;
import by.htp.library.commandImpl.GoToChange;
import by.htp.library.commandImpl.GoToRegistration;
import by.htp.library.commandImpl.LogIn;
import by.htp.library.commandImpl.Registration;

public class CommandProvider {
	private Map<CommandName, Command> commandBox = new HashMap<>();

	public CommandProvider() {
		commandBox.put(CommandName.LOGIN, new LogIn());
		commandBox.put(CommandName.GOTO1, new GoTo1());
		commandBox.put(CommandName.GOTOREGISTRATION, new GoToRegistration());
		commandBox.put(CommandName.REGISTRATION, new Registration());
		commandBox.put(CommandName.GOTOCHANGEPASSWORDPAGE, new GoToChange());
		commandBox.put(CommandName.CHANGEPASSWORD, new ChangePassword());
	}

	public Command getCommand(String commandName) {
		Command executingCommand = commandBox.get(CommandName.valueOf(commandName.toUpperCase()));
		return executingCommand;

	}

}
