package hr.fer.oop.topic10.db;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class StudentsPanel extends JPanel{
	
	//stvori komponente
	
	//combobox
	final JComboBox<String> box = new JComboBox<>();
	
	//labele
	JLabel jmbagLabel = new JLabel("JMBAG");
	JLabel surnameLabel = new JLabel("Prezime");
	JLabel nameLabel = new JLabel("Ime");
	JLabel predmetiLabel = new JLabel("Upisani predmeti");
	
	//textfield
	JTextField surnameText = new JTextField(15);
	JTextField jmbagText= new JTextField(15);
	JTextField nameText = new JTextField(15);
	
	//gumbovi
	JButton[] button = new JButton[100];
	JPanel panel2 = new JPanel();
	
	//trenutni student
	static List<StudentRecord> student = new ArrayList<StudentRecord>();
	
	GridBagConstraints gc = new GridBagConstraints();
	
	
	public StudentsPanel(final StudentDatabase students,final CourseDatabase courses,
			final EnrolmentDatabase enroles,List<String> studenti,final JTabbedPane pane){
		
		//dodaj iteme u comboBox
		for(int i = 0; i < studenti.size(); i++)
			box.addItem(studenti.get(i));
		
		//dohvati jmbag
		Object selected = box.getSelectedItem();
		String prezime = selected.toString().split(" ")[1];
		LastNameFilter lastName = new LastNameFilter(prezime);
		student = students.filter(lastName);
		final String jmbag = student.get(0).jmbag;
		
		//postavi velicinu
		Dimension size = getPreferredSize();
		setPreferredSize(size);		
		
		//postavi pocetne vrijednosti textFieldova
		nameText.setEditable(false);
		nameText.setText(students.forJMBAG(jmbag).firstName);
		
		surnameText.setEditable(false);
		surnameText.setText(students.forJMBAG(jmbag).lastName);
		
		jmbagText.setEditable(false);
		jmbagText.setText(students.forJMBAG(jmbag).jmbag);
		
		
		List<EnrolmentRecord> mapa = new ArrayList<EnrolmentRecord>();
		mapa = (List<EnrolmentRecord>) enroles.findByStudent(jmbag);
		List<String> predmetiGumbovi = new ArrayList<>();
		
		//dodaj imena predmeta u listu
		for(int i = 0; i < mapa.size() ; i++) {
			CourseRecord record = courses.findById(mapa.get(i).getCourseID());
			predmetiGumbovi.add(record.courseName);
		}
		
		
		//postavi layout
		setLayout(new GridBagLayout());
		
		
		//postavi action listener za comboBox
		box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            
             
                JComboBox comboBox = (JComboBox) event.getSource();
                
                //dohvati sto je trenutno u comboBox odabrano
                Object selected = comboBox.getSelectedItem();
                String prezime = selected.toString().split(" ")[1];
                
                //pronađi studenta sa tim prezimenom
                LastNameFilter lastName = new LastNameFilter(prezime);
                student = students.filter(lastName);
                String jmbag = student.get(0).jmbag;
                
                //pozovi funkciju za obnovu panea
                obnovi(jmbag,students,courses,enroles,pane);
                updateUI();
            }
        });
		
		//postavljanje komponenata u panel
		
		//dodaj comboBox
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;

		gc.gridx = 1;
		gc.gridy = 0;
		add(box,gc);
		
		//dodaj jmbaglabel
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		add(jmbagLabel,gc);
		
		
		//dodaj surnameLabel
		gc.gridx = 0;
		gc.gridy = 2;
		add(surnameLabel,gc);
		
		
		//dodaj nameLabel
		gc.weighty = 2;
		gc.gridx = 0;
		gc.gridy = 3;
		add(nameLabel,gc);
		
		
		//dodaj predmetiLabel
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 4;
		add(predmetiLabel,gc);

		
		//dodaj nameTextField
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		add(nameText,gc);
		
		
		//dodaj jmbagTextField
		gc.gridx = 1;
		gc.gridy = 1;
		add(jmbagText,gc);
		
		
		//dodaj surnameTextField
		gc.gridx = 1;
		gc.gridy = 2;
		add(surnameText,gc);
		
		
		gc.gridx = 1;
		gc.gridy = 5;
		
		//stvori gumbove predmeta za studenta
		for(int i = 0; i < predmetiGumbovi.size() ; i++){
			final int j = i;
			final List<EnrolmentRecord> mapa2 = mapa;
			
			button[i] = new JButton(predmetiGumbovi.get(i));
			//dodaj listener za button
			button[i].addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent ae){
			    	   
			    	  //pronadji ocjena studenta za kliknuti predmet
			          EnrolmentRecord record = enroles.findByStudentAndCourse(jmbag, mapa2.get(j).getCourseID());
			          int ocjena = record.getGrade();
			          JOptionPane.showMessageDialog(pane, "Final Grade: " + ocjena);
			       }
			      });
			
			gc.anchor  = GridBagConstraints.FIRST_LINE_START;
			add(button[i],gc);
			updateUI();
			gc.gridy++;
			
			
		}

		
	}
	
	//metoda za obnovu panela
	public void obnovi(final String jmbag, final StudentDatabase students,final CourseDatabase courses,final EnrolmentDatabase enroles,final JTabbedPane pane){
		removeAll();
		
		Dimension size = getPreferredSize();
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder(""));
		
		//postavi pocetne opcije komponenata
		nameText.setEditable(false);
		nameText.setText(students.forJMBAG(jmbag).firstName);
		
		
		surnameText.setEditable(false);
		surnameText.setText(students.forJMBAG(jmbag).lastName);
		
		
		jmbagText.setEditable(false);
		jmbagText.setText(students.forJMBAG(jmbag).jmbag);
		
		
		List<EnrolmentRecord> mapa = new ArrayList<EnrolmentRecord>();
		mapa = (List<EnrolmentRecord>) enroles.findByStudent(jmbag);
		List<String> predmetiGumbovi = new ArrayList<>();
		
		//dodaj imena predmeta u listu
		for(int i = 0; i < mapa.size() ; i++) {
			CourseRecord record = courses.findById(mapa.get(i).getCourseID());
			predmetiGumbovi.add(record.courseName);
		}
		
		//postavi layout
		setLayout(new GridBagLayout());
		
		
		
		//postavljanje komponenata u panel
		
		//dodaj comboBox
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;

		gc.gridx = 1;
		gc.gridy = 0;
		add(box,gc);
		
		//dodaj jmbaglabel
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 1;
		add(jmbagLabel,gc);
		
		
		//dodaj surnameLabel
		gc.gridx = 0;
		gc.gridy = 2;
		add(surnameLabel,gc);
		
		
		//dodaj nameLabel
		gc.weighty = 2;
		gc.gridx = 0;
		gc.gridy = 3;
		add(nameLabel,gc);
		
		
		//dodaj predmetiLabel
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 4;
		add(predmetiLabel,gc);

		
		//dodaj nameTextField
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		add(nameText,gc);
		
		
		//dodaj jmbagTextField
		gc.gridx = 1;
		gc.gridy = 1;
		add(jmbagText,gc);
		
		
		//dodaj surnameTextField
		gc.gridx = 1;
		gc.gridy = 2;
		add(surnameText,gc);
		
		
		gc.gridx = 1;
		gc.gridy = 5;
		
		//dodaj gumbove predmeta
		for(int i = 0; i < predmetiGumbovi.size() ; i++){
			final int j = i;
			final List<EnrolmentRecord> mapa2 = mapa;
			
			button[i] = new JButton(predmetiGumbovi.get(i));
			
			//dodaj listener za button
			button[i].addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent ae){
			    	   
			    	  //pronadji ocjenu studenta za kliknuti predmet
			          EnrolmentRecord record = enroles.findByStudentAndCourse(jmbag, mapa2.get(j).getCourseID());
			          int ocjena = record.getGrade();
			          JOptionPane.showMessageDialog(pane, "Final Grade: " + ocjena);
			       }
			      });
			
			gc.anchor  = GridBagConstraints.FIRST_LINE_START;
			
			add(button[i],gc);
			updateUI();
			gc.gridy++;
			
		}

		
	}


}
