package com.samax.simpleCommerce.session.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionEntryId;

    @Column(nullable = false)
    private String itemKey;

    private String itemValue;

    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String sessionId;

    @Column(nullable = false)
    private String userId;

}
