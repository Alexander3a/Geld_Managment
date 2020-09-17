package de.alex.Geld;

import java.security.Permission;
import java.util.HashMap;

public class Perms {
    Boolean read;
    Boolean write;
    Boolean delete;
    Boolean show_silent;
    Boolean show_deleted;
    public Perms(Boolean r,Boolean w,Boolean delete,Boolean show_silent, Boolean show_deleted){
        this.read = r;
        this.write = w;
        this.delete = delete;
        this.show_deleted = show_deleted;
        this.show_silent = show_silent;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getShow_silent() {
        return show_silent;
    }

    public void setShow_silent(Boolean show_silent) {
        this.show_silent = show_silent;
    }

    public Boolean getShow_deleted() {
        return show_deleted;
    }

    public void setShow_deleted(Boolean show_deleted) {
        this.show_deleted = show_deleted;
    }
}
