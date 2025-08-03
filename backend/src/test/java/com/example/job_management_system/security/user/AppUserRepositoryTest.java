package com.example.job_management_system.security.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AppUserRepositoryTest {

    private final AppUserRepository appUserRepository;

    @Autowired
    AppUserRepositoryTest(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @BeforeEach
    void setUp(){
        AppUser user1 = new AppUser("Jame", "jame@gmail.com", new BCryptPasswordEncoder().encode("jamejame123"), Role.USER);
        AppUser user2 = new AppUser("Neo", "neo@gmail.com", new BCryptPasswordEncoder().encode("neoneo123"), Role.ADMIN);
        AppUser user3 = new AppUser("Neli", "neli@gmail.com", new BCryptPasswordEncoder().encode("nelineli123"), Role.ADMIN);

        appUserRepository.saveAll(Arrays.asList(user1,user2,user3));
    }

    @Test
    public void testFindUserByEmail(){
        String email = "neo@gmail.com";
       Optional<AppUser> appUser =  appUserRepository.findByEmail(email);

       assertThat(appUser).hasValueSatisfying( user -> {
              assertThat(user.getId()).isEqualTo(2L);
              assertThat(user.getUsername()).isEqualTo("Neo");
              assertThat(user.getEmail()).isEqualTo(email);
              assertThat(user.getPassword()).isNotBlank();
              assertThat(user.getRole()).isEqualTo(Role.ADMIN);
       });
    }

    @AfterEach
    void tearDown(){
        appUserRepository.deleteAll();
    }

}