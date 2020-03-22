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
 * Servlet implementation class unfollowservlet
 */
@WebServlet("/unfollow")
public class unfollowservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public unfollowservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        System.out.println("_token = " + _token);

        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            HttpSession session = ((HttpServletRequest) request).getSession();
            Employee e = (Employee) session.getAttribute("login_employee");
            Employee employee = em.find(Employee.class, Integer.valueOf(request.getParameter("follower")));

            System.out.println((request.getParameter("follower")));
            //Followerテーブルから値を探しだす。
            List<Follower> followers = em.createNamedQuery("getFollowers", Follower.class)
                    .setParameter("employee_id", e)
                    .setParameter("follower_id", employee)
                    .getResultList();
            em.remove((followers.get(0)));//Followerテーブルから取り除く


            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().removeAttribute("follower");
            request.getSession().setAttribute("flush", "アンフォローが完了しました。");


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/unfollow.jsp");
            rd.forward(request, response);

        }
    }

}
