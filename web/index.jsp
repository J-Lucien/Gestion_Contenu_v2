<%-- 
    Document   : index
    Created on : Dec 17, 2021, 3:12:57 PM
    Author     : Lucien
--%>

<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <link rel="stylesheet" type="text/css" href="assets/css/styleAdmin.css">
    </head>
    <body>

        <div class="login">
            <h1>Login</h1>
            <form action="login.do" method="post" enctype="multipart/form-data">
                <input type="email" name="utilisateur/email" placeholder="email" required="required" />
                <input type="password" name="utilisateur/password" placeholder="Password" required="required" />
                <button type="submit" class="btn btn-primary btn-block btn-large">Connecter</button>
            </form>
            <div>
                <%
                    String msg;
                    msg = (String) request.getAttribute("erreur");
                    if (msg == null) {
                        msg = "";
                    }

                %>
                <p style="color: red;text-align: center">
                    <%= msg%>
                </p>
            </div>
        </div>



    </body>
</html>