package com.chicchic.mini_project.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter

    @Entity
    @Table(name = "NOTES")
public class Note {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int note_number;

        private String kor_name;
}
