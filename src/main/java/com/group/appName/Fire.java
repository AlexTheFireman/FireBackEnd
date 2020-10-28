package com.group.appName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fire {
    private String id;
    private String date;
    private String message;
    private String addressObjectFireFeature;
    private String district;
    private String fireStation;
    private String destination;
    private String whereWasTheFire;
    private String rescueWorks;
    private String amountOfRescuedPeople;
    private String amountOfEvacuatedPeople;
    private String fireChiefRank;
    private String fireChiefName;
    private String amountOfSmokeGroups;
    private String smokeTime;
    private String extinguishingAgents;
    private String firstEngine;
    private String secondEngine;
    private String thirdEngine;
    private String firstReserve;
    private String secondReserve;
    private String firstSquadron;
    private String secondSquadron;
    private String usingHydrants;
    private String reportPDF;
    private String locality;
    private String fireRank;
    private String detectionTime;
    private String messageTime;
    private String arrivalTime;
    private String firstNozzleTime;
    private String localizationTime;
    private String burningLiquidationTime;
    private String totalLiquidationTime;
    private String comment;

    public Fire() {
    }

     @Override
    public String toString() {
        return "Fire [id=" + id + ", date=" + date + ", message=" + message + ", addressObjectFireFeature=" +
                addressObjectFireFeature + ", district=" + district + ", fireStation=" + fireStation + ", destination=" +
                destination + ", whereWasTheFire=" + whereWasTheFire + ", rescueWorks=" + rescueWorks + ", amountOfRescuedPeople=" +
                amountOfRescuedPeople + ", amountOfEvacuatedPeople=" + amountOfEvacuatedPeople + ", fireChiefRank=" +
                fireChiefRank + ", fireChiefName=" + fireChiefName + ", amountOfSmokeGroups=" + amountOfSmokeGroups +
                ", smokeTime=" + smokeTime + ", extinguishingAgents=" + extinguishingAgents + ", firstEngine=" +
                firstEngine + ", secondEngine=" + secondEngine + ", thirdEngine=" + thirdEngine + ", firstReserve=" +
                firstReserve + ", secondReserve=" + secondReserve + ", firstSquadron=" + firstSquadron +
                ", secondSquadron=" + secondSquadron + ", usingHydrants=" + usingHydrants + ", reportPDF=" + reportPDF +
                ", locality=" + locality + ", fireRank=" + fireRank + ", detectionTime=" + detectionTime +
                ", messageTime=" + messageTime + ", arrivalTime=" + arrivalTime + ", firstNozzleTime=" +
                firstNozzleTime + ", localizationTime=" + localizationTime + ", burningLiquidationTime=" +
                burningLiquidationTime + ", totalLiquidationTime=" + totalLiquidationTime + ", comment=" + comment + "]";
    }

}