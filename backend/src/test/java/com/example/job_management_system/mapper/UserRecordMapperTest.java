package com.example.job_management_system.mapper;

import com.example.job_management_system.security.user.AppUser;
import com.example.job_management_system.security.user.Role;
import com.example.job_management_system.security.user.dto.UserRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRecordMapperTest {

    private UserRecordMapper userRecordMapper = new UserRecordMapper();

    @Test
    public void shouldMapAppUserToUserRecord() {
        AppUser appUser = mock(AppUser.class);
        when(appUser.getUsername()).thenReturn("Neo");
        when(appUser.getEmail()).thenReturn("neo@gmail.com");
        when(appUser.getRole()).thenReturn(Role.ADMIN);

         UserRecord request = userRecordMapper.apply(appUser);

      assertThat(request.username()).isEqualTo(appUser.getUsername());
      assertThat(request.email()).isEqualTo(appUser.getEmail());
      assertThat(request.role()).isEqualTo(appUser.getRole());
    }

}