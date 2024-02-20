package com.test.splitwise.repos;

import com.test.splitwise.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer> {

}
