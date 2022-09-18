package gos.fdc.cap.services;

import gos.fdc.cap.entities.FdcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AcountService acountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FdcUser fdcUser=acountService.loadUserByUsername(username);
        if(fdcUser==null) throw new UsernameNotFoundException("invalid user");
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        fdcUser.getFdcRoleUsers().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(fdcUser.getUsername(),fdcUser.getPassword(),authorities);
    }
}
