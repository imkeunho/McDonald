package servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import service.Action;
import service.ActionForward;
import service.SuperStockUpdate;
import service.SuperStockService;
import service.StoreAddService;
import service.StoreDeleteService;
import service.StoreService;
import service.StoreUpdateService;

import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SuperServlet", value = "/super/*")
public class SuperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	System.out.println(url_Command);
    	
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/super/stockpage.do")) { //재고관리페이지 (발주조회)
    		action = new SuperStockService();
    		forward = action.execute(request, response);
    	
    	}else if(url_Command.equals("/super/stockupdate.do")) { //발주 주문 확인
    		action = new SuperStockUpdate();
    		forward = action.execute(request, response);
    	
    	}else if(url_Command.equals("/super/storepage.do")) { //가맹점조회
    		action = new StoreService();
    		forward = action.execute(request, response);
    	
    	}else if(url_Command.equals("/super/storeadd.do")) { //가맹점등록
    		action = new StoreAddService();
    		forward = action.execute(request, response);
    	
    	}else if(url_Command.equals("/super/storeupdate.do")) { //가맹점 정보 수정
    		action = new StoreUpdateService();
    		forward = action.execute(request, response);
    	
    	}else if(url_Command.equals("/super/storedelete.do")) { //가맹점 삭제
    		action = new StoreDeleteService();
    		forward = action.execute(request, response);
    	
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    	
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);

    }
}