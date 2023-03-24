/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.table.DefaultTableModel;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
//+

/**
 *
 * @author ovalo
 */
public class ArticuloDAO {

    public static List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);
            articulos = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.err.println("Ocurrio un errro: " + ex);
        }
        return articulos;
    }

    public static boolean guardar(String clave, String nombre, double precio, Proveedor proveedor) {
        boolean resultado = false;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Articulo a = new Articulo();
            a.setClave(clave);
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setProvedor(proveedor);

            session.save(a);
            session.getTransaction().commit();

            resultado = a.getId() != 0;

        } catch (Exception ex) {

            System.err.println("Ocurrio un error" + ex.getMessage());
        }
        return resultado;
    
}
 public static Articulo obtenerPorId(int id) {

        Articulo articulo = null;

        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            articulo = session.get(Articulo.class,
                     id);

        } catch (HibernateException ex) {

            System.out.println("Ocurrio un error: " + ex.getMessage());

        }
        return articulo;
    }


}
