package controllers.Follower;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Follower;
import utils.DBUtil;

/**
 * Servlet implementation class FollowerServlet
 */
@WebServlet("/Follower/FollowerServlet")
public class FollowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // パラメーターの取得
        String follower_id = request.getParameter("follower");

        System.out.println("follower_id = "+ follower_id);

        //loginした人を入手getAttribute
        HttpSession session = ((HttpServletRequest)request).getSession();
        Employee e = (Employee)session.getAttribute("login_employee");
        Follower f = new Follower();//インスタンス化


       // フォロー処理 FOLLOWテーブルに追加
        EntityManager em = DBUtil.createEntityManager();

        //valueOfとは型の変換をしてくれるInteger.valueOf(変換する値)
        //findで取得できるのはidのみ。デストロイサーブレットを参考
        //フォローする人のEmployeeクラスからidを探す
        Employee fe = em.find(Employee.class, Integer.valueOf(follower_id));

        //followersのフィールド（プロパティ）に値を格納している。
        f.setEmployee(e);
        f.setFollower(fe);


        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        List<Employee> followers = em.createNamedQuery("getFollowers1", Employee.class)
                .setParameter("employee_id", e)
                .getResultList();
        request.setAttribute("employees", followers);
        em.close();



        // フォロー完了画面にフォワード
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/follow.jsp");
        rd.forward(request, response);


    }
}
