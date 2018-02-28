import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;

/*Isaac Wen (2018-02-27)
 * */
public class GUI extends JFrame{
	public GUI() {
		super("Type Clicker");
		this.setSize(1280,720);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init();
	}
	private void init() {
	    this.getContentPane().setLayout(new BorderLayout());
	    XMLComponent xmlComponent = new XMLComponent();
	    this.getContentPane().add("Center", xmlComponent.build(this.getClass().getResourceAsStream("/resource/ui.xml")));
	}
	private void display() {
		
	}

	//See drive for layout info
}
class XMLComponent extends DefaultHandler {

	  private boolean containerActive = false;
	  private JPanel primaryContainer = new JPanel();
	  private SAXParser parser = new SAXParser();
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
	    if (name.equals("button"))
	      primaryContainer.add(new JButton(atts.getValue("label")));

	  }
	}