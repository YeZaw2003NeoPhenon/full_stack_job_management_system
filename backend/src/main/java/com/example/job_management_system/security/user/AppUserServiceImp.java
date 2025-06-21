package com.example.job_management_system.security.user;

import com.example.job_management_system.config.UserMapper;
import com.example.job_management_system.mapper.DataMapper;
import com.example.job_management_system.mapper.EntityConverter;
import com.example.job_management_system.security.user.dto.UserRecord;
import com.example.job_management_system.security.user.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImp implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    private final EntityConverter<AppUser, UserRequest> entityConverter;

    private final DataMapper<AppUser , UserRecord> userMapper;

    @Autowired
    public AppUserServiceImp(AppUserRepository appUserRepository, EntityConverter<AppUser, UserRequest> entityConverter, DataMapper<AppUser, UserRecord> userMapper) {
        this.appUserRepository = appUserRepository;
        this.entityConverter = entityConverter;
        this.userMapper = userMapper;
    }

    @Override
    @Cacheable(value = "allUsers", key = "#email")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      AppUser appUser = appUserRepository.findByEmail(email)
                                         .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
      return new AppUserDetail(appUser);
    }

    @CacheEvict(value = "allUsers", key = "#userRequest.email", allEntries = true)
    public UserRequest createUser(UserRequest userRequest){
        AppUser appUser = entityConverter.dtoToEntity(userRequest,AppUser.class);

        appUser.setPassword(new BCryptPasswordEncoder().encode(appUser.getPassword()));

        AppUser savedUser = appUserRepository.save(appUser);

        return entityConverter.entityToDto(savedUser,UserRequest.class);
    }

    @Cacheable(value = "allUsers")
    public List<UserRecord> getAllUsers(){
        return appUserRepository.findAll()
                .stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

}
