
package prbpackage;

/**
 *
 * @author Diego
 */
public class Arreglo {
     private String arr[] =null;

    public Arreglo()
    {
    }

    public String[] getArr()
    {
        return arr;
    }

    public void setArr(String[] arr)
    {
        this.arr = arr;
    }
    
    public int tamanio(){
        if (arr== null)
        {
            return 0;
        }
        else{
            return arr.length;
        }
    }
    public boolean vacio(){
        return arr==null;
    }
    public void inserta(String a){
        if(arr == null){
            arr=  new String [1];
            arr[0]=a;
        }
        else{
            String nvo [] = new String[arr.length+1] ;
            System.arraycopy(arr, 0, nvo, 0, arr.length);
            nvo[arr.length]=a;
            arr= nvo;
        }
    }
    public String elimina(int p){
        if (p >=0 && arr != null)
        {
            String a = arr[p];
            if (p==0 && arr.length == 1)
            {
                arr = null;
            }
            else{
                String nvo[] = new String[arr.length-1];
                int j=0;
                for (int i = 0; i < arr.length; i++)
                {
                    if (p!= i)
                    {
                        nvo[j ++] = arr[i];
                    }
                }
                arr= nvo;
            }
            return a;
        }
        else{
//            System.out.println("Posicion incorrecta o no hay datos en el arreglo ");
            return null;
        }
    }
    public int busca(String nom){
        if(arr==null){
            return -1;
        }
        else{
            for (int i = 0; i < arr.length; i++)
            {
                if (arr[i].equals(nom))
                {
//                    System.out.println("Dato encontrado en la posicion "+ 1);
                    return i;
                }
            }
//            System.out.println("Dato no encontrado ");
            return -2;
        }
    }
    public void vaciarArr(){
        String arrAux[]=null;
        this.arr=arrAux;
    }
    
}
