package positivetest;

import dto.pet.Category;
import dto.pet.Pet;
import dto.pet.PetStatus;
import dto.pet.Tag;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testdata.PetFactory;

import java.util.List;

public class PetTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void addNewPetTest() {

        Pet pet = PetFactory.defaultPet();
        Response response = petApi.createPet(pet);

        response.then()
                .statusCode(200);

        Pet createdPet = response.as(Pet.class);

        Assert.assertEquals(createdPet.getName(), pet.getName());
        Assert.assertEquals(createdPet.getStatuses(), pet.getStatuses());
        Assert.assertEquals(createdPet.getCategory().getName(), pet.getCategory().getName());
    }

    @Test(groups = {"regression"})
    public void findByStatusTest() {

        Response response = petApi.getPetsByStatus(PetStatus.sold);
        response.then()
                .statusCode(200);

        List<Pet> pets = response.jsonPath().getList("", Pet.class);

        Assert.assertNotNull(pets);
        Assert.assertFalse(pets.isEmpty());

        for (Pet pet : pets) {
            Assert.assertEquals(pet.getStatuses(), PetStatus.sold);
        }
    }

    @Test(groups = {"smoke", "regression"})
    public void getPetByIdTest() {

        Pet pet = PetFactory.defaultPet();
        Long petId = petApi.createPet(pet)
                .jsonPath()
                .getLong("id");
        Response response = petApi.getPetById(petId);

        response.then()
                .statusCode(200);

        Pet result = response.as(Pet.class);

        Assert.assertEquals(result.getId(), petId);
        Assert.assertEquals(result.getName(), pet.getName());
        Assert.assertEquals(result.getStatuses(), pet.getStatuses());
    }

    @Test(groups = {"regression"})
    public void updateExistingPetTest(){

        // Arrange
        Pet pet = PetFactory.defaultPet();
        Long petId = petApi.createPet(pet)
                .jsonPath()
                .getLong("id");

        Pet updatedPet = Pet.builder()
                .id(petId)
                .name("UpdatedBaton")
                .statuses(PetStatus.available)
                .category(Category.builder().id(1L).name("Testing").build())
                .photoUrls(List.of())
                .tags(List.of(Tag.builder().id(1L).name("retriever").build()))
                .build();

        // Act
        Response response = petApi.updatePet(updatedPet);

        // Assert
        response.then()
                .statusCode(200);

        Pet result = response.as(Pet.class);

        Assert.assertEquals(result.getName(), "UpdatedBaton");
    }

    @Test(groups = {"regression"})
    public void deletePetTest() {

        Pet pet = PetFactory.defaultPet();

        Long petId = petApi.createPet(pet)
                .jsonPath()
                .getLong("id");

        petApi.deletePet(petId)
                .then()
                .statusCode(200);

        petApi.getPetById(petId)
                .then()
                .statusCode(404);
    }

}
