package com.launchacademy;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = {"/bookmarks/new", "/bookmarks"})
public class BookmarksController extends HttpServlet {

  private EntityManagerFactory getEmf() {
    return (EntityManagerFactory)this.getServletContext().getAttribute("emf");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getServletPath().equals("/bookmarks/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/form.jsp");
      dispatcher.forward(req, resp);

    //List out all the bookmarks
    } else if (req.getServletPath().equals("/bookmarks")) {

    //Query bookmarks db
    //Make sure it is not empty
      //EntityManagerFactory emf =
         // Persistence.createEntityManagerFactory("com.launchacademy.javaDatabaseApplications");
      //EntityManager em = emf.createEntityManager();

      //TypedQuery<Bookmark> query = em.createQuery("SELECT b FROM Bookmark b Order by title", Bookmark.class);
      //List<Bookmark> bookmarks = query.getResultList();
      EntityManagerFactory emf = getEmf();
      EntityManager em = emf.createEntityManager();

      BookmarkService service = new BookmarkService(em);

      //Set the attribute and call the show page
    req.setAttribute("bookmarks", service.getBookmarkList());


    RequestDispatcher dispatcher = req.getRequestDispatcher("/views/bookmarks/showBookmarks.jsp");
    dispatcher.forward(req, resp);
  } else {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
  }
}


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if(req.getServletPath().equals("/bookmarks")) {
      Bookmark bookmark = new Bookmark();
      try {
        BeanUtils.populate(bookmark, req.getParameterMap());
      }
      catch(IllegalAccessException ex) {
        //do some logging
      }
      catch(InvocationTargetException ex) {
        //do some logging
      }

      EntityManagerFactory emf = getEmf();
      EntityManager em = emf.createEntityManager();

      BookmarkService service = new BookmarkService(em);
      if(!service.save(bookmark)) {
        //do some error logging, re-render the form, etc
      }
      resp.sendRedirect("/bookmarks/new");
      em.close();
    }
    else {
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }
}
