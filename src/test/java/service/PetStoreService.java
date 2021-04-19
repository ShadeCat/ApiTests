package service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PetStoreService {
    private PetStore petSore;

    public PetStore getPetSore() {
        if(petSore == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://petstore.swagger.io/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            petSore = retrofit.create(PetStore.class);
        }
        return petSore;
    }
}
