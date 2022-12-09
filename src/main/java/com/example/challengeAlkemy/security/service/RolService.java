
package com.example.challengeAlkemy.security.service;

import com.example.challengeAlkemy.security.entity.Rol;
import com.example.challengeAlkemy.security.enums.RolName;
import com.example.challengeAlkemy.security.repository.RolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    
    @Autowired
    RolRepository rolRepository;
    
    public Optional<Rol> getByRolName(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }
    
            
            
            
            
}
