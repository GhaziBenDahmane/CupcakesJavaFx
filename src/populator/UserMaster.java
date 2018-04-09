package populator;

import entity.User;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

public class UserMaster {

    private SimpleStringProperty username;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private SimpleStringProperty lastLogin;
    private SimpleStringProperty role;
    private SimpleStringProperty phone;
    private SimpleStringProperty picture;
    private User user;

    public UserMaster() {
    }

    public UserMaster(User user) {
        System.out.println(user);
        this.user = user;
        this.username = new SimpleStringProperty(user.getUsername());
        this.email = new SimpleStringProperty(user.getEmail());
        this.password = new SimpleStringProperty(user.getPassword());
        this.lastLogin = new SimpleStringProperty(user.getLastLogin().toString());
        this.role = new SimpleStringProperty(user.getRoles().get(user.getRoles().size() - 1));
        this.phone = new SimpleStringProperty(user.getPhone());
        this.picture = new SimpleStringProperty(user.getPhotoprofil());
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(SimpleStringProperty username) {
        this.username = username;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(SimpleStringProperty password) {
        this.password = password;
    }

    public String getLastLogin() {
        return lastLogin.get();
    }

    public void setLastLogin(SimpleStringProperty lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(SimpleStringProperty Role) {
        this.role = Role;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(SimpleStringProperty phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture.get();
    }

    public void setPicture(SimpleStringProperty picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.username);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + Objects.hashCode(this.lastLogin);
        hash = 41 * hash + Objects.hashCode(this.role);
        hash = 41 * hash + Objects.hashCode(this.phone);
        hash = 41 * hash + Objects.hashCode(this.picture);
        hash = 41 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserMaster other = (UserMaster) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.lastLogin, other.lastLogin)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.picture, other.picture)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserMaster{" + "username=" + username + ", email=" + email + ", password=" + password + ", lastLogin=" + lastLogin + ", Role=" + role + ", phone=" + phone + ", picture=" + picture + ", user=" + user + '}';
    }

}
