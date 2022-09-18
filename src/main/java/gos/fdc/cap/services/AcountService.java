package gos.fdc.cap.services;

import gos.fdc.cap.entities.FdcRole;
import gos.fdc.cap.entities.FdcUser;

public interface AcountService {

public FdcUser saveFdcUser (String username, String password, String confirmedPassword,String email);
public FdcUser loadUserByUsername (String username);
public FdcRole saveFdcRole (FdcRole fdcRole);
public void addFdcRole(String username, String roleName);

}
