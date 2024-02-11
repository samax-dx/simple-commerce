package com.samax.simpleCommerce.session.repository;

import com.samax.simpleCommerce.session.model.SessionEntry;
import com.samax.simpleCommerce.session.model.SessionUserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SessionEntryRepository extends JpaRepository<SessionEntry, Integer> {

    Optional<SessionUserView> findTopBySessionId(String sessionId);

}
