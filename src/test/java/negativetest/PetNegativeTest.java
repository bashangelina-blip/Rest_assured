package negativetest;

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

public class PetNegativeTest extends BaseTest{

    @Test
    public void findByStatusTest() {

        Response response = petApi.getPetsByStatus(PetStatus.biba);
        response.then()
                .statusCode(200);
    }

    @Test
    public void getPetByIdTest() {

        // Act
        Response response = petApi.getPetById(40L);
        // Assert (status)
        response.then()
                .statusCode(404);

    }

    @Test
    public void updateExistingPetTest(){

        // Arrange
        Pet pet = PetFactory.defaultPet();
        Long petId = petApi.createPet(pet)
                .jsonPath()
                .getLong("id");

        Pet updatedPet = Pet.builder()
                .id(petId)
                .build();

        // Act
        Response response = petApi.updatePet(updatedPet);

        // Assert
        response.then()
                .statusCode(200);


           }
}
