

<%@page import="Page.Page"%>
<%@page import="TypeContenu.TypeContenu"%>
<%@page import="Contenu.Contenu"%>
<%@page import="Utilisateur.Utilisateur"%>
<%@page import="java.util.List"%>
<%!
    List<Object> listTypeContenu = null;
    List<Object> utilisateur = null;
    List<Object> allEmp = null;
    List<Object> contenuResultat = null;
    Page p = null;
    String succes = null;
%>
<%
    listTypeContenu = (List<Object>) request.getAttribute("listTypeContenu");

    allEmp = (List<Object>) request.getAttribute("allUtil");
    utilisateur = (List<Object>) request.getAttribute("utilisateur");
    contenuResultat = (List<Object>) request.getAttribute("contenuResultat");
    succes = (String) request.getAttribute("succes");
    p = (Page) request.getAttribute("page");

%>
<div id="page-content-wrapper">

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Recherche

                </h1>
                <ol class="breadcrumb">
                    <li>Par: </li>
                    <li class="active">
                        Type Contenu
                    </li>
                    <li>
                        <form class="form-inline" method="post" action="recherche.do" enctype="multipart/form-data">
                            <input type="text" name="idutilisateur" value="<%= ((Utilisateur) utilisateur.get(0)).getId()%>" hidden="" >
                            <select class="form-control" name="contenu/typecontenu">
                                <option value="">Select</option>
                                <% if (listTypeContenu != null) { %>
                                <% for (int i = 0; i < listTypeContenu.size(); i++) {%>
                                <option value="<%= ((TypeContenu) listTypeContenu.get(i)).getNom()%>"><%= ((TypeContenu) listTypeContenu.get(i)).getNom()%></option>
                                <% }
                                } else {%>
                                <option>Pas  Type de Contenu Trouve</option>
                                <% }%>
                            </select>
                            <div class="form-group mb-2">
                                <input type="text" class="form-control" name="contenu/nomfichier"  placeholder="Nom du fichier">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <input type="date" class="form-control" name="contenu/datecreation" placeholder="Date">
                            </div>
                            <div class="form-group mx-sm-3 mb-2">
                                <select class="form-control" name="contenu/idutilisateur">
                                    <option value="">Select</option>
                                    <% if (allEmp != null) { %>
                                    <% for (int i = 0; i < allEmp.size(); i++) {%>
                                    <option value="<%= ((Utilisateur) allEmp.get(i)).getId()%>"><%= ((Utilisateur) allEmp.get(i)).getNom() + " " + ((Utilisateur) allEmp.get(i)).getPrenom()%></option>
                                    <% }
                                    } else {%>
                                    <option>Pas de Responsable Trouve!</option>
                                    <% }%>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary mb-2">Rechercher</button>
                        </form>
                    </li>
                </ol>
                <% if (succes != null) {%>
                <div class="alert alert-success" role="alert">
                    <%= succes%>
                </div>
                <%}%>
                <div class="table-responsive">
                    <form method="post" action="info.do" enctype="multipart/form-data">
                        <input type="text" name="idUtilisateur" value="<%= ((Utilisateur) utilisateur.get(0)).getId()%>" hidden="" >
                        <table class="table table-hover table-striped table-bordered table-condensed">
                            <thead>
                                <tr>
                                    <th>Titre</th>
                                    <th>Type de contenu</th>
                                    <th>Date creation</th>
                                    <th>Description</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if (contenuResultat != null) { %>
                                <% for (int i = 0; i < contenuResultat.size(); i++) {%>
                                <tr>
                                    <td><%= ((Contenu) contenuResultat.get(i)).getTitre()%></td>
                                    <td><%= ((Contenu) contenuResultat.get(i)).getTypecontenu()%></td>
                                    <td><%= ((Contenu) contenuResultat.get(i)).getDatecreation()%></td>
                                    <% if (((Contenu) contenuResultat.get(i)).getDescription() != null) {%>
                                    <td><%= ((Contenu) contenuResultat.get(i)).getDescription()%></td>
                                    <%} else { %>
                                    <td class="alert alert-info" role="alert">
                                        Pas de description
                                    </td>
                                    <%}%>

                                    <td><a href="#"><button type="submit" name="contenu/id" value="<%= ((Contenu) contenuResultat.get(i)).getId()%>" class="btn btn-info form-control">Plus details  </button></a></td>

                                </tr>
                                <%}
                                    }%>
                            </tbody>
                        </table>
                    </form>
                </div>
                <!-- /.table-responsive -->


            </div>
            <!-- /.col-lg -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
    <!<!-- CONTENURESULTAT AVY AN @ SETPAGE (UTILISATEURCONTROLLER) SY RECHERCHE (CONTENUCONTROLLER -->

    <% if (contenuResultat != null) {%>
    <% if (p.getTotalPage() > 1) {%>
    <%= "nombre par page " + p.getNombreParPage() + "<br>"%>
    <%= "total element " + p.getTotalElement().intValue() + "<br>"%>
    <%= "total page " + p.getTotalPage() + "<br>"%>
    <%= "Page num " + p.getPage_num()+ "<br>"%>
    <%= "page courant " + p.getPageCourant().intValue()+ "<br>"%>
    <%= "arret boucle " + p.getArret_boucle()+ "<br>"%>
    <%= "interval gauche " + p.getIntervalGauche()+ "<br>"%>
    <%= "interval droit " + p.getIntervalDroite()+ "<br>"%>
    <form method="post" action="recherche.do" enctype="multipart/form-data">
        <input type="text" name="idutilisateur" value="<%= ((Utilisateur) utilisateur.get(0)).getId()%>" hidden="" >
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <% if (p.getPage_num()> 1) {%>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <!--input type="text" name="page/pageCourant" value="<%= p.getPageCourant().intValue() %>" hidden=""/-->
                        <button type="submit" name="page/direction" value="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </button>
                    </a>
                </li>
                <%}%>
                <!--  for (int i = p.getPageCourant() ; i <= p.initiale(); i++) {%> -->
                
                <% for (int i = p.getPage_num(); i <= p.getArret_boucle(); i++) {%>
                <li class="page-item">
                    <a class="page-link" href="#">
                        <button type="submit" name="page/pageCourant" value="<%= i %>">
                            <%= i%>
                        </button>
                    </a>
                </li>
                <% }%>
                <% if (p.getPage_num()<= p.getTotalPage()) {%>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <!--input type="text" name="page/pageCourant" value="<%= p.getPageCourant().intValue() %>" hidden=""/-->
                        <button type="submit" name="page/direction" value="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </button>
                    </a>
                </li>
                <%}%>
            </ul>
        </nav>

    </form>
    <%}

        }%>
</div>

<!-- /#page-content-wrapper -->