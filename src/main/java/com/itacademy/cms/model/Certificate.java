package com.itacademy.cms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "certificates")
@NoArgsConstructor
@Getter
@Setter
public class Certificate extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    @Column
    @NotNull(message = "Certificate name should be present")
    @Size(min = 10, max = 30, message = "Certificate name length should be in scope between 10 and 30 letters")
    private String name;

//    @Column
    @OneToOne
    //@JoinColumn(name = "id")
    private Course course;

//    @Column
    @ManyToOne
    //@JoinColumn(name = "id")
    private User user;

}
