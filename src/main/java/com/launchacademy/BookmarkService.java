package com.launchacademy;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BookmarkService {
  private EntityManager em;

  public BookmarkService(EntityManager em) {
    this.em = em;
  }

  public boolean save(Bookmark bookmark) {
    try {
      em.getTransaction().begin();
      em.persist(bookmark);
      em.getTransaction().commit();
      return true;
    }
    catch(Exception exc) {
      //an error occurred with the INSERT so return false
      em.getTransaction().rollback();
      return false;
    }

  }
  public List<Bookmark> getBookmarkList()   {
    TypedQuery<Bookmark> query = em.createQuery("SELECT b FROM Bookmark b Order by title", Bookmark.class);
    List<Bookmark> bookmarks = query.getResultList();
    return(bookmarks);
  }
}
