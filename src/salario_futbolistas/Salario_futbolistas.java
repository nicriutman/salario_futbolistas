
package salario_futbolistas;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;
import static oracle.jrockit.jfr.events.Bits.intValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Salario_futbolistas {

    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {       
        HashMap<String, json> mapa_de_niveles = capturar_niveles();        
        String ruta = ruta_de_json();
        JSONArray  jsonarray = leer_json(ruta);
        HashMap<String, json> map_de_json = convercion_a_map(jsonarray); 
        
	}  
    
    public static HashMap capturar_niveles(){
        Scanner reader = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>(); 
        System.out.println("ingresa el nombre del nivel y luego el valor");                                              
        map.put("A",reader.nextInt());
        map.put("B",reader.nextInt());
        map.put("C",reader.nextInt());
        map.put("Cuauh",reader.nextInt());
        return map;        
    }
    
    public static String ruta_de_json(){   
        System.out.println("copia la ruta del archivo json");
        Scanner reader = new Scanner(System.in);
        String ruta = reader.nextLine();
        return ruta;        
    }
    public static HashMap convercion_a_map (JSONArray jsonarray) throws IOException, FileNotFoundException, ParseException{
          JSONArray  jsonarray2 = jsonarray;
          JSONObject obj = new JSONObject(); 
          HashMap<Integer, json> map = new HashMap<Integer, json>(); 
          String nombre;
          String nivel;
          int goles;
          int sueldo;
          int bono;
          int sueldo_completo;
          String equipo;
           for(int i = 0;i<jsonarray2.size();i++){           
           obj = (JSONObject) jsonarray2.get(i); 
           nombre = (String) obj.get("nombre");
           nivel = (String) obj.get("nivel");           
           goles = intValue(obj.get("goles"));
           sueldo = intValue(obj.get("sueldo"));
           bono = intValue(obj.get("bono"));
           sueldo_completo = intValue(obj.get("sueldo_completo"));
           equipo = (String) obj.get("equipo");
           map.put(i,new json(nombre,nivel,goles,sueldo,bono,sueldo_completo,equipo));            
        }  
        return map;                      
          }             
    
    public static JSONArray  leer_json(String ruta) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();      
	Object obj = parser.parse(new FileReader(ruta));        
	JSONArray  jsonarray = (JSONArray ) obj;                        
        return jsonarray ;
            
}
    }

   