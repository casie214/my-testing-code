import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthenticationTest {
    private static WebDriver WebDriver;
    @BeforeAll
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        WebDriver = new ChromeDriver();
        WebDriver.manage().window().maximize();
    }
    @ParameterizedTest
    // rieng trang cua thay thi email chi dki dc 1 lan thoi ( thay dang dung trang khác với nhà trường)
    @CsvSource({
            // phải có a móc k là trang k đăng kí được đâu
            "Tống An Khang,tongankhang0409@gmail.com,Tongankhang2007"
    })
    public void registrationTest(String name,String email, String password){
        WebDriver.get("https://hoctotlamhay.vn/register");
        // sử dụng fn f12 rồi xem id của nó để lấy được chỗ findElement
        WebElement nameFiel = WebDriver.findElement(By.id("name"));
        WebElement emailFiel = WebDriver.findElement(By.id("user_email"));
        WebElement passwordFiel = WebDriver.findElement(By.id("password"));
        // tìm button có tên chứa nút "đăng ký"
        WebElement submitButton ;
        submitButton = By.xpath("//button[contains(text(),'Đăng ký')]").findElement(WebDriver);
        nameFiel.sendKeys(name);
        emailFiel.sendKeys(email);
        passwordFiel.sendKeys(password);
        submitButton.click();
//        WebDriverWait wait = new WebDriverWait(WebDriver,Duration.ofSeconds(6));
//        wait.until(ExpectedConditions.or(
//                ExpectedConditions.urlToBe("https://hoctotlamhay.vn/")
//        ));
//        //kiểm tra
//        String url = WebDriver.getCurrentUrl();
//        assertEquals("https://hoctotlamhay.vn/",url);
    }
    @ParameterizedTest
    @CsvSource({
            "Tongankhang0409@gmail.com,Tongankhang2007,Khang"
    })
    // sắp xếp đúng thứ tự, dựa trên trình duyệt web
    public void loginTest(String email, String password,String expectedName){
        // copy xuống sửa thành /login
        WebDriver.get("https://hoctotlamhay.vn/login");
        //tên thì k cần, chỉ cần email và password
        WebElement emailFiel = WebDriver.findElement(By.id("user_email"));
        WebElement passwordFiel = WebDriver.findElement(By.id("password"));
        // sửa nút contains thành đăng nhập
        WebElement submitButton = WebDriver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]"));
        emailFiel.sendKeys(email);
        passwordFiel.sendKeys(password);
        submitButton.click();
//        WebDriverWait wait = new WebDriverWait(WebDriver, Duration.ofSeconds(8));
//        wait.until(ExpectedConditions.urlContains("https://hoctotlamhay.vn/"));
//        WebElement namElement = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//*[contains(text,'"+ expectedName +"')]")
//        ));
//        assertTrue(namElement.isDisplayed(),"Không tìm thấy tên"+expectedName);
    }
    @AfterAll
    public static void tearDown(){
        WebDriver.quit();
    }
}