package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner{
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      PetService petService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    // the run method will run at startup
    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData () {
        Specialty radiology = makeSpecialty("radiology");
        Specialty surgery = makeSpecialty("surgery");
        Specialty dentistry= makeSpecialty("dentistry");

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

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setPet(pet2);
        visit.setDescription("sick cat");
        visitService.save(visit);

        System.out.println("loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("hermione");
        vet1.setLastName("granger");
        vet1.getSpecialties().add(radiology);
        vet1.getSpecialties().add(dentistry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("ginny");
        vet2.setLastName("weasley");
        vet2.getSpecialties().add(dentistry);
        vet2.getSpecialties().add(surgery);

        vetService.save(vet2);

        System.out.println("loaded vets");

    }

    private Specialty makeSpecialty(String d) {
        Specialty s = new Specialty();
        s.setDescription(d);

        return specialtyService.save(s);
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
