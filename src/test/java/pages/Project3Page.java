package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class Project3Page {

    public Project3Page(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".radio:nth-child(1) input")
    public WebElement oneWayRadioButton;

    @FindBy(css = ".radio:nth-child(2) input")
    public WebElement roundTripRadioButton;

    @FindBy(css = ".field > label")
    public List<WebElement> tripLabels;

    @FindBy(css = "div > select")
    public List<WebElement> dropdowns;

    @FindBy(css = ".field:nth-child(2) select")
    public WebElement cabinClassDropdown;

    @FindBy(css = ".field:nth-child(3) select")
    public WebElement fromDropdown;

    @FindBy(css = ".field:nth-child(4) select")
    public WebElement toDropdown;

    @FindBy(css = ".field:nth-child(7) select")
    public WebElement numberOfPassengersDropdown;

    @FindBy(css = ".field:nth-child(8) select")
    public WebElement passengerOneDropdown;

    @FindBy(css = "div[class*='Projects_date']")
    public List<WebElement> datePickers;

    @FindBy(css = ".field:nth-child(5)")
    public WebElement departDatePicker;

    @FindBy(css = ".field:nth-child(6) input")
    public WebElement returnDatePicker;

    @FindBy(css = "button[type='submit']")
    public WebElement bookButton;

    @FindBy(css = ".ml-3 > div > div")
    public WebElement departInformation;

    @FindBy(css = ".mt-4")
    public WebElement passengerInformation;


    public void datePicker(String date, String type, WebElement datePicker){
        switch (type){
            case "day":
                datePicker.findElement(By.name("day")).sendKeys(date);
                break;
            case "month":
                datePicker.findElement(By.name("month")).sendKeys(date);
                break;
            case "year":
                datePicker.findElement(By.name("year")).sendKeys(date);
                break;
            default:
        }
    }
}
