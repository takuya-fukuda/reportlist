package conrollers.Search;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            //従業員の社員番号カラムを呼び出し
            String code = request.getParameter("code");

            //クエリを使いたいので呼び出し
            //従業員番号を入力すると、社員情報が表示されるクエリ
            Employee employee = em.createNamedQuery("getEmployeesSeach", Employee.class)
                    .setParameter("code", code).getSingleResult();



            em.close();
            request.setAttribute("employee", employee);
            request.getSession().setAttribute("flush", "検索が完了しました。");



            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/search.jsp");
            rd.forward(request, response);



    }
    }
}

