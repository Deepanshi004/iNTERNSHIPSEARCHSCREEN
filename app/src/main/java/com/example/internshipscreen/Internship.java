package com.example.internshipscreen;

public class Internship {
    private String title;
    private String company;
    private String details;
    private String imageUrl;
    private int applicants;

    public Internship(String title, String company, String details, String imageUrl, int applicants) {
        this.title = title;
        this.company = company;
        this.details = details;
        this.imageUrl = imageUrl;
        this.applicants = applicants;
    }

    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getDetails() { return details; }
    public String getImageUrl() { return imageUrl; }
    public int getApplicants() { return applicants; }
}
