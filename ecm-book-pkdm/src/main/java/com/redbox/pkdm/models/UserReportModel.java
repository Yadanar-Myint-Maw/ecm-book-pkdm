package com.redbox.pkdm.models;

import com.redbox.pkdm.entities.AccountUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserReportModel {

    @Id
    private String id;

    @ManyToOne
    private AccountUser user;

    long count;

    public UserReportModel(){

    }

    public UserReportModel(AccountUser user, long count) {
        this.user = user;
        this.count = count;
    }

    public AccountUser getUser() {
        return user;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
