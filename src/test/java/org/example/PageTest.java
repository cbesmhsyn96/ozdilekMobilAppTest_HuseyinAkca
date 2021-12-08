package org.example;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.http.util.Asserts;
import org.example.basetest.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PageTest extends BaseTest {
    @Step("<wait> saniye bekle.")
    public void waitForLogo(int wait) throws InterruptedException {
        Thread.sleep(wait*1000);
        log.info("10 saniye beklendi.");
    }
    @Step("<logoApp> id li ozdilek app logosu gorundu.")
    public void appOpenControll(String logoApp){
        Asserts.check(appiumDriver.findElement(By.id(logoApp)).isDisplayed(),"logo");
        //if (appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/iv_storeTopLogo")).isDisplayed())System.out.println("Uygulama logosu göründü.");
        log.info("Uygulama logosu gorundu.");
    }
    @Step("<startSell> id li Alisverise Basla butonuna tiklandi.")
    public void clickToSellButton(String startSell) throws InterruptedException {
        appiumDriver.findElement(By.id(startSell)).click();
        log.info("Alışverişe Başla butonuna tıklandı.");
    }
    @Step("Butona basildiktan sonra <xpatMagazaButton> butonunun geldigi dogrulandi.")
    public void startSell(String xpatMagazaButton) throws InterruptedException {
        Asserts.check(appiumDriver.findElement(By.xpath(xpatMagazaButton)).isDisplayed(),"Button görüldü.");
        log.info("Alisverise Basla butonuna tiklandiginda acilan sayfa dogrulandi.");
    }
    @Step("<catogoryId> id li Kategoriler butonuna tiklandi.")
    public void clickToCategories(String catogoryId) throws InterruptedException {
        appiumDriver.findElement(By.id(catogoryId)).click();
        log.info("Kategoriler butonuna tiklandi.");
    }
    @Step("<actionBarRootCaregories>  id li element görüntülendi.")
    public void dogrulaKategoriSayfa(String actionBarRootCaregories){
        Asserts.check(appiumDriver.findElement(By.id(actionBarRootCaregories)).isDisplayed(),"Kategori listesi ayrıntıları görüntülendi.");
        log.info("Kategoriler sayfasının açıldığı doğrulandı.");
    }
    @Step("<contaionerItemsId> id li listenin <indis> inci elemani olan Kadin a tiklandi.")
        public void clickToKadinLink(String contaionerItemsId,int indis) throws InterruptedException {
        List<MobileElement> containerItems = appiumDriver.findElements(By.id(contaionerItemsId));
        containerItems.get(indis).click();
        log.info("Kadin sekmesine tiklandi.");
        //log.warn("");
    }
    @Step("<kadinLinklerId> id li Kadin kategorisindeki linklerin <indisPantolon> indisli Pantolon linkine tiklandi.")
    public void clickToPantolonLink(String kadinLinklerId,int indisPantolon) throws InterruptedException {
        List<MobileElement> kadinLinkler = appiumDriver.findElements(By.id(kadinLinklerId));
        kadinLinkler.get(indisPantolon).click();
        log.info("Pantolon kategorisine tıklandı.");
    }
    @Step("Sayfada scroll işlemi yapıldı.")
    public void scrollOnPage() throws InterruptedException {
        Dimension dimension = appiumDriver.manage().window().getSize();
        int scrollStart = 1849;
        int scrollEnd = 514;
        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point(0,scrollStart)).moveTo(PointOption.point(0,scrollEnd)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).release().perform();
        List<MobileElement> mobileElementList = appiumDriver.findElements(By.className("//android.view.ViewGroup"));
        for (MobileElement mobileElement: mobileElementList){
            if (mobileElement == appiumDriver.findElement(By.xpath("//android.view.ViewGroup[@element-id='2b2e4bfb-e404-4a29-a636-82cd1e9f75e6']"))){
                mobileElement.click();//xpat için parametre ver
            }
        }
        log.info("Sayfa scroll edildi.");
    }
    @Step("Rastgele 1 ürün secildi.")
    public void randomProductClick() throws InterruptedException {
        Actions action = new Actions(appiumDriver);
        List<MobileElement> mobileElementList = appiumDriver.findElements(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.ImageView"));
        Random rand = new Random();
        int gecici,index=0;
        while(rand.nextInt(mobileElementList.size())<0)
        {
            rand.nextInt(mobileElementList.size());
            index++;
        }
        action.moveToElement(mobileElementList.get(rand.nextInt(mobileElementList.size()))).click().perform();
    }
    @Step("Urun detay sayfasinin acildigi <bedenTablosuId> id li elementinin varligi ile dogrulandi.")
    public void productDetailsControll(String bedenTabloId){
        MobileElement bedenTablosuButton = appiumDriver.findElement(By.id(bedenTabloId));
        Asserts.check(bedenTablosuButton.isDisplayed(),"Beden tablosu butonu goruntulendi.");
    }
    @Step("<favButtonId> id li favoriler butonuna tiklandi.")
    public void favoritesButtonClick(String favButtonId) throws InterruptedException {
        if(appiumDriver.findElement(By.id(favButtonId)).isDisplayed()==false){
            Dimension dimension = appiumDriver.manage().window().getSize();
            int start_x = (int)(dimension.width*0.5);
            int start_y = (int)(dimension.height*0.7);
            int end_x = (int)(dimension.width*0.5);
            int end_y = (int)(dimension.height*0.3);
            TouchAction touch = new TouchAction(appiumDriver);
            touch.press(PointOption.point(start_x,start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(end_x,end_y)).perform();
        }
        MobileElement favButton = appiumDriver.findElement(By.id(favButtonId));
        favButton.click();
    }
    @Step("<logInButton> resource-id li buttonun varligi kontrol edilerek giris sayfasinin acildigi dogrulandi.")
    public void isVisibleLoginButton(String logInButton){
       MobileElement loginButton = appiumDriver.findElementById(logInButton);
       Asserts.check(loginButton.isDisplayed(),"Login butonu var.");
    }
    @Step("<emailId> id li eposta ve <passwordId> id li parola girildi.")
    public void sendKeysValues(String emailId,String passwordId){
        appiumDriver.findElement(By.id(emailId)).sendKeys("text");
        appiumDriver.findElement(By.id(passwordId)).sendKeys("şifre");
    }
}