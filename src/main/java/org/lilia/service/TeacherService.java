package org.lilia.service;

import org.lilia.models.Teacher;

public class TeacherService {
    public Teacher createTeacher(int id){
        return new Teacher(id);
    }
}
