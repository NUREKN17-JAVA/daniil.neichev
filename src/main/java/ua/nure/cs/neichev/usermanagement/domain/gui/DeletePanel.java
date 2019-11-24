package ua.nure.cs.neichev.usermanagement.domain.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.neichev.usermanagement.domain.User;
import ua.nure.cs.neichev.usermanagement.domain.db.DaoFactory;
import ua.nure.cs.neichev.usermanagement.domain.db.DatabaseException;
import ua.nure.cs.neichev.usermanagement.domain.db.UserDao;

public class DeletePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private Color bgColor;
	private JLabel sureText;
	
	public DeletePanel(MainFrame parent, User usr) {
		this.parent = parent;
		initialize(usr);
	}
	
	private void initialize(User usr) {
		this.setName("deletePanel");
		this.setLayout(new BorderLayout());
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		userDao = DaoFactory.getInstance().getUserDao();
		this.user = usr;
	}
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
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
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ok");
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			try {
				userDao.delete(this.user);
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
		}
		this.setVisible(false);
		parent.showBrowsePanel();
	}
}
