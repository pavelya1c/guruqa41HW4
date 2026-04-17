import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;


public class TestBoxHW4 extends TestBase {


    @Test
    public void successfulFormSubmissionWithAllFieldsTestHardForm() {
        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#userNumber").setValue(userPhone);
        $("#genterWrapper").$(byText(userGender)).click();

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(mounthOfBirth);
        $(".react-datepicker__month").$(byText(dayOfBirth)).doubleClick();

        $(".subjects-auto-complete__input").setValue(sendKey)
                .pressEnter();

        $("#hobbiesWrapper").$(byText(userHobbies)).click();


        $("#uploadPicture").uploadFromClasspath(userPicture);

        $("#currentAddress").setValue(userCurrentAdress);
        $(".col-md-4.col-sm-12").click();
        $(byText(userState)).click();
        $("#city").click();
        $(byText(userCity)).click();

        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(userGender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userPhone));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(userHobbies));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(dayOfBirth + " " + mounthOfBirth  + "," + yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(userSubjects));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(userPicture));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(userCurrentAdress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(userState + " " + userCity));


    }

    @Test
    void successfulFormSubmissionWithAllFieldsTestEasyForm(){

        open("/text-box");

        $("#userName").setValue(firstName + " " + lastName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(userCurrentAdress);
        $("#permanentAddress").setValue(userPermanentAdress);
        $("#submit").click();

        $("#output").shouldHave(Condition.text(firstName + " " + lastName));
        $("#output").shouldHave(Condition.text(userEmail));
        $("#output").shouldHave(Condition.text(userCurrentAdress));
        $("#output").shouldHave(Condition.text(userPermanentAdress));



    }

    @Test
    public void successfulFormSubmissionWithRequiredFieldsTestHardForm() {
        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#userNumber").setValue(userPhone);
        $("#genterWrapper").$(byText(userGender)).click();
        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(userGender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userPhone));
    }

    @Test
    public void successfulFormSubmissionWithNoTAllFieldsTestEasyForm() {
        open("/text-box");
        $("#userName").setValue(firstName + " " + lastName);
        $("#userEmail").setValue(userEmail);

        $("#submit").click();

        $("#output").shouldHave(Condition.text(firstName + " " + lastName));
        $("#output").shouldHave(Condition.text(userEmail));


    }

    //НЕГАТИВНЫЕ ТЕСТЫ

    @Test
    public void shouldShowValidationErrorsWhenAllRequiredFieldsAreEmptyTestHardForm() {
        open("/automation-practice-form");
        $("#genterWrapper").$(byText(userGender)).click();
        $("#submit").click();
        $("#userForm").shouldHave(cssClass("was-validated"));
    }

    @Test
    public void shouldShowValidationErrorsWhenFirstNameAreEmptyTestHardForm() {
        open("/automation-practice-form");
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#userNumber").setValue(userPhone);
        $(".form-check #gender-radio-1").click();
        $("#submit").click();
        $("#userForm").shouldHave(cssClass("was-validated"));
    }

    @Test
    public void shouldNotDisplayResultTableWhenFormSubmissionIsInvalidTestHardForm() {
        open("/automation-practice-form");
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#userNumber").setValue(userPhone);
        $("#genterWrapper").$(byText(userGender)).click();
        $("#submit").click();
        $("#userForm").shouldNotHave(cssClass("table-dark"));
    }

    @Test
    public void shouldShowValidationErrorWhenInvalidEmailIsEnteredTestEasyForm() {
        open("/text-box");
        $("#userEmail").setValue(userEmailNotValid);
        $("#submit").click();
        $("#userEmail").shouldHave(cssClass("field-error"))
                .shouldHave(cssClass("form-control"));

    }


}
