import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

/*Isaac Wen (2018-02-27)
 * */
public class GUI extends JFrame{
	private String currentType;
	public GUI() {
		super("Type Clicker");	
		currentType = "";
		this.setSize(1280,720);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init();
	}
	private void init() {
		this.setVisible(true);
	}
	public void update(String i) {
		this.removeAll();
		this.getContentPane().setLayout(new BorderLayout());
		XMLComponent xmlComponent = new XMLComponent();
		this.add("Center", xmlComponent.build(this.getClass().getResourceAsStream("/resource/ui.xml")));
		currentType = i;
		display();
	}
	private void display() {

	}

	//See drive for layout info
}
class XMLComponent extends DefaultHandler {
	private JPanel primaryContainer = new JPanel();
	private SAXParser parser = new SAXParser();
	private ArrayList<JComponent> components = new ArrayList<>();
	public XMLComponent() {
		super();
	}
	public JComponent build(String xmlDocument) {
		parser.setContentHandler(this);
		try {
			parser.parse(new InputSource(new FileInputStream(xmlDocument)));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return primaryContainer;
	}
	public JComponent build(InputStream a) {
		parser.setContentHandler(this);
		try {
			parser.parse(new InputSource(a));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return primaryContainer;
	}
	public void startElement(String namespaceURI, String name, String qName, Attributes atts) {
		if (name.equals("button")) {
			components.add(new JButton(atts.getValue("label")));
			primaryContainer.add(components.get(components.size()-1));
		}
		if (name.equals("text")) {
			components.add(new JLabel(atts.getValue("label")));
			primaryContainer.add(new JLabel(atts.getValue("label")));
		}
	}
}