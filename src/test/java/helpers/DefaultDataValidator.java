package helpers;

import org.json.JSONObject;

public class DefaultDataValidator {
    public String checkForDefaultData(JSONObject json, UserPojo user) {
        String defaultEmail = "default_email@gmail.com";
        String defaultName = "Default User";
        String defaultID = "99999";

        if(json.toString().contains(defaultEmail) || json.toString().contains(defaultName)) {
            String jsonAsString = json.toString().replace(defaultEmail, user.getEmail());
            jsonAsString = jsonAsString.replace(defaultName, user.getName());
            jsonAsString = jsonAsString.contains("female") ?
                    jsonAsString.replace("female", user.getGender()) :
                    jsonAsString.replace("male", user.getGender());

            return jsonAsString;
        } else if (json.toString().contains(defaultID)) {
            return json.toString().replace(defaultID, String.valueOf(user.getId()));
        }
        return json.toString();
    }
}
