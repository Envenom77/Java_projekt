package hr.fer.oop.lab3.topic1.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * @author Josip Gatjal
 *
 */
public class MyShell {
	
	private static SimpleHashTable commands = new SimpleHashTable();
	
	static {
		
		ShellCommand[] cc = {
				new HelpCommand(),
				new QuitCommand(),
				new CdCommand(),
				new TerminalCommand(),
				new TypeFilename(),
				new FilterCommand(),
				new CopyCommand(),
				new XcopyCommand()
		};
		
		for(ShellCommand c : cc){
			System.out.println(commands.size);
			commands.put(c.getCommandName(), c);
		}
	}
	
	public static class EnvironmentImpl implements Environment {
		
		SimpleHashTable terminals = new SimpleHashTable();
		Terminal activeTerminal;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		

		@Override
		public String readLine() {
			
			String linija = "";
			
			try {
				linija = reader.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			return linija;
		}

		@Override
		public void write(String s) {
			try {
				writer.write(s);
				writer.flush();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}

		@Override
		public void writeln(String s) {
			try {
				writer.write(s);
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}

		@Override
		public Terminal getActiveTerminal() {
			
			return activeTerminal;
		}

		@Override
		public void setActiveTerminal(Terminal ter) {
			this.activeTerminal = ter;
			
		}

		@Override
		public Terminal getOrCreateTerminal(int num) {
			
			//ako postoji terminal vrati njega
			if (terminals != null)
				if (terminals.containsKey(num)) {
					
					Terminal ter = (Terminal) terminals.get(num);
					setActiveTerminal(ter);
					terminals.put(num,ter);
					
					return ter;  
				}
			//ako ne postoji terminal, stvori ga i vrati istog
			Terminal ter = new Terminal(num);
			
			setActiveTerminal(ter);
			
			
			terminals.put(num, ter);
			
			return ter;
			
		}

		@Override
		public Terminal[] listTerminals() {
			
			Terminal[] terminali = new Terminal[terminals.size];
			
			return terminali;
		}

		@Override
		public Iterable commands() {

			return new Iterable<ShellCommand>() {

		@Override
		public Iterator<ShellCommand> iterator() {
			return new Iterator<ShellCommand>() {

			//vrati nam iterator iz SimpleHashtable-a (obican kakav je)
			Iterator<SimpleHashTable.tableEntry> i = commands.iterator();
	
			@Override
			public boolean hasNext() {
				//next je isti
				return i.hasNext();
			}
	
			@Override
			public ShellCommand next() {
				// mi ne zelimo vratit TableEntry objekt(koji se sastoji od vrijednosti Key i Value),
				// nego mi zelimo vratit Object stavljen pod Value() vrijednost.
				// Objekt koji vracamo MORAO je prihvatit ugovor da implementira ShellCommands.
				return (ShellCommand) i.next().getValue();
			}
	
			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			};
		}
		};
		
			
		}
		
	}
	
	private static Environment environment = new EnvironmentImpl();
	
	public static void main(String[] args) throws IOException {
		environment.getOrCreateTerminal(1);
		
		environment.writeln("Welcome to MyShell! You may enter commands");
		
		while(true) {
			int trenutni = environment.getActiveTerminal().getId();
			environment.write(trenutni  + "$" + environment.getActiveTerminal().getCurrentPath() + ">");
			String line = environment.readLine();
			
			String[] split = line.trim().split("\\s+");
			
			String cmd = split[0];
	
			String arg = "";
			
			for(int i = 1; i < split.length; i++) arg += split[i] + " ";
			
			String[] argumenti = arg.split("\\s+");
			
			//ako ne postoji takva naredba
			if( (commands.get(cmd)) instanceof Integer ) {
				environment.writeln("Unknown command!");
				continue;
			}
			
			ShellCommand shellCommand = (ShellCommand) commands.get(cmd);
			
			if(shellCommand == null) {
				environment.writeln("Unknown command!");
			}
			
			if(shellCommand.execute(environment, arg.trim()) == CommandStatus.EXIT) 
				break;
			
			
		}
		
		environment.writeln("Thank you for using this shell. Goodbye!");
	}
	
	
	
	

}
