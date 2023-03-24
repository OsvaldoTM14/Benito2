/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.benito.persistencia;

import javax.swing.JOptionPane;
import mx.itson.benito.entidades.Usuario;
import mx.itson.benito.presentacion.ProveedorListado;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ovalo
 */
public class UsuarioDAO {

    public static boolean Create(String user, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Usuario usuario = new Usuario();
            usuario.setUser(user);
            usuario.setPassword(password);

            session.save(usuario);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public static boolean Login(String user, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Usuario usuario
                = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("user", user)).uniqueResult();

        try {
            if (usuario != null) {

                if (usuario.getPassword().equals(password)) {

                    ProveedorListado pl = new ProveedorListado();
                    pl.setVisible(true);
                    
                    JOptionPane.showMessageDialog(null, "Bienvendo usuario " + usuario.getUser());

                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la contraseña " + " no corresponde a la del usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario" + usuario.getUser() + " no existe", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

    }

}
