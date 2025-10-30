import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthTest2 {
    private static WebDriver driver;
    @BeforeAll
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @CsvSource({
            "Tống An Khang,tongankhang0409@gmail.com,Tongankhang2007"
    })
    public void register(String name, String email, String password){
        driver.get("https://hoctotlamhay.vn/register");
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(name);
        WebElement emailField = driver.findElement(By.id("user_email"));
        emailField.sendKeys(email);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
//        WebElement button = By.xpath("//button[contains(text(),'Đăng ký')]").findElement(driver);
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"));
        button.click();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
