package com.sviatoslav.app.model;

public class Statistic {

    private Double minTemperature;
    private Double maxTemperature;
    private Double avgTemperature;

    @Override
    public String toString() {
        return "Statistic{" +
                "minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", avgTemperature=" + avgTemperature +
                '}';
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(Double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }
}
