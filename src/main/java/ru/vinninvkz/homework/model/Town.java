package ru.vinninvkz.homework.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long population;
    private boolean hasMetro;
    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL)
    private List<Showplace> showplaceList;
}
