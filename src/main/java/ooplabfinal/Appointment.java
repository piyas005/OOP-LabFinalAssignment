package ooplabfinal;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private Date appointmentDate;

    public Appointment(Doctor doctor, Patient patient, Date appointmentDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
    }

    // Getters and Setters
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment [Doctor: " + doctor.getName() + ", Patient: " + patient.getName() + ", Date: " + appointmentDate + "]";
    }
}

