package ua.nure.cs.neichev.usermanagement.domain.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.neichev.usermanagement.domain.User;
import ua.nure.cs.neichev.usermanagement.domain.db.DaoFactory;
import ua.nure.cs.neichev.usermanagement.domain.db.DatabaseException;
import ua.nure.cs.neichev.usermanagement.domain.db.UserDao;

public class EditPanel extends JPanel implements ActionListener {
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor;
	
	public EditPanel(MainFrame parent, User usr) {
		this.parent = parent;
		initialize(usr);
	}
	
	private void initialize(User usr) {
		// TODO Auto-generated method stub
		this.setName("editPanel");
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		userDao = DaoFactory.getInstance().getUserDao();
		this.user = usr;
		setFields();
	}
	private JPanel getButtonPanel() {
		// TODO Auto-generated method stub
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		// TODO Auto-generated method stub
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		// TODO Auto-generated method stub
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ok");
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel() {
		// TODO Auto-generated method stub
		if (fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(2, 3));
			addLabeledField(fieldPanel, "Name", getFirstNameField());
			addLabeledField(fieldPanel, "Surname", getLastNameField());
			addLabeledField(fieldPanel, "Date of birth", getDateOfBirthField());
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		// TODO Auto-generated method stub
		if (dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dayOfBirthField");
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		// TODO Auto-generated method stub
		if (lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		// TODO Auto-generated method stub
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private JTextField getFirstNameField() {
		// TODO Auto-generated method stub
		if (firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");
		}
		return firstNameField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}
			try {
				parent.getDao().update(user);
			} catch (DatabaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		parent.showBrowsePanel();
	}
}