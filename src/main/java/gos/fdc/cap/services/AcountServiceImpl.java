package gos.fdc.cap.services;

import gos.fdc.cap.dao.FdcRoleRepository;
import gos.fdc.cap.dao.FdcUserRepository;
import gos.fdc.cap.entities.FdcRole;
import gos.fdc.cap.entities.FdcUser;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AcountServiceImpl implements AcountService {
    private FdcUserRepository fdcUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private FdcRoleRepository fdcRoleRepository;




    protected AcountServiceImpl(FdcUserRepository fdcUserRepository, FdcRoleRepository fdcRoleRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.fdcUserRepository = fdcUserRepository;
        this.fdcRoleRepository = fdcRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public FdcUser saveFdcUser(String username, String password, String confirmedPassword, String email) {
        FdcUser user  ;
        user = fdcUserRepository.findFdcUserByUsername(username);
        if(user != null) throw new RuntimeException("User alraedy exists");
        if(!password.contentEquals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        FdcUser fdcUser  = new FdcUser();
        fdcUser.setUsername(username);
        fdcUser.setPassword(bCryptPasswordEncoder.encode(password));
        fdcUser.setEmail(email);
        fdcUser.setActived(true);
        fdcUserRepository.save(fdcUser);
        System.out.println(username);
        addFdcRole(username,"USER");


        return fdcUser;
    }

    @Override
    public FdcUser loadUserByUsername(String username) {
        System.out.println("loadUserByUsername methode");

        return fdcUserRepository.findFdcUserByUsername(username);
    }

    @Override
    public FdcRole saveFdcRole(FdcRole fdcRole) {
        return fdcRoleRepository.save(fdcRole);
    }

    @Override
    public void addFdcRole(String username, String roleName) {

        FdcUser fdcUser = fdcUserRepository.findFdcUserByUsername(username);
        FdcRole fdcRole = fdcRoleRepository.findByRoleName(roleName);

        System.out.println("FDC Role :"+fdcRole.toString());
        fdcUser.getFdcRoleUsers().add(fdcRole);
       // System.out.println("FDC User Role :"+fdcUser.getFdcRoleUsers());
        System.out.println("FDC User avant :"+fdcUser.toString());
        fdcUserRepository.save(fdcUser);
        System.out.println("FDC User Après :"+fdcUserRepository.findFdcUserByUsername(username).toString());


    }
}
