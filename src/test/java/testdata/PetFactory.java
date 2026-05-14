package testdata;

import dto.pet.Category;
import dto.pet.Pet;
import dto.pet.PetStatus;
import dto.pet.Tag;

import java.util.List;

public class PetFactory {

        public static Pet defaultPet() {
            return Pet.builder()
                    .id(System.currentTimeMillis())
                    .name("Baton")
                    .statuses(PetStatus.available)
                    .category(Category.builder()
                            .id(1L)
                            .name("doggie")
                            .build())
                    .photoUrls(List.of())
                    .tags(List.of(
                            Tag.builder()
                                    .id(1L)
                                    .name("retriever")
                                    .build()))
                    .build();
        }
}
