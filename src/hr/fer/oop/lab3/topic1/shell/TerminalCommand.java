package hr.fer.oop.lab3.topic1.shell;

public class TerminalCommand extends AbstractCommand {
	
	static String commandName = "terminal";
	static String commandDescription = "Creates a new terminal";

	
	public TerminalCommand() {
		super(commandName, commandDescription);
	}
	
	public CommandStatus execute(Environment env, String s){
		
		try{
		Terminal ter = env.getOrCreateTerminal(Integer.parseInt(s));
		} catch(NumberFormatException e) {
			System.out.println("Terminals must be defined by a number!");
			return CommandStatus.CONTINUE;
		}
		
		
		return CommandStatus.CONTINUE;
	}

}
