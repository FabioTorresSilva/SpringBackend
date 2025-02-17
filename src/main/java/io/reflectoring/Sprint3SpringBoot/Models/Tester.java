package io.reflectoring.Sprint3SpringBoot.Models;

import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import java.util.ArrayList;
import java.util.List;

public class Tester extends User{

    public List<WaterAnalysisDto> waterAnalysis;

    public Tester(String name, String email, String password) {
        super(name, email, password);
        role = Role.Tester;
        waterAnalysis = new ArrayList<WaterAnalysisDto>();
    }

    public Tester() {
        role = Role.Tester;
    }
}
