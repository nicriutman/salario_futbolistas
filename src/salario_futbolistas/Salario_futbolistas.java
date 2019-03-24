
package salario_futbolistas;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static oracle.jrockit.jfr.events.Bits.intValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Salario_futbolistas {

    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {       
        JSONArray  jsonarray = leer_json();
        HashMap<String, json> map = convercion_a_map(jsonarray);
	}  
    
    public static HashMap convercion_a_map (JSONArray jsonarray) throws IOException, FileNotFoundException, ParseException{
          JSONArray  jsonarray2 = jsonarray;
          JSONObject obj = new JSONObject(); 
          HashMap<String, json> map = new HashMap<String, json>();          
           for(int i = 0;i<jsonarray2.size();i++){
           String clave = Integer.toString(i);
           obj = (JSONObject) jsonarray2.get(i); 
           String nombre = (String) obj.get("nombre");
           String nivel = (String) obj.get("nivel");           
           int goles = intValue(obj.get("goles"));
           int sueldo = intValue(obj.get("sueldo"));
           int bono = intValue(obj.get("bono"));
           int sueldo_completo = intValue(obj.get("sueldo_completo"));
           String equipo = (String) obj.get("equipo");
           map.put(clave,new json(nombre,nivel,goles,sueldo,bono,sueldo_completo,equipo));            
        }  
        return map;                      
          }
         
    
    
    public static JSONArray  leer_json() throws FileNotFoundException, IOException, ParseException{
        JSONParser parser = new JSONParser();      
	Object obj = parser.parse(new FileReader("informacion.json"));        
	JSONArray  jsonarray = (JSONArray ) obj;                        
        return jsonarray ;
            
}
    }

   