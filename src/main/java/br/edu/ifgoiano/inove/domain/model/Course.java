package br.edu.ifgoiano.inove.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdateDate;

    @ManyToMany
    @JoinTable(name = "tb_student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<User> student;

    @ManyToMany
    @JoinTable(name = "tb_admin_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<User> admins;

    @ManyToMany
    @JoinTable(name = "tb_instructor_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<User> instructors;

    @OneToMany(mappedBy = "course")
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}
