package com.hsjy.manager.hsjymanager.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private  Integer rid;
    private  String name;
    private Set<Permission> permissions = new HashSet<>();
}