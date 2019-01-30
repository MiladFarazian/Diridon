import javax.swing.UIManager;

public class PersonalProject {
	public static void main(String [] args) {
		//Adjusts Text to Better Fit User's Screen
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Oops! Your system is not supported! Try running it on a Windows PC!");
		}
		//Runs Diridon Program Through Constructor
		new Diridon();
	}
}
