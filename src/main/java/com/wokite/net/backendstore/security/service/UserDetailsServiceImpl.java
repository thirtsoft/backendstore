package com.wokite.net.backendstore.security.service;

import com.wokite.net.backendstore.models.Utilisateur;
import com.wokite.net.backendstore.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByUsername(username).get();
        if (user != null && user.isActive()) {
            return UserPrinciple.build(user);
        }else {
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + username);
        }
    }

}
