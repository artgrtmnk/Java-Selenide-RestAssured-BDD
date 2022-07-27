package utils;

import org.json.JSONObject;

public class DefaultDataValidator {
    public String checkForDefaultData(JSONObject json, UserPojo user) {
        final String DEFAULT_EMAIL = "default_email@gmail.com";
        final String DEFAULT_NAME = "Default User";
        final String DEFAULT_ID = "99999";

        if(json.toString().contains(DEFAULT_EMAIL) || json.toString().contains(DEFAULT_NAME)) {
            String jsonAsString = json.toString().replace(DEFAULT_EMAIL, user.getEmail());
            jsonAsString = jsonAsString.replace(DEFAULT_NAME, user.getName());
            jsonAsString = jsonAsString.contains("female") ?
                    jsonAsString.replace("female", user.getGender()) :
                    jsonAsString.replace("male", user.getGender());

            return jsonAsString;
        } else if (json.toString().contains(DEFAULT_ID)) {
            return json.toString().replace(DEFAULT_ID, String.valueOf(user.getId()));
        }
        return json.toString();
    }
}
