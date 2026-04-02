package com.SmartGarbageCollection.GarbageCollection.Entity;

public enum PickupStatus {

    REQUESTED,      // User created request
    IN_PROGRESS,    // Collector accepted / started
    COMPLETED,      // Successfully collected
    FAILED          // Not collected / rejected / missed
}