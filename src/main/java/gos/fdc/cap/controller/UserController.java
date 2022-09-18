package gos.fdc.cap.controller;


import gos.fdc.cap.dao.FdcUserRepository;
import gos.fdc.cap.dto.UserForm;
import gos.fdc.cap.entities.FdcUser;
import gos.fdc.cap.services.AcountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private AcountService accountService;

    @Autowired
    private FdcUserRepository fdcUserRepository;



    @RequestMapping("/register")
    public FdcUser register(@RequestBody UserForm userForm){
        System.out.println(userForm.getUsername());
        System.out.println(userForm.getTel());
        return  accountService.saveFdcUser(
                userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword(),userForm.getEmail());
    }

    @RequestMapping("/fdcUsers")
    private List<FdcUser> getAllUser(){
        return fdcUserRepository.findAll();
    }


}


