package pl.artsit.flexgoals.model;

import java.util.Date;

public class ChoosenPath {

   private Integer id;
   private Integer idGoal;
   private Integer idUser;
   private Date date;

    public ChoosenPath(Integer id, Integer idGoal, Integer idUser, Date date) {
        this.id = id;
        this.idGoal = idGoal;
        this.idUser = idUser;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGoal() {
        return idGoal;
    }

    public void setIdGoal(Integer idGoal) {
        this.idGoal = idGoal;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "choosenPath{" +
                "id=" + id +
                ", idGoal=" + idGoal +
                ", idUser=" + idUser +
                ", date=" + date +
                '}';
    }
}
