/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import cl.pojos.Categoria;
import cl.util.HibernateUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author patri
 */
@ManagedBean(name = "catBean")
@RequestScoped
public class CategoriaBean {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaBean() {
    }

    //devolver una lista de categorias    
    public List<Categoria> getCategorias() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        //estructura hql
        List<Categoria> lista = s.createQuery("from Categoria").list();

        //2da Forma de obtener datos , recibe la clase que se quiere mapear.. como esta construida la clase
        //categoria.class
        List<Categoria> lista2 = s.createCriteria(Categoria.class).list();
        return lista;
    }

    public String guardar() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();

        Transaction ts = s.beginTransaction();

        Categoria cat = new Categoria(nombre);
        s.saveOrUpdate(cat);
        //hace efectiva la transaccion
        ts.commit();
        return "index";
    }
    
//    public String eliminar()
//    {
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        Session s = sf.openSession();
//
//        Transaction ts = s.beginTransaction();
//        Categoria cat=new Categoria(eliminar());
//        s.delete(cat);
//        ts.commit();
//        return "index";
//    }

}
