package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalSpecialtyApplication {



    public static void main(String[] args) {
        SpringApplication.run(MedicalSpecialtyApplication.class, args);

        String inputSpecialty = "Physician Assistant1";

        boolean containsSpecialty = containsMedicalSpecialty(inputSpecialty);
        System.out.println("Medical Specialty exists: " + containsSpecialty);
    }

    public static boolean containsMedicalSpecialty(String specialty) {
        for (MedicalSpecialty medicalSpecialty : MedicalSpecialty.values()) {
            if (medicalSpecialty.getSpecialty().equalsIgnoreCase(specialty)) {
                return true;
            }
        }
        return false;
    }
}


enum MedicalSpecialty {
    NURSE_PRACTITIONER("Nurse Practitioner"),
    FAMILY_NURSE_PRACTITIONER("Family Nurse Practitioner"),
    PHYSICIAN_ASSISTANT("Physician Assistant"),
    PEDIATRIC_NURSE_PRACTITIONER("Pediatric Nurse Practitioner");

    private final String specialty;

    MedicalSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }
}
