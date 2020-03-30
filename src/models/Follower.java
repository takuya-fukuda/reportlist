package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


//日報拡張機能により新たにテーブルを作成。
//employeeテーブルと多対多で結合させる。
@Table(name = "followers")
@NamedQueries({
    @NamedQuery(
            name = "getAllFollowers",
            query = "SELECT f FROM Follower AS f ORDER BY f.id ASC"
            ),
    @NamedQuery(
            name = "getFollowersCount",
            query = "SELECT COUNT(f) FROM Follower AS f"
            ),
    @NamedQuery(
            name = "getFollowers",
            query = "SELECT f FROM Follower AS f WHERE f.employee = :employee_id and f.follower = :follower_id"
            ),//おそらく重複したときに
    @NamedQuery(
            name = "getFollowers1",
            query = "SELECT f.follower FROM Follower AS f WHERE f.employee = :employee_id"
            ),

})
@Entity
public class Follower {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne//1対多
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "follower_id",nullable = false)
    private Employee follower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getFollower() {
        return follower;
    }

    public void setFollower(Employee follower) {
        this.follower = follower;
    }


}
