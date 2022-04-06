package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.IProduitDao;
import dao.ProduitDaoImpl;
import metier.Produit;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IProduitDao metier;
@Override
public void init() throws ServletException {
metier = new ProduitDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response) 
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("produits.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
ProduitModele model= new ProduitModele();
model.setMotCle(motCle);
List<Produit> prods = metier.produitsParMC(motCle);
model.setProduits(prods);
request.setAttribute("model", model);
request.getRequestDispatcher("produits.jsp").forward(request,response);
}
}
}