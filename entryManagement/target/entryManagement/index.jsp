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
    <title>Entry management</title>
</head>
<body>



<div align="center" style="margin-top: 50px;">
    <h1>Welcome</h1>
    <form action="">
        <input type="submit" name="checkin" value="Chech-In" size="500"
               style="color: rgb(20, 20, 20); padding: 10px; background-color: rgb(199, 188, 30);" maxlength="100"
               onclick="document.forms[0].action = 'checkin.jsp'; return true;"> <br><br>
        <input type="submit" name="checkout" value="Chech-Out" size="500"
               style="color: rgb(20, 20, 20); padding: 10px; background-color: rgb(210, 114, 114);" maxlength="100"
               onclick="document.forms[0].action = 'checkout.jsp'; return true;"> <br><br>
    </form>
</div>

</body>
</html>
