package api;

import dto.pet.Pet;
import dto.pet.PetStatus;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PetApi extends BaseApi{
    private static final String PET_PATH = "/pet";

    @Step("FindByStatus(multiple): {statuses}")
    public Response getPetsByStatus(PetStatus... statuses) {

        String statusValues = Stream.of(statuses)
                .map(PetStatus::name)
                .collect(Collectors.joining(","));

        return sendGetRequestWithQueryParams(
                PET_PATH + "/findByStatus",
                Map.of("status", statusValues)
        );
    }

    @Step("Get Pet by Id: {petId}")
    public Response getPetById(Long petId){
        return sendGetRequestWithPathParam(PET_PATH, petId);
    }

    @Step("Add a new pet to the store")
    public Response createPet(Pet pet){
        return sendPostRequest(PET_PATH,pet);
    }

    @Step("Update an existing pet")
    public Response updatePet(Pet pet){
        return sendPutRequest(PET_PATH,pet);
    }

    @Step("Delete pet: {petId}")
    public Response deletePet(Long petId){
        return sendDeleteRequest(PET_PATH, petId);
    }
}
