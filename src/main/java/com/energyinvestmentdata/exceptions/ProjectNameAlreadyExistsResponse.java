package com.energyinvestmentdata.exceptions;

/**
 * @author Rabiu Ademoh
 */
public class ProjectNameAlreadyExistsResponse {

    private String projectName;

    public ProjectNameAlreadyExistsResponse(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
