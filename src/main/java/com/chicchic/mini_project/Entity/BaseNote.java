package com.chicchic.mini_project.Entity;

import com.chicchic.mini_project.dao.PerfumeDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "BASE_NOTES")
public class BaseNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "perfumeNumber")
    @JsonBackReference
    private PerfumeDetail perfume;

    @ManyToOne
    @JoinColumn(name = "note_number")
    private Note note;
}
