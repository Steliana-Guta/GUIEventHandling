import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.*;

// A kind of JPanel that contains buttons/label/fields
public class DataPanel extends JPanel implements ActionListener
{
	private ImageIcon imageIcon;
	private JButton getIPButton;          // Button to retrieve local IP info
	private JLabel inputAddressLabel;        // Label over output
	private JTextField inputAddressField; // Area to display local info

	private JLabel remoteAddressLabel;
	private JTextField remoteAddressField;
	private JLabel remoteHostLabel;
	private JTextField remoteHostField;

	private JLabel imageLabel;
	private Timer timer;
	private int movementStep = 2;

	private int xIcon = 0;
	private int yIcon = 0;

	public DataPanel()
	{
		setLayout(null);                       // Suppress panel layout features

		getIPButton = new JButton("Get host");      // Create button to obtain local info
		getIPButton.setBounds(0,200,100,30);           // x, y, w, h within this JPanel
		getIPButton.addActionListener(this);
		add(getIPButton);                         // Add button to this JPanel

		inputAddressLabel = new JLabel("Address Name:");
		inputAddressLabel.setBounds(190, 170, 160,30);    // x, y, w, h within this JPanel
		add(inputAddressLabel);

		inputAddressField = new JTextField();          // Create output area for local info
		inputAddressField.setBounds(150, 200, 160,30);  // x, y, w, h within this JPanel
		inputAddressField.addActionListener(this);
		add(inputAddressField);                   // Add JTextField to this JPanel

		remoteAddressLabel = new JLabel("Remote address:");
		remoteAddressLabel.setBounds(0, 270, 100,30);
		add(remoteAddressLabel);

		remoteAddressField = new JTextField();
		remoteAddressField.setBounds(0,300,100,30);
		remoteAddressField.setEditable(false);
		add(remoteAddressField);

		remoteHostLabel = new JLabel("Remote host:");
		remoteHostLabel.setBounds(180, 270, 100,30);
		add(remoteHostLabel);

		remoteHostField = new JTextField();
		remoteHostField.setEditable(false);
		remoteHostField.setBounds(150,300,150,30);
		add(remoteHostField);

		imageIcon = new ImageIcon(getClass().getResource("/Java-logo.png")); // image is 300x158
		imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(0, 0, 300, 158);
		//add(imageLabel);

		timer = new Timer( 1000 / 30, this );
		timer.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// Can use tools to draw line, shapes, add images etc

		handleImageIconLocation(g);
	}

	private void handleImageIconLocation(Graphics g) {

		if (yIcon <= -160) {

			movementStep = 2;

		} else if (yIcon >= 0) {

			movementStep = -2;

		}

		yIcon += movementStep;

		imageIcon.paintIcon( this, g, xIcon, yIcon );

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == timer) {

			// handleTimerTick();

			repaint();

		}

		if (e.getSource() == getIPButton || e.getSource() == inputAddressField) {

			handleButtonClick();

		}

	}

	private void handleTimerTick() {

		int x = imageLabel.getLocation().x;
		int y = imageLabel.getLocation().y;

		if (y <= -160) {

			movementStep = 2;

		} else if (y >= 0) {

			movementStep = -2;

		}

		y += movementStep;

		imageLabel.setLocation(x, y);

	}

	private void handleButtonClick() {

		try {

			String address = inputAddressField.getText();
			InetAddress host = InetAddress.getByName(address);

			remoteAddressField.setText(host.getHostAddress());
			remoteHostField.setText(host.getHostName());

		} catch (UnknownHostException ex) {

			remoteAddressField.setText("Cannot resolve");
			remoteHostField.setText("Cannot resolve");

		}

	}

	private void handleButtonClickLocalHost() {

		try {

			InetAddress host = InetAddress.getLocalHost();

			String text = host.getHostAddress() + ":" + host.getHostName();

			inputAddressField.setText(text);

		} catch (UnknownHostException ex) {

			inputAddressField.setText("Cannot resolve");

		}

	}
}