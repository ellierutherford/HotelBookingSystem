package com.marriott.booking.repository;

import com.marriott.booking.model.User;
import com.marriott.booking.model.UserInfo;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}

