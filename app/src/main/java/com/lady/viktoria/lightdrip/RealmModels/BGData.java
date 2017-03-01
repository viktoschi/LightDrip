package com.lady.viktoria.lightdrip.RealmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BGData extends RealmObject {

    @PrimaryKey
    private long id;
    public long getid() {
        return id;
    }
    public void setid(final long id) {
        this.id = id;
    }

    private double a;
    public double getA() {
        return a;
    }
    public void setA(final double a) {
        this.a = a;
    }

    private double ageAdjustedRawValue;
    public double getageAdjustedRawValue() {
        return ageAdjustedRawValue;
    }
    public void setageAdjustedRawValue(final double ageAdjustedRawValue) {
        this.ageAdjustedRawValue = ageAdjustedRawValue;
    }

    private double b;
    public double getB() {
        return b;
    }
    public void setB(double b) {
        this.b = b;
    }

    private double c;
    public double getC() {
        return c;
    }
    public void setC(double c) {
        this.c = c;
    }

    private double calculatedValue;
    public double getCalculatedValue() {
        return calculatedValue;
    }
    public void setCalculatedValue(double calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    private double calculatedValueSlope;
    public double getCalculatedValueSlope() {
        return calculatedValueSlope;
    }
    public void setCalculatedValueSlope(double calculatedValueSlope) {
        this.calculatedValueSlope = calculatedValueSlope;
    }

    private boolean calibrationFlag;
    public boolean getCalibrationFlag() {
        return calibrationFlag;
    }
    public void setCalibrationFlag(boolean calibrationFlag) {
        this.calibrationFlag = calibrationFlag;
    }

    private String calibrationUuid;
    public String getCalibrationUuid() {
        return calibrationUuid;
    }
    public void setCalibrationUuid(String calibrationUuid) {
        this.calibrationUuid = calibrationUuid;
    }

    private double filteredData;
    public double getFilteredData() {
        return filteredData;
    }
    public void setFilteredData(double filteredData) {
        this.filteredData = filteredData;
    }

    private double ra;
    public double getRa() {
        return ra;
    }
    public void setRa(double ra) {
        this.ra = ra;
    }

    private double rawData;
    public double getRawData() {
        return rawData;
    }
    public void setRawData(double rawData) {
        this.rawData = rawData;
    }

    private double rb;
    public double getRb() {
        return rb;
    }
    public void setRb(double rb) {
        this.rb = rb;
    }

    private double rc;
    public double getRc() {
        return rc;
    }
    public void setRc(double rc) {
        this.rc = rc;
    }

    private String sensorUuid;
    public String getSensorUuid() {
        return sensorUuid;
    }
    public void setSensorUuid(String sensorUuid) {
        this.sensorUuid = sensorUuid;
    }

    private double timeSinceSensorStarted;
    public double getTimeSinceSensorStarted() {
        return timeSinceSensorStarted;
    }
    public void setTimeSinceSensorStarted(double timeSinceSensorStarted) {
        this.timeSinceSensorStarted = timeSinceSensorStarted;
    }

    private double timestamp;
    public double getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

}