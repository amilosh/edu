package by.milosh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity to check application features.
 * <p/>
 * Created on 2021-03-04
 * <p/>
 *
 * @author Alexander Milosh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String name;
    private String team;
    private String country;
    private Position position;
    private Integer salary;
}
