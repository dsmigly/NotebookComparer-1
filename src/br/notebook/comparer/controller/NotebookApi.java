package br.notebook.comparer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import br.notebook.comparer.dao.NotebookDao;
import br.notebook.comparer.model.Notebook;

/**
 * Servlet implementation class People
 */
@WebServlet("/notebook")
public class NotebookApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NotebookDao pd;
	
	//private ArrayList<People> peopleData = new ArrayList<People>(); //Pode remover...
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotebookApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, 
	                                                          IOException {
		// TODO Auto-generated method stub
		response.getWriter()
                    .append(request.getParameter("pid")).append(";")
		            .append(request.getParameter("pname")).append(";")
		            .append(request.getParameter("email")).append(";")
		            .append(request.getParameter("dob")).append(";")
		            .append(request.getParameter("color")).append(";")
		            .append(request.getParameter("cardType")).append(";")
		            .append(request.getParameter("value")).append(";")
		            .append(request.getParameter("periodM")).append(";")
		            .append(request.getParameter("periodA")).append(";")
		            .append(request.getParameter("periodN"));
		
		System.out.println(request);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Notebook p = new Notebook(
				request.getParameter("pid"), //ID sera gerado no BD pela sequence
				request.getParameter("pname"),
				request.getParameter("email"),
        		request.getParameter("dob"),
        		request.getParameter("value"),
        		request.getParameter("color"),
        		request.getParameter("cardType"),
        		request.getParameter("gender"),
        		request.getParameter("periodM") + ";" +
        		request.getParameter("periodA") + ";" +
        		request.getParameter("periodN"));
		
		System.out.print(p);
		
		
		
		
		String status = "OK";
		String message = "Notebook data saved!";
		String now = String.valueOf(new Date());
		
		try {
		   
		   if (pd == null)
			   pd = new NotebookDao();	
		   
		   pd.addNotebook(p);
		
		} catch (Exception e) {
			status = "Error";
			message = e.getMessage();
			System.err.println(now +  " - Ops!! - " + message);
			System.err.println(now +  " - Ops!! - " + p);
			e.printStackTrace();
		}
		
		response.setContentType("application/json"); //MIME Type
		response.setCharacterEncoding("utf-8");
		
	    //create Json Object
		JsonObject json = new JsonObject();

		// put some value pairs into the JSON object .
		
		json.addProperty("Status", status);
		json.addProperty("Message", message);
		json.addProperty("Time", now);


		 response.getWriter().print(json.toString());
         response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
