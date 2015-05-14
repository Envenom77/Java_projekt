package hr.fer.oop.topic10.db;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class CoursesPanel extends JPanel{
	
	JComboBox<String> box = new JComboBox<String>();
	
	JLabel nameLabel = new JLabel("Ime i prezime");
	JLabel ocjenaLabel = new JLabel("ocjena");
	
	JTextField[] textField = new JTextField[20];
	JButton[] button = new JButton[20];
	
	
	GridBagConstraints gc = new GridBagConstraints();
	
	public CoursesPanel(final StudentDatabase students,final CourseDatabase courses,final EnrolmentDatabase enroles,List<String> predmeti,final JTabbedPane pane,JPanel panel){
		Dimension size = getPreferredSize();
		setPreferredSize(size);
	
		setBorder(BorderFactory.createTitledBorder(""));
		
		for(int i = 0; i < predmeti.size(); i++)
			box.addItem(predmeti.get(i));
		
		setLayout(new GridBagLayout());
		
		box.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            
             
                JComboBox comboBox = (JComboBox) event.getSource();

                
                String imePredmeta = (String) comboBox.getSelectedItem();
                
                CourseRecord predmet = courses.findFirstByName(imePredmeta);
        		
        		List<EnrolmentRecord> lista = new ArrayList<>();
        		
        		lista = (List<EnrolmentRecord>) enroles.findByCourse(predmet.getCourseID());
                
                obnovi(lista,students,courses,enroles,pane);
                updateUI();
            }
        });
		
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 0;
		add(box,gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 0;
		gc.gridy = 1;
		add(nameLabel,gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(ocjenaLabel,gc);
		
		
		gc.gridx = 0;
		gc.gridy = 2;
		
	//dodaj parne neparne
		
		String imePredmeta = (String) box.getSelectedItem();
		
		CourseRecord predmet = courses.findFirstByName(imePredmeta);
		
		List<EnrolmentRecord> lista = new ArrayList<>();
		
		lista = (List<EnrolmentRecord>) enroles.findByCourse(predmet.getCourseID());
		
		for(int i =  0; i < lista.size(); i++){
			gc.gridx = 0;
			//ime i prezime
			textField[i] = new JTextField(26);
			System.out.println(students.forJMBAG(lista.get(i).getStudentJMBAG()).GetFirstName());
			System.out.println(students.forJMBAG(lista.get(i).getStudentJMBAG()).GetFinalGrade());
			
			textField[i].setText(students.forJMBAG(lista.get(i).getStudentJMBAG()).GetFirstName() + " " + 
					students.forJMBAG(lista.get(i).getStudentJMBAG()).GetLastName() + "\t\t   " + 
					students.forJMBAG(lista.get(i).getStudentJMBAG()).GetFinalGrade());
			textField[i].setEditable(false);
			Font f2 = new Font("Engravers MT", Font.BOLD, 12);
			textField[i].setFont(f2);
			if(i%2 == 0)
				textField[i].setBackground(Color.lightGray);
			else
				textField[i].setBackground(Color.white);
			
			
			
			//gumb detalji
			button[i] = new JButton("Detalji");
			button[i].addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent ae){
			          pane.setSelectedIndex(0);
			          
			       }
			      });
			
			gc.anchor  = GridBagConstraints.FIRST_LINE_START;
			gc.gridy++;
			add(textField[i],gc);
			gc.gridx++;
			add(button[i],gc);
			updateUI();
		}
		
	}
	
	public void obnovi(final List<EnrolmentRecord> lista, final StudentDatabase students,final CourseDatabase courses,final EnrolmentDatabase enroles,final JTabbedPane pane){
		removeAll();
		
		
		Dimension size = getPreferredSize();
		setPreferredSize(size);
	
		setBorder(BorderFactory.createTitledBorder(""));
		
		
		setLayout(new GridBagLayout());
		
		//dodaj komponente
		
		//dodaj comboBox
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 0;
		add(box,gc);
		
		//dodaj nameLabel
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 0;
		gc.gridy = 1;
		add(nameLabel,gc);

		//dodaj label s ocjenom
		gc.gridx = 1;
		gc.gridy = 1;
		add(ocjenaLabel,gc);
		
		
		gc.gridx = 0;
		gc.gridy = 2;
		
		//dodaj text fieldove i buttone
		for(int i =  0; i < lista.size(); i++){
			
			gc.gridx = 0;
			
			//ime i prezime
			textField[i] = new JTextField(26);
			
			//pronadji studenta po predmeti i jmbagu
			EnrolmentRecord student = enroles.findByStudentAndCourse(lista.get(i).getStudentJMBAG(), lista.get(i).getCourseID());
			
			//postavi vrijednosti textFielda
			textField[i].setText(students.forJMBAG(lista.get(i).getStudentJMBAG()).GetFirstName() + " " + 
					students.forJMBAG(lista.get(i).getStudentJMBAG()).GetLastName() + "\t\t   " + 
					student.getGrade());
			
			//ukrasi
			textField[i].setEditable(false);
			Font f2 = new Font("Engravers MT", Font.BOLD, 12);
			textField[i].setFont(f2);
			
			if(i%2 == 0)
				textField[i].setBackground(Color.lightGray);
			else
				textField[i].setBackground(Color.white);
			
			
			
			//gumb detalji
			button[i] = new JButton("Detalji");
			button[i].addActionListener(new ActionListener() {
			       public void actionPerformed(ActionEvent ae){
			          pane.setSelectedIndex(0);
			          
			       }
			      });
			
			gc.anchor  = GridBagConstraints.FIRST_LINE_START;
			gc.gridy++;
			add(textField[i],gc);
			gc.gridx++;
			add(button[i],gc);
			updateUI();
		}

		
	}
	


}
