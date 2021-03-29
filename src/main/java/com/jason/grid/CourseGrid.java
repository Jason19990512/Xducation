package com.jason.grid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseGrid implements Serializable {
    private String searchText;
    private String refcstypeno;
    private String order;
}
