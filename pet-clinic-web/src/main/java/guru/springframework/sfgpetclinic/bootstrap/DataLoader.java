package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{
    private OwnerService ownerService;
    private VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    // the run method will run at startup
    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("joe");
        owner1.setLastName("blow");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("shelly");
        owner2.setLastName("duval");

        ownerService.save(owner2);

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
}
