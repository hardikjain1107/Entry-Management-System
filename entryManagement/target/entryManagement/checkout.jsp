<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
    body {
        background-image: url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
    }
</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Check-Out Page</title>
</head>
<body>

<center>
    <h1>Check-Out</h1>

    <div align="center" style="margin-top: 50px;">

        <form method="post" action="checkout">
            <h2>Visitor's Personal information: </h2>

            <input type="text" name="username" placeholder="Name" size="35"
                   STYLE="color: rgb(20, 20, 20); background-color: rgb(114, 196, 210);" size="30"
                   maxlength="30"><br><br>
            <input type="text" name="userEmail" placeholder="Email Address" size="35"
                   STYLE="color: #FFFFFF; background-color: rgb(114, 196, 210);" size="30" maxlength="30"><br><br>
            <br>
            <input type="submit" value="Submit">
        </form>
    </div>
</center>
</body>
</html>