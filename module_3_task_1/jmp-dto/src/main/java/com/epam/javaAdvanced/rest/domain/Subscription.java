package com.epam.javaAdvanced.rest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "subscription_to_user_fk"))
    private User user;
    private LocalDate startDate;
}
