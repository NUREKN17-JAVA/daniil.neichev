package ua.nure.cs.neichev.usermanagement.domain.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.INITIALIZE;

import ua.nure.cs.neichev.usermanagement.domain.User;
import ua.nure.cs.neichev.usermanagement.domain.db.DaoFactory;
import ua.nure.cs.neichev.usermanagement.domain.db.UserDao;

public class MainFrame extends JFrame {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private AddPanel addPanel;
	private UserDao dao;
	private EditPanel editPanel;
	private DetailsPanel detailsPanel;
	private UserTableModel utm;
	private DeletePanel deletePanel;

	public UserDao getDao() {
		return dao;
	}

	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
		utm = new UserTableModel();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("User management");
		this.setContentPane(getJContentPanel());
	}

	private JPanel getJContentPanel() {
		// TODO Auto-generated method stub
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}

	private JPanel getBrowsePanel() {
		// TODO Auto-generated method stub
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		frame.setVisible(true);

	}

	public void showAddPanel() {
		// TODO Auto-generated method stub
		showPanel(getAddPanel());
	}
	
	public void showBrowsePanel() {
		// TODO Auto-generated method stub
		showPanel(getBrowsePanel());
	}

	private void showPanel(JPanel panel) {
		// TODO Auto-generated method stub
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}

	private AddPanel getAddPanel() {
		// TODO Auto-generated method stub
		if (addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}
	
	private EditPanel getEditPanel(User user) {
		// TODO Auto-generated method stub
		if (editPanel == null) {
			editPanel = new EditPanel(this, user);
		}
		return editPanel;
	}
	
	private DetailsPanel getDetailsPanel(User user) {
		// TODO Auto-generated method stub
		if (detailsPanel == null) {
			detailsPanel = new DetailsPanel(this, user);
		}
		return detailsPanel;
	}
	
	private DeletePanel getDeletePanel(User user) {
		// TODO Auto-generated method stub
		if (deletePanel == null) {
			deletePanel = new DeletePanel(this, user);
		}
		return deletePanel;
	}

	public void showEditPanel(int selectedRow) {
		// TODO Auto-generated method stub
		showPanel(getEditPanel(utm.getValueAt(selectedRow)));
	}
	
	public void showDetailsPanel(int selectedRow) {
		// TODO Auto-generated method stub
		showPanel(getDetailsPanel(utm.getValueAt(selectedRow)));
	}

	public void showDeletePanel(int selectedRow) {
		// TODO Auto-generated method stub
		showPanel(getDeletePanel(utm.getValueAt(selectedRow)));
	}
}
