package scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Project3Page;
import utils.DropdownHandler;
import utils.Waiter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class _03_ProjectTest extends Base {

    @BeforeMethod
    public void setPage(){
        driver.get("https://techglobal-training.com/frontend/project-3");
        project3Page = new Project3Page();
    }

    @Test(priority = 1, description = "Test Case 01 - Validate the default Book your trip form")
    public void validateTheDefaultBookYourTripForm(){
        Assert.assertTrue(project3Page.oneWayRadioButton.isDisplayed());
        Assert.assertTrue(project3Page.oneWayRadioButton.isEnabled());
        Assert.assertTrue(project3Page.oneWayRadioButton.isSelected());

        Assert.assertTrue(project3Page.roundTripRadioButton.isDisplayed());
        Assert.assertTrue(project3Page.roundTripRadioButton.isEnabled());
        Assert.assertFalse(project3Page.roundTripRadioButton.isSelected());

        project3Page.tripLabels.forEach(tl -> Assert.assertTrue(tl.isDisplayed()));
        project3Page.dropdowns.forEach(dd -> Assert.assertTrue(dd.isDisplayed()));
        project3Page.datePickers.forEach(dp -> Assert.assertTrue(dp.isDisplayed()));
        Assert.assertFalse(project3Page.returnDatePicker.isEnabled());

        Assert.assertEquals(DropdownHandler.getDefaultOption(project3Page.numberOfPassengersDropdown), "1");
        Assert.assertEquals(DropdownHandler.getDefaultOption(project3Page.passengerOneDropdown), "Adult (16-64)");

        Assert.assertTrue(project3Page.bookButton.isDisplayed());
        Assert.assertTrue(project3Page.bookButton.isEnabled());
    }

    @Test(priority = 2, description = "Test Case 02 - Validate the Book your trip form when Round trip is selected")
    public void validateTheBookYourTripFormWhenRoundTripIsSelected(){
        project3Page.roundTripRadioButton.click();

        Assert.assertTrue(project3Page.roundTripRadioButton.isSelected());
        Assert.assertFalse(project3Page.oneWayRadioButton.isSelected());

        project3Page.tripLabels.forEach(tl -> Assert.assertTrue(tl.isDisplayed()));
        project3Page.dropdowns.forEach(dd -> Assert.assertTrue(dd.isDisplayed()));
        project3Page.datePickers.forEach(dp -> Assert.assertTrue(dp.isDisplayed()));

        Assert.assertEquals(DropdownHandler.getDefaultOption(project3Page.numberOfPassengersDropdown), "1");
        Assert.assertEquals(DropdownHandler.getDefaultOption(project3Page.passengerOneDropdown), "Adult (16-64)");

        Assert.assertTrue(project3Page.bookButton.isDisplayed());
        Assert.assertTrue(project3Page.bookButton.isEnabled());
    }

    @Test(priority = 3, description = "Test Case 03 - Validate the booking for 1 passenger and one way")
    public void validateTheBookingFor1PassengerAndOneWay() {

        String cabinType = "Business";
        String fullFromCity = "Illinois";
        String from = "IL";
        String fullToCity = "Florida";
        String to = "FL";
        String numberOfPassengers = "1";
        String passengerType = "Senior (65+)";

        DropdownHandler.selectByVisibleText(project3Page.cabinClassDropdown, cabinType);
        DropdownHandler.selectByVisibleText(project3Page.fromDropdown, fullFromCity);
        DropdownHandler.selectByVisibleText(project3Page.toDropdown, fullToCity);

        LocalDate nextWeek = LocalDate.now().plusWeeks(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String formattedDay = nextWeek.format(formatter);

        DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("E MMM dd yyyy");
        String fullDate = nextWeek.format(fullDateFormatter);


        project3Page.datePicker(formattedDay, "day", project3Page.departDatePicker);

        DropdownHandler.selectByVisibleText(project3Page.numberOfPassengersDropdown, numberOfPassengers);
        DropdownHandler.selectByVisibleText(project3Page.passengerOneDropdown, passengerType);

        project3Page.bookButton.click();

        String expectedLocAndDate = "DEPART\n" + from + " to " + to + "\n" + fullDate;
        String expectedPassengerInformation = "Number of Passengers: " + numberOfPassengers + "\nPassenger 1: " + passengerType + "\nCabin class: " + cabinType;
        Assert.assertEquals(project3Page.departInformation.getText(), expectedLocAndDate);
        Assert.assertEquals(project3Page.passengerInformation.getText(), expectedPassengerInformation);

        // Tue Jun 20 2023 format we need
        //Number of Passengers: 1
        //Passenger 1: Senior (65+)
        //Cabin class: Business
//        System.out.println(project3Page.departInformation.getText());
//        System.out.println(project3Page.passengerInformation.getText());
    }
}
