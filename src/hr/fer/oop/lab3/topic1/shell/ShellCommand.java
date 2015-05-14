package hr.fer.oop.lab3.topic1.shell;

/**
 * 
 * @author Josip Gatjal
 *
 */
public interface ShellCommand {
	
	public String getCommandName();
	
	public String getCommandDescription();
	
	public CommandStatus execute(Environment env, String s);

}
