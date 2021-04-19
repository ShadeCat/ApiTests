package service;

import models.DeleteMessage;
import models.Pet;
import retrofit2.Call;
import retrofit2.http.*;

public interface PetStore {
    @POST("v2/pet")
    Call<Pet> addPet(@Body Pet pet);

    @PUT("v2/pet")
    Call<Pet> updatePet(@Body Pet pet);

    @GET("v2/pet/{id}")
    Call<Pet> getPetById(@Path("id") long id);

    @DELETE("v2/pet/{id}")
    Call<DeleteMessage> deletePetById(@Path("id") long id);
}
