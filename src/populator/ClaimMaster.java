package populator;

import entity.Claim;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ding
 */
public class ClaimMaster {

    private SimpleStringProperty clientName;
    private SimpleStringProperty description;
    private SimpleStringProperty postedOn;
    private SimpleStringProperty answer;
    private SimpleStringProperty answeredBy;
    private Claim claim;

    public ClaimMaster(SimpleStringProperty clientName, SimpleStringProperty description, SimpleStringProperty postedOn, SimpleStringProperty answer, SimpleStringProperty answeredBy) {
        this.clientName = clientName;
        this.description = description;
        this.postedOn = postedOn;
        this.answer = answer;
        this.answeredBy = answeredBy;
    }

    public ClaimMaster(Claim claim) {
        this.claim = claim;
        this.clientName
                = clientName = new SimpleStringProperty(claim.getClient().getUsername());
        this.description = new SimpleStringProperty(claim.getDescription());
        this.postedOn = new SimpleStringProperty(claim.getPostedOn().toString());
        this.answer = new SimpleStringProperty(claim.getAnswer() != null ? claim.getAnswer() : "");
        this.answeredBy = new SimpleStringProperty(claim.getAnsweredBy() != null ? claim.getAnsweredBy().getUsername() : "");
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public String getClientName() {
        return clientName.get();
    }

    public SimpleStringProperty clientNameProperty() {
        return clientName;
    }

    public void setClientName(SimpleStringProperty clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(SimpleStringProperty description) {
        this.description = description;
    }

    public String getPostedOn() {
        return postedOn.get();
    }

    public void setPostedOn(SimpleStringProperty postedOn) {
        this.postedOn = postedOn;
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(SimpleStringProperty answer) {
        this.answer = answer;
    }

    public String getAnsweredBy() {
        return answeredBy.get();
    }

    public void setAnsweredBy(SimpleStringProperty answeredBy) {
        this.answeredBy = answeredBy;
    }

    @Override
    public String toString() {
        return "ClaimMaster{" + "clientName=" + clientName + ", description=" + description + ", postedOn=" + postedOn + ", answer=" + answer + ", answeredBy=" + answeredBy + '}';
    }

}
