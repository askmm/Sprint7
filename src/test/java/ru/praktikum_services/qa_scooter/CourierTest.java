package ru.praktikum_services.qa_scooter;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import ru.praktikum_services.qa_scooter.courier.*;

public class CourierTest {
    protected final CourierGenerator generator = new CourierGenerator();
    protected final CourierClient client = new CourierClient();
    protected final CourierAssertions check = new CourierAssertions();
    protected int courierId;
    protected Courier courier;

    @After
    public void deleteCourier() {
        if (courierId > 0) {
            client.deleteCourier(courierId);
        }
    }

    protected int courierId(){
        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.logIn(creds);
        return check.loggedInSuccessfully(loginResponse);
    }
}
