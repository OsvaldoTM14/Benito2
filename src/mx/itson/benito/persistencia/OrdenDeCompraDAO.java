/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.OrdenDeCompra;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author ovalo
 */
public class OrdenDeCompraDAO {

    public static List<OrdenDeCompra> obtenerTodos() {
        List<OrdenDeCompra> orden = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<OrdenDeCompra> criteriaQuery = session.getCriteriaBuilder().createQuery(OrdenDeCompra.class);
            criteriaQuery.from(OrdenDeCompra.class);
            orden = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.err.println("Ocurrio un errro: " + ex);
        }
        return orden;
    }

}
