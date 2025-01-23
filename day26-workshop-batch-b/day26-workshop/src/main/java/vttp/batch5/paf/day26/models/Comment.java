package vttp.batch5.paf.day26.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch5.paf.day26.repositories.Constants.*;

public record Comment(String user, int rating, String text) {
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add(F_USER, user)
            .add(F_RATING, rating)
            .add(F_TEXT, text)
            .build();
    }

    public static Comment toComment(JsonObject j) {
        return new Comment(j.getString(F_USER), j.getInt(F_RATING), j.getString(F_TEXT));
    }
}
