

<%@page import="TypeContenu.TypeContenu"%>
<%@page import="Utilisateur.Utilisateur"%>
<%@page import="java.lang.String"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Contenu.Contenu"%>
<%@page import="java.util.List"%>
<%!
    String include = "/pageInclude.jsp";
    String pageInclude = include;
    String test = "";

    List<Object> listTypeContenu = null;
    List<Object> utilisateur = null;
    String login = "";
%>
<%
    

    listTypeContenu = (List<Object>) request.getAttribute("listTypeContenu");
    utilisateur = (List<Object>) request.getAttribute("utilisateur");
    test = (String) request.getAttribute("pageInc");
    login = (String)session.getAttribute("login");
    if(login == null){
        response.sendRedirect("/Gestion_Contenu/index.jsp");
    }
    
    //out.print("<br><br><br>include "+test);
%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <title>Gestion contenu</title>


        <!-- Bootstrap Core CSS -->
        <link href="<%= request.getContextPath()%>/assets/css/bootstrap.css" rel="stylesheet">


        <!-- Custom CSS -->
        <link href="<%= request.getContextPath()%>/assets/css/dash_user.css" rel="stylesheet">
        <!-- <link href="../assets/css/font-awesome.css" rel="stylesheet"> -->

        <!-- Custom Fonts -->
        <!-- <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"> -->
        <!-- FONT AWESOME CDN -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>


    <body>

        <div id="wrapper">

            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <!--  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                         <span class="sr-only">Toggle navigation</span>
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                     </button> -->
                    <a class="navbar-brand" href="#"></i> G-Contenu</a> 
                </div>
                <!-- /.navbar-header -->

                <a style="position:absolute;top:35px;left:-1px;font-size:20px;color:#7f7f7f;padding:5px;background-color: #000;border-bottom-right-radius:10px; " href="#menu-toggle" id="menu-toggle" <i class="fa fa-dedent"></i></a>

                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav" style="margin-right: 10px">
                    <li class="dropdown">

                        <%="<a style=\"font-size:16px\" href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-user\"></i> " + login + " <b class=\"caret\"></b></a> "%>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" data-toggle="modal" data-target="#profile"><i class="fa fa-fw fa-user"></i> Profile</a>
                            </li>

                            <li class="divider"></li>
                            <li>
                                <form method="post" action="logOut.do" enctype="multipart/form-data">
                                    <button type="submit" style="background-color: white; color: black; border-width: 0px"> Log Out</button>
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- /.top-nav -->
            </nav>
            <!-- /.navbar -->

            <!-- MODAL -> USER INFO -->
            <div class="modal fade" id="profile" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h3 class="modal-title"><b><i class="fa fa-fw fa-user"></i>PROFILE</b></h3>
                        </div>
                        <div class="modal-body text-center">
                            <h4>Name : <code><%= ((Utilisateur) utilisateur.get(0)).getNom()%></code></h4>
                            <h4>Email : <code><%= ((Utilisateur) utilisateur.get(0)).getEmail()%></code></h4>
                            <h4>Phone No : <code><%= ((Utilisateur) utilisateur.get(0)).getContact()%></code></h4>
                            <h4>Address : <code><%= ((Utilisateur) utilisateur.get(0)).getAddresse()%></code></h4>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->      


            <!-- MODAL -> CHANGING PASSWORD -->
            <div class="modal fade" id="change_password" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h3 class="modal-title"><b><i class="fa fa-fw fa-gear"></i>Settings</b></h3>
                        </div> 


                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->                                                                                                                        
            <!-- Sidebar -->
            <div id="sidebar-wrapper">
                <form method="post" action="setPage.do" enctype="multipart/form-data">
                    <input type="text" name="utilisateur/email" value="<%= ((Utilisateur) utilisateur.get(0)).getEmail()%>" hidden="">

                    <ul class="sidebar-nav">
                        <li class="active">
                            <button type="submit" style="background-color: black;color: white; border-width: 0px"><i class="fa fa-fw fa-dashboard"></i> Accueil</button>
                        </li>
                        <% for (int i = 0; i < listTypeContenu.size(); i++) {%>
                        <% pageInclude = ((TypeContenu) listTypeContenu.get(i)).getPage(); %>
                        <li>
                            <button type="submit" name="pageInclude" value="<%= "/"+pageInclude+"/"+ pageInclude+".jsp" %>" style="background-color: black;color: white; border-width: 0px"><%= ((TypeContenu) listTypeContenu.get(i)).getNom()%></button>
                            
                        </li>
                        <% }%>
                        <!--new bills by bootstrap badges -->

                    </ul>
                </form>
            </div>
            <!-- /#sidebar-wrapper -->

            <!--PAGE INCLUDE JSP--->
            <jsp:include page="<%= test%>" />


        </div>
        <!-- /#wrapper -->
        <!-- FOOTER -->

        <!-- jQuery Version 1.11.0 -->
        <script src="<%= request.getContextPath()%>/assets/js/jquery-1.11.0.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="<%= request.getContextPath()%>/assets/js/bootstrap.min.js"></script>

        <!-- FORM VALIDATION -->

        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>

    </body>

</html>
