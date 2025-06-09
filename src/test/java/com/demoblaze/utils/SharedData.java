package com.demoblaze.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@Scope("cucumber-glue")
public class SharedData {
    private List<String> models = new ArrayList<>();
}
