package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    // the run method will run at startup
    @Override
    public void run(String... args) throws Exception {
        PetType dog = makePetType("dog");
        PetType cat = makePetType("cat");

        Owner owner1 = new Owner();
        owner1.setFirstName("joe");
        owner1.setLastName("blow");
        owner1.setAddress("123 Foo St");
        owner1.setCity("Miami");
        owner1.setTelephone("818-123-3456");

        owner1 = ownerService.save(owner1);
        Pet pet1 = makePet("stan", LocalDate.of(2015,1,20), dog, owner1);


        Owner owner2 = new Owner();
        owner2.setFirstName("shelly");
        owner2.setLastName("duval");

        owner2 = ownerService.save(owner2);
        Pet pet2 = makePet("stupid cat", LocalDate.now(), cat, owner2);

        System.out.println("loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("hermione");
        vet1.setLastName("granger");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("ginny");
        vet2.setLastName("weasley");

        vetService.save(vet2);

        System.out.println("loaded vets");

    }

    private Pet makePet(String name, LocalDate date, PetType type, Owner owner) {
        Pet pet = new Pet();
        pet.setBirthDate(date);
        pet.setPetType(type);
        pet.setName(name);
        pet.setOwner(owner);
        owner.getPets().add(pet);

        return petService.save(pet);

    }

    private PetType makePetType(String type) {
        PetType t = new PetType();
        t.setName(type);
        return petTypeService.save(t);
    }
}
