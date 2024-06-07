package com.pmf.pris.maps;

public class DistanceResponse {

    private String distanceText;
    private long distanceValue;
    private String durationText;
    private long durationValue;

    // Getters and setters
    public String getDistanceText() {
        return distanceText;
    }

    public void setDistanceText(String distanceText) {
        this.distanceText = distanceText;
    }

    public long getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(long distanceValue) {
        this.distanceValue = distanceValue;
    }

    public String getDurationText() {
        return durationText;
    }

    public void setDurationText(String durationText) {
        this.durationText = durationText;
    }

    public long getDurationValue() {
        return durationValue;
    }

    public void setDurationValue(long durationValue) {
        this.durationValue = durationValue;
    }
}
