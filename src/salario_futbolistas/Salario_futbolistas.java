
package salario_futbolistas;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
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
        HashMap<String, Double> map_porcentajes_totales = porcentage_total(map_de_json,mapa_de_niveles);        
	}  
    
    public static HashMap porcentage_total(HashMap map,HashMap map2){
        double porcentaje_grupal = 0;
        double porcentaje_individual = 0;
        double porcentaje_total = 0;
        int total_goles_individuales =0;
        int meta_de_goles=0;
        HashMap<String, json> map_de_json = map;
        HashMap<String, Integer> map_de_niveles = map2;
        HashMap<String, Double> map_porcentajes_totales = new HashMap<String,Double>();
        for (int i =0;i<map_de_json.size();i++){
          total_goles_individuales = total_goles_individuales + map_de_json.get(i).getGoles(); 
          meta_de_goles = meta_de_goles + map_de_niveles.get(map_de_json.get(i).getNivel());          
        }  
        porcentaje_grupal = total_goles_individuales/meta_de_goles;
        porcentaje_grupal = porcentaje_grupal*100;
        for(int i =0;i<map_de_json.size();i++){
          porcentaje_individual =  map_de_json.get(i).getGoles()/map_de_niveles.get(map_de_json.get(i).getNivel());
          porcentaje_individual = porcentaje_individual*100;
          porcentaje_total = porcentaje_individual+porcentaje_grupal;
          porcentaje_total = porcentaje_individual/2;
          map_porcentajes_totales.put(map_de_json.get(i).getNombre(),porcentaje_total);
        }
        return map_porcentajes_totales;        
    }
    
    public static HashMap capturar_niveles(){
        Scanner reader = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>(); 
        System.out.println("ingrese el valor de los niveles");
        System.out.println("A=");                                              
        map.put("A",reader.nextInt());
        System.out.println("B=");
        map.put("B",reader.nextInt());
        System.out.println("C=");        
        map.put("C",reader.nextInt());
        System.out.println("Cuauh=");
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

   