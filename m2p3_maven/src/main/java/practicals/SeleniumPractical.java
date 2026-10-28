package practicals;
import org.openqa.selenium.*; 
import org.openqa.selenium.chrome.ChromeDriver; 
import java.nio.file.*; 
import java.time.Duration;

public class SeleniumPractical { 
	public static void main(String[] args) throws Exception {
		Path downloads=Path.of("target","downloads").toAbsolutePath(); 
		Files.createDirectories(downloads);
		WebDriver d=new ChromeDriver(); 
		try {
			d.get(System.getProperty("test.url","https://the-internet.herokuapp.com/upload"));
			WebElement input=d.findElement(By.id("file-upload")); input.sendKeys(Path.of("sample-upload.txt").toAbsolutePath().toString()); d.findElement(By.id("file-submit")).click();
			System.out.println("Upload result="+d.findElement(By.id("uploaded-files")).getText());
			System.out.println("Configure Chrome download preferences for download testing; download directory="+downloads);
			}
		finally {d.quit();}
	}
}