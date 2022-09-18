package gos.fdc.cap.dao;

import gos.fdc.cap.entities.FdcUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface FdcUserRepository extends JpaRepository<FdcUser,Long> {

    public FdcUser findFdcUserByUsername ( String username);
}
