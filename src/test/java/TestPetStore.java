import models.Category;
import models.DeleteMessage;
import models.Pet;
import models.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.PetStore;
import service.PetStoreService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;

public class TestPetStore {
    private PetStore petStore;
    private static final long nonExistencePetId = 0;

    @BeforeEach
    public void petStoreInit(){
        petStore = new PetStoreService().getPetSore();
    }

    public void assertNonExistPet() throws IOException{
        int responseCode = petStore.getPetById(nonExistencePetId).execute().code();
        Assertions.assertEquals(404, responseCode);
    }

    private static Pet getFullFillPet(){
        Pet fullFillPet = new Pet();
        Category category = new Category();
        category.setId(12);
        category.setName("new category");
        Tag tag = new Tag();
        tag.setId(55);
        tag.setName("new tag");
        Tag tag2 = new Tag();
        tag2.setId(33);
        tag2.setName("new tag 3");
        Tag[] tags = {tag, tag2};
        fullFillPet.setName("CreatedPet");
        fullFillPet.setPhotoUrls(new String[] {"one", "two"});
        fullFillPet.setCategory(category);
        fullFillPet.setTags(tags);
        fullFillPet.setStatus("new status");
        return fullFillPet;
    }

    public static Pet[] validPet() {
        Pet fullFillPet = getFullFillPet();
        Pet titleOnlyPet = new Pet();
        fullFillPet.setId(2000);
        titleOnlyPet.setId(2001);
        titleOnlyPet.setName("Name only pet");
        return new Pet[] {fullFillPet, titleOnlyPet};
    }


    @ParameterizedTest
    @MethodSource("validPet")
    public void testCreatePetFullFill(Pet validPet) throws IOException {
        long petId = validPet.getId();
        Pet createdPet = petStore.addPet(validPet).execute().body();
        Assertions.assertEquals(createdPet, validPet);
        Pet petFromGet = petStore.getPetById(petId).execute().body();
        Assertions.assertEquals(validPet, petFromGet);
    }

    @Test
    public void testTryCreateUnnamedPet() throws IOException{
        Pet unnamedPet = getFullFillPet();
        unnamedPet.setId(2003);
        unnamedPet.setName(null);
        int responseCode = petStore.addPet(unnamedPet).execute().code();
        Assertions.assertEquals(405, responseCode);
    }

    @Test
    public void testDeletePet() throws IOException{
        Pet testPet = getFullFillPet();
        Pet createdPet = petStore.addPet(testPet).execute().body();
        Assertions.assertNotNull(createdPet);
        DeleteMessage actualResponse = petStore.deletePetById(createdPet.getId()).execute().body();
        DeleteMessage expectedResponse = new DeleteMessage();
        expectedResponse.setMessage(String.valueOf(createdPet.getId()));
        expectedResponse.setType("unknown");
        expectedResponse.setCode("200");
        Assertions.assertEquals(expectedResponse, actualResponse);
        int responseCode = petStore.getPetById(createdPet.getId()).execute().code();
        Assertions.assertEquals(404, responseCode);
    }

    @Test
    public void testDeleteNonExistPet() throws IOException{
        assertNonExistPet();
        int responseCode = petStore.deletePetById(nonExistencePetId).execute().code();
        Assertions.assertEquals(404, responseCode);
    }

    @Test
    public void testUpdateNonExistPet() throws IOException{
        assertNonExistPet();
        Pet updatePet = getFullFillPet();
        updatePet.setId(nonExistencePetId);
        int responseCode = petStore.updatePet(updatePet).execute().code();
        Assertions.assertEquals(405, responseCode);
    }

    @Test
    public void testUpdateOnlyName() throws IOException{
        Pet originPet = getFullFillPet();
        long petId = 2005;
        originPet.setId(petId);
        String newName = "New name";
        petStore.addPet(originPet).execute();
        Pet updateData = new Pet();
        updateData.setId(petId);
        updateData.setName(newName);
        petStore.updatePet(updateData).execute();
        originPet.setName(newName);
        Pet editedPet = petStore.getPetById(petId).execute().body();
        Assertions.assertEquals(originPet, editedPet);
    }

}
