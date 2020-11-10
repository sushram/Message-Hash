package com.abc.messagehash.data.repo;

import com.abc.messagehash.data.entity.MessageHash;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageHashRepository extends CrudRepository<MessageHash,Long> {

    List<MessageHash> findByHash(String hash);
}
