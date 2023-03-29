import org.openqa.selenium.chrome.ChromeDriver;

public class Automation {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://testphp.vulnweb.com/login.php");
    }
}
