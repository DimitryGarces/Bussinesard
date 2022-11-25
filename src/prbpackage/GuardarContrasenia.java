
package prbpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Diego
 */
public class GuardarContrasenia <Tipo>{

    public void guardaC(String nomArch, String obj){
        try
        {
            FileOutputStream ubicacion = new FileOutputStream( nomArch);
            ObjectOutputStream arch = new ObjectOutputStream(ubicacion);
            arch.writeObject(obj);
            arch.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("Error, no se encontró el archivo... ");
        }
        catch(IOException ex){
            System.out.println("Error "+ ex.toString());
        }
    }
    public String cargaC(String nomArch){
        String obj= null;
        try
        {
            FileInputStream ubicacion = new FileInputStream(nomArch);
            ObjectInputStream arch = new ObjectInputStream(ubicacion);
            obj =(String)arch.readObject().toString();
            
        } catch (FileNotFoundException ex)
        {
            System.out.println("Error no se encontro el archivo ");
        } catch (Exception ex)
        {
            System.out.println("Error "+ ex.toString());
        }
        return obj;
    }
    
}
