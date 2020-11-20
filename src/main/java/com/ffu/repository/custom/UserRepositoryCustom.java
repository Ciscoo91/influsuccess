package com.ffu.repository.custom;

import com.ffu.domain.User;
import com.ffu.repository.dto.UserSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<User> getUserPageable(UserSearchDTO userSearchDTO, Pageable pageable);
}
