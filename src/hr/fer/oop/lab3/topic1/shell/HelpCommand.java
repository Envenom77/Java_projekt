package hr.fer.oop.lab3.topic1.shell;

public class HelpCommand extends AbstractCommand {

	static String commandName = "help"; 
	static String commandDescription = "Writes out all possible commands with their description";
	
	public HelpCommand() {
		super(commandName, commandDescription);
		
	}
	
	public CommandStatus execute(Environment env, String s){
		
		Iterable<ShellCommand> commands = env.commands();
		
		for(ShellCommand cmd: commands){
			env.write("\t");
			env.write(cmd.getCommandName());
			env.write(" -- ");	
			env.writeln(cmd.getCommandDescription());
		}
		return CommandStatus.CONTINUE;
	}

}
