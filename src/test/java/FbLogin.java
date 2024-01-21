import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class FbLogin {
    public static WebDriver webDriver;

    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }



    @Test(priority = 1)
    public void loginPage() throws InterruptedException, IOException, AWTException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.facebook.com/");

        String str = webDriver.getCurrentUrl();
        System.out.println(str);

        String src = webDriver.getPageSource();
        System.out.println(src);

        String tit = webDriver.getTitle();
        System.out.println(tit);

        webDriver.manage().window().maximize();
        Thread.sleep(1000);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 2)
    public void loginFb() throws InterruptedException {
            webDriver.findElement(By.name("email")).sendKeys("akkikarn20@gmail.com");
            webDriver.findElement(By.name("pass")).sendKeys("Password@123");
            webDriver.findElement(By.name("login")).click();
            Thread.sleep(3000);
        }

        @Test(priority = 3)
        public void screenshot() throws IOException, InterruptedException {
            TakesScreenshot ss = (TakesScreenshot) webDriver;
            File StFile = ss.getScreenshotAs(OutputType.FILE);
            File DsFile = new File("C:\\Users\\DELL\\IntelliJIdeaProjects\\FacebookLogin\\Screenshot\\ss1.png");
            FileHandler.copy(StFile, DsFile);
            Thread.sleep(3500);
        }

        @Test(priority = 4)
        public void post() throws InterruptedException, IOException {
            webDriver.findElement(By.xpath("//span[text()='Photo/video']")).click();
            Thread.sleep(3500);

            webDriver.findElement(By.xpath("//p[@class='xdj266r x11i5rnm xat24cr x1mh8g0r x16tdsg8']")).sendKeys("Har Har Mahadev...");

            webDriver.findElement(By.xpath("//div[@class='x14yjl9h xudhj91 x18nykt9 xww2gxu x6s0dn4 x972fbf xcfux6l x1qhh985 xm0m39n x9f619 x3nfvp2 xl56j7k x1n2onr6 x1qhmfi1 x1vqgdyp x100vrsf']")).click();

            Runtime.getRuntime().exec("C:\\Users\\DELL\\Desktop\\pic\\fbpic2.exe");

            webDriver.findElement(By.xpath("//span[text()='Post']")).click();
        }

    @Test(priority = 5)
    public void searchAllFriendAndLikeOnePost() throws InterruptedException {
        webDriver.findElement(By.xpath("//a[@aria-label='Friends']")).click();
        Thread.sleep(2500);
        webDriver.findElement(By.xpath("//span[text()='All friends']")).click();
        Thread.sleep(7000);
        List<WebElement> friendList = webDriver.findElements(By.xpath("//div[@class='x1xmf6yo']//div[@data-visualcompletion='ignore-dynamic']"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

        for (int i = 1; i <= friendList.size(); i++) {
            webDriver.findElement(By.xpath("//div[@class='x1xmf6yo']//div[@data-visualcompletion='ignore-dynamic'][" + i + "]")).click();
            Thread.sleep(4000);
            javascriptExecutor.executeScript("window.scrollBy(0,1000)");
            Thread.sleep(2000);

            WebElement likePost = webDriver.findElement(By.xpath("//div[@class='xq8finb x16n37ib']//div[@class='x9f619 x1n2onr6 x1ja2u2z x78zum5 xdt5ytf x2lah0s x193iq5w xeuugli x150jy0e x1e558r4 x10b6aqq x1yrsyyn']//span[text()='Like'][1]"));
            likePost.click();
            Thread.sleep(1000);
        }
    }

        @Test(priority = 6)
        public void searchParticularFriend() throws InterruptedException {
            webDriver.findElement(By.xpath("//label[@class='x1a2a7pz x1qjc9v5 xnwf7zb x40j3uw x1s7lred x15gyhx8 x9f619 x78zum5 x1fns5xo x1n2onr6 xh8yej3 x1ba4aug xmjcpbm']")).sendKeys("Rakesh Kumar");
            Thread.sleep(3000);
            webDriver.findElement(By.xpath("//span[@class='x193iq5w xeuugli x13faqbe x1vvkbs x1xmvt09 x6prxxf xvq8zen xo1l8bm xzsf02u']")).click();
            Thread.sleep(3000);
            robot.mouseWheel(20);
            Thread.sleep(5000);
        }

        @Test(priority = 7)
        public void homeTab() throws InterruptedException {
            webDriver.findElement(By.xpath("//a[@aria-label='Home']")).click();
            Thread.sleep(3000);
        }

        @Test(priority = 8)
        public void countLikes() throws InterruptedException {
            webDriver.findElement(By.xpath("//span[text()='Akarsh Kumar']")).click();
            Thread.sleep(3500);
            robot.mouseWheel(40);
            Thread.sleep(3500);

            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

            WebElement ele = webDriver.findElement(By.xpath("//span[text()='You']"));
            javascriptExecutor.executeScript("arguments[0].click();", ele);
            Thread.sleep(4000);

            List<WebElement> likeCount = webDriver.findElements(By.xpath("//div[@class='x78zum5 xdt5ytf x1iyjqo2 x1n2onr6']/div[@data-visualcompletion='ignore-dynamic']"));
            System.out.println("No of like : " + likeCount.size());
            Thread.sleep(5500);

            webDriver.findElement(By.xpath("//div[@class='x1d52u69 xktsk01']")).click();
            Thread.sleep(5000);
        }



        @Test(priority = 9)
        public void logout() throws InterruptedException {

        webDriver.findElement(By.xpath("//div[@class='x78zum5 x1n2onr6']")).click();
        Thread.sleep(5000);

        webDriver.findElement(By.xpath("//span[text()='Log Out']")).click();
        Thread.sleep(3500);

    }

    @Test(priority = 10)
    public void terminateBrowser(){
        webDriver.close();
    }
    }

