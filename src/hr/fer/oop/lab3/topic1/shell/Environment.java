package hr.fer.oop.lab3.topic1.shell;

/**
 * 
 * @author Josip Gatjal
 *
 */
public interface Environment {
	
	public String readLine();
	
	public void write(String s);
	
	public void writeln(String s);
	
	public Terminal getActiveTerminal();
	
	public void setActiveTerminal(Terminal ter);
	
	public Terminal getOrCreateTerminal(int num);
	
	public Terminal[] listTerminals();
	
	public Iterable commands();
	

}
