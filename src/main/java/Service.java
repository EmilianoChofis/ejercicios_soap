import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

@WebService(name="Service", targetNamespace = "utez")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Service {
    @WebMethod(operationName = "generaRfc")

    //ejercicio 04
   /* public String responseMessage(@WebParam(name = "num") int num){
        Random randomxd = new Random();
        int random = randomxd.nextInt(10);
        //random = (int)(Math.random()*2)+1;
        System.out.println("numero random: "+random);
        if (num == random){
            return "diste en el clavo";
        }
        return "no";
    }*/

    //Ejercicio 05
    /*public String responseMessage05(@WebParam(name = "palabra") String palabra){

            return palabra.replaceAll("[aeiou]","");


    }*/

    //ejercicio 06
    public String generaRfc(@WebParam(name = "nombre") String nombre, @WebParam(name = "paterno") String paterno,
                            @WebParam(name = "materno") String materno, @WebParam(name = "anio") String anio,
                            @WebParam(name = "mes") String mes, @WebParam(name = "dia") String dia){
        String rfc;
        SecureRandom random = new SecureRandom();
        String fec_nac = anio+"/"+mes+"/"+dia;
        StringBuilder rfc_builder = new StringBuilder();
        for (int i = 0; i <= 6; i++) {
            if (i <= 1) {
                rfc_builder.append(paterno.charAt(i));
            } else {
                if (i == 3) {
                    rfc_builder.append(materno.charAt(0));
                } else {
                    if (i == 4) {
                        rfc_builder.append(nombre.charAt(0));
                    } else {
                        if (i == 5) {
                            rfc_builder.append(anio.charAt(2));
                            rfc_builder.append(anio.charAt(3));
                            rfc_builder.append(mes);
                            rfc_builder.append(dia);
                        }
                        if (i == 6) {
                            final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                            for (int j = 0; j < 3; j++) {
                                int randomIndex = random.nextInt(chars.length());
                                rfc_builder.append(chars.charAt(randomIndex));
                            }
                        }
                    }
                }
            }
        }
//
        rfc = String.valueOf(rfc_builder);

    return rfc.toUpperCase();
    }


    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/Service",new Service());
        System.out.println("Esperando...");
    }
}
