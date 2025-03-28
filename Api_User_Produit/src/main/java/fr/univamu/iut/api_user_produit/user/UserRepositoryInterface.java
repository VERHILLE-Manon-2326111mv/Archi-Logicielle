package fr.univamu.iut.api_user_produit.user;
import java.util.*;


public interface UserRepositoryInterface {


    public void close();


    public User getUser( int id );


    public ArrayList<User> getAllUser() ;
    public ArrayList<User> getAllUserClient() ;
    public ArrayList<User> getAllUserGestionnaire() ;



    public boolean updateUser( int id_user, String password, String nom, String role);

    public boolean deleteUser( User user);

    public boolean addUser( User user );

}