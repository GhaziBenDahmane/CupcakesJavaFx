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
    private User user;

    public UserMaster() {
    }

    public UserMaster(User user) {
        this.user = user;
        this.username = new SimpleStringProperty(user.getUsername());
        this.email = new SimpleStringProperty(user.getEmail());
        this.password = new SimpleStringProperty(user.getPassword());
        this.lastLogin = new SimpleStringProperty(user.getLastLogin().toString());
        this.role = new SimpleStringProperty(user.isAdmin() ? "Admin" : "User");
        this.phone = new SimpleStringProperty(user.getPhone());
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty lastLoginProperty() {
        return lastLogin;
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
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

        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserMaster{" + "username=" + username + ", email=" + email + ", password=" + password + ", lastLogin=" + lastLogin + ", role=" + role + ", phone=" + phone + ", user=" + user + '}';
    }

}
