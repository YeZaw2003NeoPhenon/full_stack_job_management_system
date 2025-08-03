package com.example.job_management_system.security.user;


import com.example.job_management_system.mapper.EntityConverter;
import com.example.job_management_system.mapper.UserRecordMapper;
import com.example.job_management_system.security.user.dto.UserRecord;
import com.example.job_management_system.security.user.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImpTest {

    @Mock
    private EntityConverter<AppUser, UserRequest> entityConverter;

    @Mock
    private UserRecordMapper userRecordMapper;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserServiceImp appUserServiceImp;

    private AppUser user;

    private UserRecord userRecord;

    private UserRequest userRequest;

    @BeforeEach
    void setUp(){
         user = new AppUser("Neli", "neli@gmail.com", new BCryptPasswordEncoder().encode("nelineli123"), Role.ADMIN);
         userRecord = new UserRecord(user.getUsername(), user.getEmail(), user.getRole());
         userRequest = new UserRequest(user.getUsername(),user.getEmail(), user.getPassword(), user.getRole());
    }

    @Test
    public void shouldGetAllUsers(){
        when(appUserRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userRecordMapper.apply(user)).thenReturn(userRecord);

        List<UserRecord> records = appUserServiceImp.getAllUsers();
        assertThat(records).hasSize(1);
        assertThat(records.get(0).email()).isEqualTo(user.getEmail());
        assertThat(records.get(0).username()).isEqualTo(user.getUsername());
        assertThat(records.get(0).role()).isEqualTo(user.getRole());

        verify(appUserRepository, times(1)).findAll();
        verify(userRecordMapper, times(1)).apply(user);
    }

    @Test
    public void shouldLoadUserByEmail(){
        String email = "neli@gmail.com";
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(user));
        UserDetails appUserDetails = appUserServiceImp.loadUserByUsername(email);

        assertThat(appUserDetails.getUsername()).isEqualTo(user.getUsername());
        assertThat(appUserDetails.getPassword()).isEqualTo(user.getPassword());
        assertThat(appUserDetails.getAuthorities()).isEqualTo(user.getRole().getGrantedAuthorities());
        assertThat(appUserDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))).isTrue();

        verify(appUserRepository, times(1)).findByEmail(email);
    }

    @Test
    public void shouldCreateUser(){
        when(entityConverter.dtoToEntity(any(UserRequest.class), eq(AppUser.class))).thenReturn(user);
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);
        when(entityConverter.entityToDto(any(AppUser.class), eq(UserRequest.class))).thenReturn(userRequest);

        UserRequest createdUser = appUserServiceImp.createUser(userRequest);

        assertThat(createdUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(createdUser.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void shouldThrowExceptionWhenEmailNotFound() {

        when(appUserRepository.findByEmail("missing@example.com"))
                .thenReturn(Optional.empty());


        UsernameNotFoundException exception = assertThrowsExactly(
                UsernameNotFoundException.class,
                () -> appUserServiceImp.loadUserByUsername("missing@example.com")
        );

        assertThat(exception.getMessage()).isEqualTo("User not found with email : " + "missing@example.com");
    }

}