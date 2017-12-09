package shm.dim.lab_8_9;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String key;
    private String name;
    private String surname;
    private String group;
    private String addInfo;
    private String photoUri;


    public User(){

    }

    public User(String name, String surname, String group, String addInfo, String photoUri, String key) {
        this.key = key;
        this.name = name;
        this.surname = surname;
        this.addInfo = addInfo;
        this.group = group;
        this.photoUri = photoUri;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }


    public Map<String, String> toMap(){
        HashMap<String, String> result = new HashMap<>();
        if(!name.isEmpty() && !surname.isEmpty() && !group.isEmpty() &&
           !addInfo.isEmpty() && !photoUri.isEmpty()) {
            result.put("name",    name);
            result.put("surname", surname);
            result.put("mark",    group);
            result.put("addInfo", addInfo);
            result.put("photoId", photoUri);
        }
        return result;
    }
}