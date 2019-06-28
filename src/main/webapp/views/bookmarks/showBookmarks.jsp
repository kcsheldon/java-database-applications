<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "java.util.List"%>
<%@ page import = "java.util.HashMap"%>
<%@ page import = "com.launchacademy.Bookmark"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Bookmarks</title>
  </head>
  <body>
    <h4>List of Bookmarks</h4>
    <h5>Title       Author        Description</h5>
         <% List<Bookmark> bookmarks = (ArrayList)request.getAttribute("bookmarks"); %>
         <ul>
          <%
          for(Bookmark bookmark:bookmarks){
                       %>
                         <li><%= bookmark.getTitle() + " " + bookmark.getUrl() + " "  +  bookmark.getDescription() %></li>

                         <% } %>
                      </ul>

        <p><a href="/bookmarks/new">Add a new bookmark</a></p>
  </body>
</html>