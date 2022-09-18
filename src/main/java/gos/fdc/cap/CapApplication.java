package gos.fdc.cap;

import gos.fdc.cap.entities.FdcRole;
import gos.fdc.cap.services.AcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.stream.Stream;

@SpringBootApplication
public class CapApplication implements CommandLineRunner {

    @Autowired
    private AcountService accountService;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(CapApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void run(String... args) throws Exception {


        accountService.saveFdcRole(new FdcRole(null,"USER"));
        accountService.saveFdcRole(new FdcRole(null,"ADMIN"));
        Stream.of("user1","user2","user3","admin").forEach(un->{
            accountService.saveFdcUser(un,"1234","1234","48854916");
        });


        accountService.saveFdcUser("agent","1234","1234","0707849611");
        accountService.addFdcRole("admin","ADMIN");
        accountService.addFdcRole("admin","USER");
    }
}
