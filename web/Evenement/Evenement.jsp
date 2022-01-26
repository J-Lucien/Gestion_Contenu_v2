<%@page import="Utilisateur.Utilisateur"%>
<%@page import="java.util.List"%>
<%!
    String message = "";
    List<Object> utilisateur = null;
%>
<%
    message = (String) request.getAttribute("message") != null ? (String) request.getAttribute("message") : "";
    utilisateur = (List<Object>) request.getAttribute("utilisateur");
%>
<div id="page-content-wrapper">

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Evenement
                </h1>
                <% if (!message.isEmpty()) {%>
                <div class="alert alert-success" role="alert">
                    <%= message%>
                </div>
                <% }%>
                <!-- Pills Tabbed HISTORY | DUE -->
                <ul class="nav nav-pills nav-justified">
                    <li class="active"><a href="#" data-toggle="pill">Evenement</a>
                    </li>

                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="generated">

                        <form action="ajoutContenu.do" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="titre">Titre</label>
                                <input type="text" class="form-control" name="contenu/titre" id="titre">
                            </div>
                            <div class="form-group">
                                <label for="titre">Lieu</label>
                                <input type="text" class="form-control" name="contenu/lieu" id="titre">
                            </div>
                            <div class="form-group">
                                <label for="daty">Date debut</label>
                                <input type="datetime-local" class="form-control" name="contenu/datecreation" id="daty">
                            </div>
                            <div class="form-group">
                                <label for="daty">Date fin</label>
                                <input type="datetime-local" class="form-control" name="contenu/datefin" id="daty">
                            </div>
                            <div class="form-group">
                                <label for="description">description</label>
                                <textarea class="form-control" name="contenu/description" id="description" rows="3"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="remarque">Remarque </label>
                                <textarea class="form-control" name="contenu/remarque" id="description" rows="3"></textarea>
                            </div>
                            <input type="text" name="contenu/typecontenu" value="Evenement" hidden="">
                            <input type="text" name="typeContenu/page" value="Evenement" hidden="">
                            <input type="text" name="contenu/idutilisateur" value="<%= ((Utilisateur) utilisateur.get(0)).getId()%>" hidden="">
                            <button type="submit" class="btn btn-primary">Enregistrer</button>
                        </form>

                    </div>

                    <!-- .table-responsive -->
                </div>

            </div><!-- .tab-content -->

        </div><!-- /.col-lg-12 -->

    </div> <!-- /.row -->

</div><!-- /.container-fluid -->


</div
