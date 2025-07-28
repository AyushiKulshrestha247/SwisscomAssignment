package model;

import lombok.Data;

import java.util.List;

@Data
public class Resource {
    private String id;
    private List<Owner> owners;
}
