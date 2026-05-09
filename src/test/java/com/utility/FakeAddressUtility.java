package com.utility;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

import java.util.Locale;

public class FakeAddressUtility {

    public static void main(String[] args){
        getFakeAddress();
    }

    public static AddressPOJO getFakeAddress(){

        Faker faker = new Faker(Locale.US);
        AddressPOJO addressPojo =
                new AddressPOJO(faker.address().city(),faker.company().name(),faker.address().buildingNumber(),
                        faker.address().streetAddress(),faker.numerify("#####"),faker.phoneNumber().cellPhone(),faker.phoneNumber().cellPhone(),"other",
                "Home address",faker.address().state());
        return addressPojo;
    }
}
