package com.example.projects.githubprofiles;

public class Details {
    String login, avatarUrl, url, id;

    public Details() {
    }

    public Details(String login, String avatarUrl, String url, String id) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.url = url;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
