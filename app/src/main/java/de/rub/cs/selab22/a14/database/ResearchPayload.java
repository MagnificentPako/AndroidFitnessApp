package de.rub.cs.selab22.a14.database;

import java.util.List;
import java.util.UUID;

import de.rub.cs.selab22.a14.database.entities.DBActivity;
import de.rub.cs.selab22.a14.database.entities.Data;

public class ResearchPayload {

    public List<Data> data;
    public List<DBActivity> dbActivities;
    public UUID uuid;

    public ResearchPayload(List<Data> data, List<DBActivity> dbActivities, UUID uuid) {
        this.data = data;
        this.dbActivities = dbActivities;
        this.uuid = uuid;
    }
}
