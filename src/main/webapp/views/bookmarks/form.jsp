<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Add a New Bookmark</title>
  </head>
  <body>
    <form action="/bookmarks" method="post">
      <div>
        <label for="title">Title</label>
        <input type="text" name="title" value="" />
      </div>
      <div>
        <label for="url">Not Really a Url</label>
        <input type="text" name="url" value="" />
      </div>
      <div>
        <label for="description">Description</label>
        <textarea name="description"></textarea>
      </div>
       <input type="submit" value="Save" />
    </form>

    <form action="/bookmarks" method="get" >
        <input type="submit" value="List All Bookmarks" />
    </form>
  </body>
</html>