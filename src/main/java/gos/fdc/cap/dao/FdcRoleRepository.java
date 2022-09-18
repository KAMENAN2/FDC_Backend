package gos.fdc.cap.dao;

import gos.fdc.cap.entities.FdcRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface FdcRoleRepository extends JpaRepository<FdcRole,Long> {

    public FdcRole findByRoleName(String rolename);
}
