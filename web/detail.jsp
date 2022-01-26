<%@page import="Utilisateur.Utilisateur"%>
<%@page import="TypeContenu.TypeContenu"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Contenu.Contenu"%>
<%@page import="java.util.List"%>
<%!
    Contenu contenuById = null;
    Utilisateur utilisateur = null;
    String erreur = "";
    //List<Object> utilisateur = null;
%>
<%

    contenuById = (Contenu) request.getAttribute("contenuById");
    utilisateur = (Utilisateur) ((List<Object>) request.getAttribute("utilisateur")).get(0);
    erreur = (String) request.getAttribute("erreur");
//    utilisateur = (List<Object>) request.getAttribute("utilisateur");
//    if(utilisateur!= null){
//        out.print("<br><br>"+ ((Employe) utilisateur.get(0)).getEmail() );
//    }

%>
<div id="page-content-wrapper">

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Details

                </h1>

                <!-- <h4>{User} complaint history goes here</h4> -->
                <!-- DB RETRIEVAL search db where id is his and status is processed/pending -->
                <div class="table-responsive">
                    <div class="col-lg-6">
                        <div class="card mb-3" style="max-width: 540px;">
                            <div class="row no-gutters">
                                <div class="col-md-4">
                                    <img src="<%= request.getContextPath()%>/assets/img/Face.jpg" class="card-img" alt="Profile" width="100%" height="100%">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h3 class="card-title">Responsable</h3>
                                        <h4><b>Nom</b> : <%= utilisateur.getNom()%> </h4> 
                                        <h4><b>Prenom</b> : <%= utilisateur.getPrenom()%> </h4> 
                                        <h4><b>Email</b> : <%= utilisateur.getEmail()%> </h4> 
                                        <h4><b>Contact</b> : <%= utilisateur.getContact()%> </h4> 
                                        <h4><b>Addresse</b> : <%= utilisateur.getAddresse()%> </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><br>
                    <div class="col-lg-6 ">
                        <form method="post" action="telecharger.do" enctype="multipart/form-data">
                            <% if (contenuById != null) {%>
                            <input type="text" name="idUtilisateur" value="<%= utilisateur.getId()%>" hidden="">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title"><b>Titre </b>: <%= contenuById.getTitre()%></h4>
                                    <p >Type du contenu : <%= contenuById.getTypecontenu()%> </p>
                                    <p >Date creation : <%= contenuById.getDatecreation()%> </p>
                                    <% if (contenuById.getDatefin() != null) {%>
                                    <p >Date fin : <%= contenuById.getDatefin()%> </p>
                                    <% }
                                        if (contenuById.getDescription() != null) {%>
                                    <p >Description : <%= contenuById.getDescription()%> </p>
                                    <%}
                                        if (contenuById.getLieu() != null) {%>
                                    <p >Lieu : <%= contenuById.getLieu()%> </p>
                                    <%}
                                        if (contenuById.getRemarque() != null) {%>
                                    <p >Remarque : <%= contenuById.getRemarque()%> </p>
                                    <% }
                                        if (contenuById.getNomfichier() != null) {%>
                                    <a href="#" ><button type="submit" name="contenu/id" value="<%= contenuById.getId()%>" class="btn btn-primary"> Telecharger</button></a> <small><%= contenuById.getNomfichier()%></small> 
                                    <% }%>

                                </div>
                            </div>
                            <%} else {%>
                            <div class="alert alert-warning" role="alert">
                                <%= erreur%>
                            </div>
                            <% }%>
                            <br>

                        </form>
                    </div>
                    <div class="col-lg-6"></div>
                    <div class="col-lg-6">
                        <form method="post" action="recherche.do" enctype="multipart/form-data">
                            <input type="text" name="idUtilisateur" value="<%= utilisateur.getId()%>" hidden="">
                            <a href="#"><button type="submit" class="btn btn-primary"> Retour</button></a>
                        </form>
                    </div>
                </div>

                <!-- /.table-responsive -->


            </div>
            <!-- /.col-lg -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->

</div>
<!-- /#page-content-wrapper -->